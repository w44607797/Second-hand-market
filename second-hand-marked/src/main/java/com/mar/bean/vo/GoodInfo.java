package com.mar.bean.vo;

import com.mar.bean.dto.SkuInfoDTO;
import lombok.Data;

@Data
public class GoodInfo {
    private CategoryVo categoryView;
    private Long price;
    private SkuInfoDTO skuInfo;


}
