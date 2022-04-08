package com.mar.service;

import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.exception.DatabaseException;
import com.mar.exception.RedisException;
import com.mar.exception.TotalException;
import org.springframework.stereotype.Service;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/


public interface UserService {
    ResponseResult userLogin(UserLoginVO userLoginVO) throws DatabaseException, TotalException;
    ResponseResult userRegister(UserRegisterVO userRegisterVO);
    String UserBingdingJWT(UserLoginVO userLoginVO) throws RedisException, TotalException;
    String getLoginCode();


}
