package com.mar.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String skuId;
    private String cartPrice;
    private String skuNum;
    private String imgUrl;
    private String skuName;
    private String isChecked;
    private String skuPrice;
}
