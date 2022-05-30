package com.mar.service;

import com.mar.bean.vo.OrderCommitVO;
import com.mar.bean.vo.OrderListVO;

/**
 * @author guokaifeng
 * @createDate: 2022/4/11
 **/


public interface OrderService {
    /**
     * 提交订单
     */
    void addOrder(String phone, OrderCommitVO orderListVO);

    OrderListVO getOrderList(String phone);
}
