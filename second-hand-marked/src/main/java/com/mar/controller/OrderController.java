package com.mar.controller;

import com.mar.bean.vo.ResponseResult;
import com.mar.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guokaifeng
 * @createDate: 2022/4/19
 **/


@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    RedisUtils redisUtils;


    @PostMapping("/auth/submitOrder")
    public ResponseResult<String> submitOrder(@RequestParam("tradeNo")String tradeNo){



        return ResponseResult.success();
    }
}
