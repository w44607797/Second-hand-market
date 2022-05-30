package com.mar.controller;

import com.mar.bean.vo.OrderCommitVO;
import com.mar.bean.vo.OrderListVO;
import com.mar.bean.vo.ResponseResult;
import com.mar.exception.TotalException;
import com.mar.service.OrderService;
import com.mar.service.RedisService;
import com.mar.utils.RedisUtils;
import com.mar.utils.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guokaifeng
 * @createDate: 2022/4/19
 **/


@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;


    @PostMapping("/auth/submitOrder")
    public ResponseResult<String> submitOrder(@RequestParam("tradeNo") String tradeNo,
                                              OrderCommitVO orderCommitVO, BindingResult bindingResult,
                                              HttpServletRequest request
    ) throws TotalException {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new TotalException(StateEnum.SERVICE_ERROR_ORDERWRONGFORMAT.getCode(),
                    defaultMessage,StateEnum.SERVICE_ERROR_ORDERWRONGFORMAT.getMessage());
        }
        orderCommitVO.setTraderNo(tradeNo);
        String token = request.getHeader("token");
        String phone = redisService.getUserPhoneByToken(token);
        orderService.addOrder(phone,orderCommitVO);

        return ResponseResult.success();
    }

    @GetMapping("/api/order/auth/{page}/{limit}")
    public ResponseResult getOrderList(@PathVariable("page") String page, @PathVariable("limit")
            String limit, HttpServletRequest request) {
        String token = request.getHeader("token");
        String phone = redisService.getUserPhoneByToken(token);
        OrderListVO orderList = orderService.getOrderList(phone);
        return ResponseResult.success(orderList);

    }


}
