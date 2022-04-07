package com.mar.service;

import com.mar.bean.vo.ShoppingCartVO;

import java.util.List;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/

public interface ShopService {
    ShoppingCartVO[] getShoppingCartByPhone(String phone);
    ShoppingCartVO[] getShoppingCartByChecked(String isChecked);
    void addToShoppingCart(String token,String skuId,String skuNum);
}
