package com.mar.bean.dto;

import lombok.Data;

@Data
public class SpuSaleAttrValueDTO {
    //自增
    private Long id;
    private Long spuId;
    private Long baseSaleAttrId;
    private String saleAttrValueName;
    private String saleAttrName;
    private Integer isChecked;

}
