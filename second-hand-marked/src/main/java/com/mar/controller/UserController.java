package com.mar.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.exception.DatabaseException;
import com.mar.exception.RedisException;
import com.mar.exception.UserException;
import com.mar.service.RedisService;
import com.mar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@RestController
@RequestMapping("/api/user")
@CrossOrigin
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
    @PostMapping("/passport/register")
    public ResponseResult userRegister(@Valid UserRegisterVO userRegisterVO, BindingResult bindingResult) throws UserException {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new UserException(defaultMessage);
        }
        return userService.userRegister(userRegisterVO);
    }

    @GetMapping("/passport/sendCode/{phone}")
    public ResponseResult getVertifyCode(HttpServletResponse response, @PathVariable String phone) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        OutputStream out = response.getOutputStream();
        try {
            lineCaptcha.write(out);
            redisService.storeCode(phone,lineCaptcha.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed(300,"返回验证码失败");
        }finally {
            out.close();
        }
        return ResponseResult.success();
    }
    @GetMapping("/passport/logout/{phone}")
    public ResponseResult userLogout(@PathVariable String phone){
        redisService.deleteKey(phone);
        return ResponseResult.success();
    }
}
