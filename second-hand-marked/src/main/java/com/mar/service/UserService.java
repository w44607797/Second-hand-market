package com.mar.service;

import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import org.springframework.stereotype.Service;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/


public interface UserService {
    boolean userLogin(UserLoginVO userLoginVO);
    boolean userRegister(UserRegisterVO userRegisterVO);
    String UserBingdingJWT(UserLoginVO userLoginVO);
    String getLoginCode();
}
