package com.mar.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.SecretKey;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@Component
public class JWTUtil {

    public final static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String getJWT(String phone){
        return Jwts.builder().setSubject(phone).signWith(key).compact();
    }

}
