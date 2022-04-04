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
public class RedisExceptionHandle {
    @ResponseBody
    @ExceptionHandler(RedisException.class)
    public ResponseResult RedisException(DatabaseException e){
        return ResponseResult.failed(600,e.getMessage());
    }
}
