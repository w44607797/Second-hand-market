package com.mar.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.Digester;
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
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisService redisService;

    @Override
    public ResponseResult userLogin(UserLoginVO userLoginVO) throws TotalException {
        Map<String, Object> map = new HashMap();
        map.put("condition","password");
        map.put("phone",userLoginVO.getPhone());
        String salt;
        String password;
        try {
            password = userMapper.getParamByPhone(map);
            map.put("condition","salt");
            salt = userMapper.getParamByPhone(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TotalException(StateEnum.DATABASE_ERROR_MESSAGE.getCode(),
                    StateEnum.DATABASE_ERROR_MESSAGE.getMessage(),
                    StateEnum.DATABASE_ERROR_FAILEDTOGETUSER.getMessage());
        }
        if(password==null||salt==null){
            return ResponseResult.failed(StateEnum.USER_ERROR_NOREGISTER.getCode(),
                    StateEnum.USER_ERROR_NOREGISTER.getMessage());
        }
        String total = userLoginVO.getPassword()+salt;
        String pass = MD5Util.getMD5(total);
        if(pass.equals(password)){
            return ResponseResult.success();
        }
        return ResponseResult.failed(StateEnum.USER_ERROR_WRONGPASSWORD.getCode(),
                StateEnum.USER_ERROR_WRONGPASSWORD.getMessage());
    }

    @Override
    public ResponseResult userRegister(UserRegisterVO userRegisterVO) {
        String regCode = userRegisterVO.getCode();
        String phone = userRegisterVO.getPhone();
        String salt = SaltUtil.getSalt(4);
        String total = userRegisterVO.getPassword()+ salt;
        String resultPassword = MD5Util.getMD5(total);
        String correctCode = redisService.getCode(phone);
        if(!regCode.equals(correctCode)){
            return ResponseResult.failed(StateEnum.USER_ERROR_WRONGCODE.getCode(),
                    StateEnum.USER_ERROR_WRONGCODE.getMessage());
        }
        int i = userMapper.registerUser(phone,resultPassword,salt);
        if(i==0){
            return ResponseResult.failed(StateEnum.USER_ERROR_HASBEENREGISTER.getCode(),
                    StateEnum.USER_ERROR_HASBEENREGISTER.getMessage());
        }
        return ResponseResult.success();
    }

    @Override
    public String UserBingdingJWT(UserLoginVO userLoginVO) throws TotalException {
        String phone = userLoginVO.getPhone();
        String jwt = JWTUtil.getJWT(phone);
        try {
            redisService.setToken(phone,jwt);
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
