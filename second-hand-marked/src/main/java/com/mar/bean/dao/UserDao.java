package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

@TableName("app_user")
public class UserDao {
    private String phone;
    private String password;
    private String headshot;
    private String salt;
    private String permission;

}
