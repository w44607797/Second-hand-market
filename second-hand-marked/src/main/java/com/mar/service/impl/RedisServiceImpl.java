package com.mar.service.impl;

import com.mar.bean.vo.UserLoginVO;
import com.mar.service.RedisService;
import com.mar.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean storeJWT(UserLoginVO userLoginVO) {
        String UserId = userLoginVO.getPhone();
        String jwt = JWTUtil.getJWT(UserId);
        redisTemplate.opsForValue().set(jwt,UserId);
        return true;
    }
}
