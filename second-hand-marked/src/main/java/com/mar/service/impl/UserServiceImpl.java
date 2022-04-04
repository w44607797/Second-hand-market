package com.mar.service.impl;

import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@Service
public class UserServiceImpl implements UserService {


    @Override
    public boolean userLogin(UserLoginVO userLoginVO) {
        return false;
    }

    @Override
    public boolean userRegister(UserRegisterVO userRegisterVO) {
        return false;
    }

    @Override
    public String UserBingdingJWT(UserLoginVO userLoginVO) {
        return null;
    }

    @Override
    public String getLoginCode() {
        return null;
    }

}
