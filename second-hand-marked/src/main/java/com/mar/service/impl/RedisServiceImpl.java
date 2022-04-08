package com.mar.service.impl;

import com.mar.bean.vo.UserLoginVO;
import com.mar.service.RedisService;
import com.mar.utils.JWTUtil;
import com.mar.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    RedisUtils redisUtils;


    @Override
    public void storeCode(String phone,String code) {
        redisTemplate.opsForValue().set(phone+":code",code,1800,TimeUnit.SECONDS);
    }

    @Override
    public String getCode(String phone) {
       return redisTemplate.opsForValue().get(phone+":code");
    }

    @Override
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean checkExist(String key) {
        return redisUtils.hasKey(key);
    }

    /**
     * phone:permission
     * @param phone
     * @param token
     */

    @Override
    public void setToken(String phone,String token) {
        redisTemplate.opsForValue().set(token,phone,1800,TimeUnit.SECONDS);
    }
}
