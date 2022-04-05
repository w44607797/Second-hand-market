package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author guokaifeng
 * @createDate: 2022/4/5
 **/

@TableName("Order_Dao")
public class OrderDao {
    private String traderNo;
    private String consignee;
    private String consigneeTel;
    private String deliveryAddress;
    private String paymentWay;
    private String orderComment;
    private String orderDetailList;
    private String orderId;
    private String page;
    private String limit;
}
