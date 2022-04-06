package com.mar.bean.vo;

import com.mar.bean.dto.OrderCommitDTO;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/

public class OrderListVO {
    private String id;
    private String consignee;
    private String consigneeTel;
    private String totalAmount;
    private String orderStatus;
    private String userId;
    private String paymentWay;
    private String deliveryAddress;
    private String orderComment;
    private String outTradeNo;
    private String tradeBody;
    private String createTime;
    private String expireTime;
    private String processStatus;
    private String trackingNo;
    private String parentOrderId;
    private String imgUrl;
    private OrderCommitDTO[] orderDetailList;
    private String orderStatusName;
    private String wareId;
}
