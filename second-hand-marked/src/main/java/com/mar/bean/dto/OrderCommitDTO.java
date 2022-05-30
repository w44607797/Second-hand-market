package com.mar.bean.dto;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * orderDetailList
 * 提交订单时的购物车商品详情
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCommitDTO {
    private String id;
    private String orderId;
    private String skuId;
    private String skuName;
    private String imgUrl;
    private String orderPrice;
    private String skuNum;

}
