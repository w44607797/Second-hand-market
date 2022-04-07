package com.mar.controller;

import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.ShoppingCartVO;
import com.mar.service.ShopService;
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
}
