package com.mar.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.UserLoginVO;
import com.mar.bean.vo.UserRegisterVO;
import com.mar.exception.TotalException;
import com.mar.service.RedisService;
import com.mar.service.UserService;
import com.mar.utils.RedisUtils;
import com.mar.utils.StateEnum;
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
    @Autowired
    RedisUtils redisUtils;

    @PostMapping("/passport/login")
    public ResponseResult<String> userLogin(@Valid UserLoginVO userLoginVO, BindingResult bindingResult) throws TotalException {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new TotalException(StateEnum.USER_ERROR_ERRORFORMAT.getCode(),
                    defaultMessage,StateEnum.USER_ERROR_ERRORFORMAT.getMessage());
        }
        ResponseResult responseResult = userService.userLogin(userLoginVO);
        if(!responseResult.isOk()){
            return responseResult;
        }
        String s = userService.UserBingdingJWT(userLoginVO);
        responseResult.setData(s);
        return responseResult;
    }

    /**
     * 用户注册
     * @param userRegisterVO
     * @param bindingResult
     * @return
     * @throws TotalException
     */

    @PostMapping("/passport/register")
    public ResponseResult userRegister(@Valid UserRegisterVO userRegisterVO, BindingResult bindingResult) throws TotalException {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new TotalException(defaultMessage);
        }
        return userService.userRegister(userRegisterVO);
    }

    /**
     *
     * @param response
     * @param phone
     * @return 验证码图片
     * @throws IOException
     */

    @GetMapping("/passport/sendCode/{phone}")
    public ResponseResult getVertifyCode(HttpServletResponse response, @PathVariable String phone) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        OutputStream out = response.getOutputStream();
        try {
            lineCaptcha.write(out);
            redisService.storeCode(phone,lineCaptcha.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed(StateEnum.SERVICE_ERROR_FAILEDTOGETCODE.getCode(),
                    StateEnum.SERVICE_ERROR_FAILEDTOGETCODE.getMessage());
        }finally {
            out.close();
        }
        return ResponseResult.success();
    }
    @GetMapping("/passport/logout/{phone}")
    public ResponseResult userLogout(@PathVariable String phone){
//        redisService.deleteKey(phone);
        redisUtils.delete(phone);
        return ResponseResult.success();
    }

    @GetMapping("/nologin")
    public ResponseResult userNoLoginPage(TotalException totalException) throws TotalException {
        throw totalException;
    }
}
