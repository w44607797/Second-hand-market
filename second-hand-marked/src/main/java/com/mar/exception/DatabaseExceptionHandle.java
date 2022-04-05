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
public class DatabaseExceptionHandle {
    @ResponseBody
    @ExceptionHandler(DatabaseException.class)
    public ResponseResult DatabaseException(DatabaseException e){
        return ResponseResult.failed("500",e.getMessage());
    }
}
