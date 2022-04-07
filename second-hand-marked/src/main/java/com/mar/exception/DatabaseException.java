package com.mar.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@Slf4j
public class DatabaseException extends Exception{

    public DatabaseException(String message,String logMess){
        this(message);
        log.error(logMess);
    }
    public DatabaseException(){}
    public DatabaseException(String message){super(message);}

}
