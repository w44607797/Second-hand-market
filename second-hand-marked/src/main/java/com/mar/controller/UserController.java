package com.mar.controller;

import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.exception.DatabaseException;
import com.mar.exception.RedisException;
import com.mar.exception.UserException;
import com.mar.service.RedisService;
import com.mar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @PostMapping("/passport/login")
    public ResponseResult<String> userLogin(@Valid UserLoginVO userLoginVO, BindingResult bindingResult) throws UserException, DatabaseException, RedisException {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new UserException(defaultMessage);
        }
        ResponseResult<String> responseResult = userService.userLogin(userLoginVO);
        if(!responseResult.isOk()){
            return responseResult;
        }
        String s = userService.UserBingdingJWT(userLoginVO);
        responseResult.setData(s);
        return responseResult;
    }
}
