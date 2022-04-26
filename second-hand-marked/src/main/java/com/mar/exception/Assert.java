package com.mar.exception;

/**
 * @author guokaifeng
 * @createDate: 2022/4/18
 **/

public class Assert {

    public static void assertNotNull(Object object,String code,String message) throws TotalException {
        if(object==null){
            throw new TotalException(code,message,message);
        }
    }
}
