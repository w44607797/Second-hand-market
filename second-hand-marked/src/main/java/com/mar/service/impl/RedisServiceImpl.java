package com.mar.service.impl;

import com.mar.bean.vo.UserLoginVO;
import com.mar.service.RedisService;
import com.mar.utils.JWTUtil;
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


    @Override
    public void storeCode(String phone,String code) {
        redisTemplate.opsForValue().set(phone,code,1800,TimeUnit.SECONDS);
    }

    @Override
    public String getCode(String phone) {
       return redisTemplate.opsForValue().get(phone);
    }

    @Override
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
