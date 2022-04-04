package com.mar.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.Digester;
import com.mar.bean.mapper.UserMapper;
import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.exception.DatabaseException;
import com.mar.exception.RedisException;
import com.mar.service.UserService;
import com.mar.utils.ExceptionUtil;
import com.mar.utils.JWTUtil;
import com.mar.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
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
            throw new DatabaseException(ExceptionUtil.DATABASE_MESSAGE,ExceptionUtil.DATABASE_MESSAGE_USER);
        }
        if(password==null||salt==null){
            throw new DatabaseException(ExceptionUtil.DATABASE_MESSAGE,ExceptionUtil.DATABASE_MESSAGE_USER);
        }
        String pass = MD5Util.getMD5(userLoginVO.getPassword()+salt);
        if(pass.equals(password)){
            return ResponseResult.success();
        }
        return ResponseResult.failed(101,"用户账号或者密码错误");
    }

    @Override
    public boolean userRegister(UserRegisterVO userRegisterVO) {
        return false;
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
