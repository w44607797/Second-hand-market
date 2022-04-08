package com.mar.service;

import com.mar.bean.vo.UserLoginVO;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/


public interface RedisService {
    void storeCode(String phone,String code);
    String getCode(String phone);
    void deleteKey(String key);
    boolean checkExist(String key);
    void setToken(String phone,String token);

}
