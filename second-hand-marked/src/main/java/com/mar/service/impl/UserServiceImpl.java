package com.mar.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDao> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisService redisService;

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


}
