package com.mar.bean.dto;

import com.mar.bean.dao.SkuAttrValue;
import com.mar.bean.dao.SkuImage;
import lombok.Data;

import java.util.List;

@Data
public class SkuInfoDTO {
    //private Long id;
    //private Long spuId;
    //private String price;

    /**
     * 库存
     */
    //private Long stock;
    private String skuName;
    private String skudesc;
   // private String weight;
    //private Long tmId;
   // private Long category3Id;
    private String resource;
    private String skuDefaulting;
    //private String isSale;
    //private List<SkuImage> skuImageList;
   // private List<SkuAttrValue> skuAttrValueList;
   // private List<SkuSaleAttrValue> skuSaleAttrValueList;

    //返回null的skuSaleAttrValueList没有加入
}
