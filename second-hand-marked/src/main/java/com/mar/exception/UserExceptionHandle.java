package com.mar.exception;

import com.mar.bean.vo.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@ControllerAdvice
public class UserExceptionHandle {
    @ResponseBody
    @ExceptionHandler(UserException.class)
    public ResponseResult UserException(Exception e){
        return ResponseResult.failed(0,e.getMessage());
    }
}
