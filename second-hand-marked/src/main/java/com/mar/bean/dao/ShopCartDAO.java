package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("shop_cart")
public class ShopCartDAO implements Serializable {
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
