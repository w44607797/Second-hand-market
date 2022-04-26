package com.mar.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/


/**
 * OrderDetailVO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private String id;
    @Min(value = 0,message = "商品的id格式错误")
    @NotEmpty(message = "商品id不能为空")
    private String skuId;
    @NotNull(message = "商品名称不能为空")
    @NotEmpty(message = "商品名称不能为空")
    private String skuName;
    private String imgUrl;
    @Min(value = 0,message = "订单价格有误")
    private String orderPrice;
    @Min(value = 0,message = "商品数量不能小于0")
    private String skuNum;
    private String hasStock;
}
