package com.mar.exception;

import com.mar.bean.vo.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guokaifeng
 * @createDate: 2022/4/7
 **/

@ControllerAdvice
public class TotalExceptionHandle {
    @ResponseBody
    @ExceptionHandler(TotalException.class)
    public ResponseResult TotalExceptionHand(TotalException e){
        return ResponseResult.failed(e.getCode(),e.getMessage());
    }
}
