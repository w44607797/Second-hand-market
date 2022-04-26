package com.mar.controller;

import com.mar.bean.vo.ResponseResult;
import com.mar.bean.vo.ShoppingCartVO;
import com.mar.exception.TotalException;
import com.mar.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/

@RestController
@RequestMapping("/api/cart")
public class ShopController {

    @Autowired
    CartService cartService;

    /**
     * 获取购物车列表
     * @param phone
     * @return
     */

    @GetMapping("/cartList/{phone}")
    public ResponseResult GetShoppingCartList(@PathVariable String phone){
        ShoppingCartVO[] shoppingCartByPhone = cartService.getShoppingCartByPhone(phone);
        return ResponseResult.success(shoppingCartByPhone,"成功");
    }

    /**
     * 删除购物车
     * @return
     */
    @DeleteMapping("/deleteCart/{skuId}")
    public ResponseResult deleteCart(@PathVariable("skuId")String skuId,HttpServletRequest request) throws TotalException {
        String token = request.getHeader("token");
        cartService.deleteCart(token,skuId);
        return ResponseResult.success();
    }

    /**
     * 添加商品到购物车
     * @param skuId
     * @param number
     * @param request
     * @return
     * @throws TotalException
     */
    @PostMapping("/addToCart/{skuId}/{skuNum}")
    public ResponseResult addCommodityToCart(@PathVariable("skuId")String skuId,@PathVariable("skuNum")String number,HttpServletRequest request) throws TotalException {
        String token = request.getHeader("token");
        cartService.addToShoppingCart(token,skuId,number);
        return ResponseResult.success();

    }

    /**
     * 切换购物车选择状态
     * @return
     */
    @GetMapping("/checkCart/{skuId}/{isChecked}")
    public ResponseResult changeChartStatus(@PathVariable("skuId") String skuId,
                                            @PathVariable("isChecked") String isChecked,
                                            HttpServletRequest request){
        String phone = request.getHeader("token");
        cartService.changeCartStatus(skuId,isChecked);
        return ResponseResult.success();
    }


}
