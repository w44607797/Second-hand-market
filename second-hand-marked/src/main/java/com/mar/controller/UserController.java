package com.mar.controller;

import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.exception.UserException;
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

    @PostMapping("/passport/login")
    public ResponseResult userLogin(@Valid UserLoginVO userLoginVO, BindingResult bindingResult) throws UserException {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new UserException(defaultMessage);
        }

        if(userService.userLogin(userLoginVO)){

        }else {

        }

        return null;
    }
}
