package com.mar.service;

import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.exception.DatabaseException;
import com.mar.exception.RedisException;
import org.springframework.stereotype.Service;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/


public interface UserService {
    ResponseResult userLogin(UserLoginVO userLoginVO) throws DatabaseException;
    boolean userRegister(UserRegisterVO userRegisterVO);
    String UserBingdingJWT(UserLoginVO userLoginVO) throws RedisException;
    String getLoginCode();
}
