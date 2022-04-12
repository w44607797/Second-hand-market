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

    /**
     * 存储对应手机号的验证码
     * @param phone
     * @param code
     */

    @Override
    public void storeCode(String phone,String code) {
        redisTemplate.opsForValue().set(phone+":code",code,1800, TimeUnit.SECONDS);
    }

    /**
     * 获取对应手机号的验证码
     * @param phone
     * @return
     */
    @Override
    public String getCode(String phone) {
        return redisTemplate.opsForValue().get(phone+":code");
    }


    /**
     * 设置token对应的参数
     * phone:permission
     * @param phone
     * @param token
     */

    @Override
    public void setToken(String phone,String token) {
        redisTemplate.opsForValue().set(token,phone,1800,TimeUnit.SECONDS);
    }

    private String getUserParamByToken(String token,int index) {
        /**
         * phone:permission
         */
        String[] split = token.split(":");
        return split[index];
    }

    @Override
    public String getUserPhoneByToken(String token) {
        return getUserParamByToken(token,0);
    }

    @Override
    public String getUserPermissionByToken(String token) {

        return getUserParamByToken(token,1);
    }

}