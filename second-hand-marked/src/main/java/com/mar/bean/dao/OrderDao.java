package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author guokaifeng
 * @createDate: 2022/4/5
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Order_Dao")
public class OrderDao implements Serializable {
    private static final long serialVersionUID = 1L;
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
