package com.mar.service;

import com.mar.bean.vo.ShoppingCartVO;
import com.mar.exception.TotalException;

import java.util.List;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/

public interface CartService {
    ShoppingCartVO[] getShoppingCartByPhone(String phone);
    void addToShoppingCart(String token,String skuId,String skuNum) throws TotalException;
    void deleteCart(String token,String skuId) throws TotalException;
    void changeCartStatus(String skuId,String isChecked);
}
