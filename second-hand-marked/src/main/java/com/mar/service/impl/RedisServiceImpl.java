package com.mar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mar.bean.dao.UserDao;
import com.mar.bean.mapper.UserMapper;
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

    @Autowired
    UserMapper userMapper;

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
     * filed1:filed2
     * phone:permission
     * @param phone
     * @param token
     */

    @Override
    public void setToken(String phone,String token) {
        QueryWrapper<UserDao> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        queryWrapper.select("permission");
        UserDao userDao = userMapper.selectOne(queryWrapper);
        String permission = userDao.getPermission();
        redisUtils.hPut(token,"phone",phone);
        redisUtils.hPut(token,"permission",permission);
        redisUtils.expire(token,1800,TimeUnit.SECONDS);
    }

    @Override
    public String getUserPhoneByToken(String token) {
        return (String) redisUtils.hGet(token,"phone");
    }

    @Override
    public String getUserPermissionByToken(String token) {
        return (String) redisUtils.hGet(token,"permission");
    }

    @Override
    public String getOrderId() {
        if(!redisUtils.hasKey("orderId")){
            redisUtils.set("orderId","1");
            redisUtils.incrBy("orderId",1L);
            return "1";
        }
        redisUtils.incrBy("orderId",1L);
        return redisUtils.get("orderId");
    }

}