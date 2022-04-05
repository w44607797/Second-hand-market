package com.mar.utils;



/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/


public enum StateEnum {
    USER_ERROR_NOREGISTER("001","用户未注册"),
    USER_ERROR_HASBEENREGISTER("002","该手机号已经注册过了"),
    USER_ERROR_WRONGPASSWORD("003","用户密码错误"),
    USER_ERROR_WRONGCODE("004","登录验证码出错"),


    SERVICE_ERROR_FAILEDTOGETCODE("300","返回验证码失败"),


    DATABASE_ERROR_MESSAGE("500","服务端出错,请联系管理员"),
    DATABASE_ERROR_FAILEDTOGETUSER("501","数据库获取用户信息出错");


    private String code;
    private String message;
    StateEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
