package com.mar.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.Digester;
import com.mar.bean.mapper.UserMapper;
import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.exception.DatabaseException;
import com.mar.exception.RedisException;
import com.mar.service.RedisService;
import com.mar.service.UserService;
import com.mar.utils.ExceptionUtil;
import com.mar.utils.JWTUtil;
import com.mar.utils.MD5Util;
import com.mar.utils.SaltUtil;
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
    public ResponseResult userLogin(UserLoginVO userLoginVO) throws DatabaseException {
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
            throw new DatabaseException(ExceptionUtil.DATABASE_MESSAGE,ExceptionUtil.DATABASE_MESSAGE_USER);
        }
        if(password==null||salt==null){
            return ResponseResult.failed(1,"用户还未注册");
        }
        String total = userLoginVO.getPassword()+salt;
        String pass = MD5Util.getMD5(total);
        if(pass.equals(password)){
            return ResponseResult.success();
        }
        return ResponseResult.failed(101,"用户账号或者密码错误");
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
            return ResponseResult.failed(900,"验证码错误");
        }
        int i = userMapper.registerUser(phone,resultPassword,salt);
        if(i==0){
            return ResponseResult.failed(002,"用户已注册");
        }
        return ResponseResult.success();
    }

    @Override
    public String UserBingdingJWT(UserLoginVO userLoginVO) throws RedisException {
        String phone = userLoginVO.getPhone();
        String jwt = JWTUtil.getJWT(phone);
        try {
            redisTemplate.opsForValue().set(phone,jwt,1800, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RedisException("用户绑定JWT失败");
        }
        return jwt;
    }

    @Override
    public String getLoginCode() {
        return null;
    }



}
