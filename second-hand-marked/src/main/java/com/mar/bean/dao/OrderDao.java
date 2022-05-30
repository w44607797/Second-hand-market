package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.TableField;
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
    private String phone;
    @TableField("trader_no")
    private String traderNo;
    private String consignee;
    @TableField("consignee_tel")
    private String consigneeTel;
    @TableField("delivery_address")
    private String deliveryAddress;
    @TableField("payment_way")
    private String paymentWay;
    @TableField("order_comment")
    private String orderComment;
    @TableField("order_detail_list")
    private String orderDetailList;
    private String orderId;
}
