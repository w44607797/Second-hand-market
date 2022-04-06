package com.mar.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterVO {
    @Min(value = 0,message = "用户的id不符合格式")
    @NotEmpty(message = "用户id不能为空")
    private String phone;

    @NotEmpty(message = "用户密码不能为空")
    @NotNull(message = "用户密码不能为空")
    private String password;

    @NotEmpty(message = "验证码不能为空")
    @NotNull(message = "验证码不能为空")
    private String code;

}