package com.mar.bean.vo;

import com.mar.bean.dto.SkuInfoDTO;
import com.mar.bean.dto.SpuSaleAttrList;
import lombok.Data;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

@Data
public class DetailsVo {
    private String price;
    private CategoryVo categoryView;
    //private List<SpuSaleAttrList> spuSaleAttrList;
    private SkuInfoDTO skuInfo;

}
