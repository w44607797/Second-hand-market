package com.mar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mar.bean.dao.UserDao;
import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.exception.TotalException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/


public interface UserService extends IService<UserDao> {
    ResponseResult userLogin(UserLoginVO userLoginVO) throws TotalException;
    ResponseResult userRegister(UserRegisterVO userRegisterVO);
    String getJWTByPhone(String phone) throws TotalException;
    String getLoginCode();
    String uploadHeadShot();
    OutputStream getUserHeadShotStream(String url) throws IOException;
    String getUserHeadShotUrl(String phone);



}
