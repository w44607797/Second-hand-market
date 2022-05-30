package com.mar.bean.vo;

import com.mar.bean.dto.OrderCommitDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author guokaifeng
 * @createDate: 2022/4/25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCommitVO {
    private String traderNo;
    @NotEmpty(message = "收件人姓名不能为空")
    @NotNull(message = "收件人姓名不能为空")
    private String consignee;
    @NotEmpty(message = "收件人电话不能为空")
    @NotNull(message = "收件人电话不能为空")
    private String consigneeTel;
    @NotEmpty(message = "收件地址不能为空")
    @NotNull(message = "收件地址不能为空")
    private String deliveryAddress;
    private String paymentWay;
    private String orderComment;
    private OrderCommitDTO[] orderDetailList;

}
