package com.mar.bean.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guokaifeng
 * @createDate: 2022/4/23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoParam {
    private String phone;
    private String permission;

}
