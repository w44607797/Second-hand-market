package com.mar.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

public class RedisException extends Exception{

    public RedisException(){}
    public RedisException(String message){
        super(message);
    }
}
