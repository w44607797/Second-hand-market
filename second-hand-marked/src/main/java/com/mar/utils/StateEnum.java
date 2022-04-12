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
    USER_ERROR_FAILEDTOBINGDINGJWT("005","用户绑定JWT失败"),
    USER_ERROR_ERRORFORMAT("006","数据校验出错"),
    USER_ERROR_NOLOGIN("007","用户还没有登录"),
    USER_ERROR_WRONGJWT("008","用户身份过期"),

    SHOP_ERROR_FAILTOADDCART("400","加购数量不能再减啦"),
    SHOP_ERROR_NOSUCHCART("401","没有对应的购物车可删除"),

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
