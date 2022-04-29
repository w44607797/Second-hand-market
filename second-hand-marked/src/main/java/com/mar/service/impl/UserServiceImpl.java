package com.mar.service.impl;

import cn.hutool.Hutool;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mar.bean.dao.UserDao;
import com.mar.bean.mapper.UserMapper;
import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.exception.TotalException;
import com.mar.service.RedisService;
import com.mar.service.UserService;
import com.mar.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.ObjectName;
import javax.servlet.ServletOutputStream;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@Service
@Slf4j
@PropertySource("classpath:service.properties")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDao> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisService redisService;

    @Value("${user.headshot.url}")
    private String userHeadShotUrl;

    @Override
    public ResponseResult userLogin(UserLoginVO userLoginVO) throws TotalException {
        QueryWrapper<UserDao> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("password", "salt");
        queryWrapper.eq("phone", userLoginVO.getPhone());
        UserDao userDao = userMapper.selectOne(queryWrapper);
        if (userDao == null) {
            throw new TotalException(StateEnum.DATABASE_ERROR_MESSAGE.getCode(),
                    StateEnum.DATABASE_ERROR_MESSAGE.getMessage(),
                    StateEnum.DATABASE_ERROR_FAILEDTOGETUSER.getMessage());
        }

        /**
         * 获取乱码和盐
         */

        String salt = userDao.getSalt();
        String password = userDao.getPassword();

        if (password == null || salt == null) {
            return ResponseResult.failed(StateEnum.USER_ERROR_NOREGISTER.getCode(),
                    StateEnum.USER_ERROR_NOREGISTER.getMessage());
        }
        String total = userLoginVO.getPassword() + salt;
        String pass = MD5Util.getMD5(total);
        if (!pass.equals(password)) {
            return ResponseResult.failed(StateEnum.USER_ERROR_WRONGPASSWORD.getCode(),
                    StateEnum.USER_ERROR_WRONGPASSWORD.getMessage());
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult userRegister(UserRegisterVO userRegisterVO) {
        String regCode = userRegisterVO.getCode();
        String phone = userRegisterVO.getPhone();
        String salt = SaltUtil.getSalt(4);
        String name = userRegisterVO.getName();
        String total = userRegisterVO.getPassword() + salt;
        String resultPassword = MD5Util.getMD5(total);
        String correctCode = redisService.getCode(phone);
        if (!regCode.equals(correctCode)) {
            return ResponseResult.failed(StateEnum.USER_ERROR_WRONGCODE.getCode(),
                    StateEnum.USER_ERROR_WRONGCODE.getMessage());
        }
        /**
         * 判断是否注册
         */

        if (userMapper.checkIsRegister(phone) > 0) {
            return ResponseResult.failed(StateEnum.USER_ERROR_HASBEENREGISTER.getCode(),
                    StateEnum.USER_ERROR_HASBEENREGISTER.getMessage());
        }

        UserDao userDao = new UserDao();
        userDao.setSalt(salt);
        userDao.setPassword(resultPassword);
        userDao.setPhone(phone);
        userDao.setName(name);
        userMapper.insert(userDao);
        return ResponseResult.success();
    }

    @Override
    public String getJWTByPhone(String phone) throws TotalException {
        String jwt = JWTUtil.getJWT(phone);
        try {
            redisService.setToken(phone, jwt);
        } catch (Exception e) {
            throw new TotalException(StateEnum.USER_ERROR_FAILEDTOBINGDINGJWT.getCode(),
                    StateEnum.USER_ERROR_FAILEDTOBINGDINGJWT.getMessage(),
                    StateEnum.USER_ERROR_FAILEDTOBINGDINGJWT.getMessage());
        }
        return jwt;
    }

    @Override
    public String getLoginCode() {
        return null;
    }

    @Override
    public String uploadHeadShot(String url,String phone, MultipartFile file,String extendsion) throws IOException {
        String uuid = IdUtil.randomUUID();
        String profileName = url+"/"+phone+"/";
        File profile1 = new File(url);
        File profile2 = new File(profileName);

        if (!profile1.exists() || !profile2.exists()) {
            log.info("没有存储文件夹，尝试新建");
            if (profile1.mkdirs()) {
                log.info("存储文件夹新建成功");
            } else {
                log.error("存储文件夹新建失败");
                return "服务器存储失败";
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(uuid);
        sb.append(".");
        sb.append(extendsion);
        String fileName = sb.toString();
        byte[] bytes = file.getBytes();
        String allURL = profileName+fileName;

        OutputStream outputStream = new FileOutputStream(allURL);
        outputStream.write(bytes);
        UpdateWrapper<UserDao> updateWrapper = new UpdateWrapper<UserDao>();
        updateWrapper.eq("phone","17759048528");
        UserDao userDao = new UserDao();
        userDao.setHeadshot(allURL);
        userMapper.update(userDao, updateWrapper);
        return allURL;

    }

    @Override
    public ServletOutputStream getUserHeadShotStream(String phone, ServletOutputStream outputStream) throws IOException {

        String userHeadShotUrl = getUserHeadShotUrl(phone);
        /**
         * 获取headshoturl
         */

        try {
            InputStream fileInputStream = new FileInputStream(userHeadShotUrl);
            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                outputStream.write(ch);
            }
        } catch (IOException e) {
            log.error("url路径错误");
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

        return outputStream;
    }

    public String getUserHeadShotUrl(String phone) {
        QueryWrapper<UserDao> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("headshot");
        queryWrapper.eq("phone",phone);
        UserDao userDao = userMapper.selectOne(queryWrapper);
        String url = userDao.getHeadshot();
        if(url==null){
            //返回无头像的存储地址
            return userHeadShotUrl+"/Null";
        }
        return url;
    }


}
