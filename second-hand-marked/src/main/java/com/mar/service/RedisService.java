package com.mar.service;

import com.mar.bean.vo.UserLoginVO;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/


public interface RedisService {
    boolean storeJWT(UserLoginVO userLoginVO);
}
