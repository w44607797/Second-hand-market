package com.mar.exception;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

public class UserException extends Exception{
    public UserException(){}
    public UserException(String message){
        super(message);
    }
}
