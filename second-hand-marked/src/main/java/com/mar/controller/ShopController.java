package com.mar.controller;

import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.ShoppingCartVO;
import com.mar.exception.TotalException;
import com.mar.service.ShopService;
import com.mar.utils.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/

@RestController
@RequestMapping("/api")
public class ShopController {

    @Autowired
    ShopService shopService;


    @GetMapping("/cart/cartList/{phone}")
    public ResponseResult GetShoppingCartList(@PathVariable String phone){
        ShoppingCartVO[] shoppingCartByPhone = shopService.getShoppingCartByPhone(phone);
        return ResponseResult.success(shoppingCartByPhone);
    }
    @GetMapping("/cart/cartList")
    public ResponseResult GetCartList() throws TotalException {
        ShoppingCartVO[] shoppingCartByPhone = shopService.getShoppingCartByPhone("17759048528");
        return ResponseResult.success(shoppingCartByPhone,"成功");
    }
}
