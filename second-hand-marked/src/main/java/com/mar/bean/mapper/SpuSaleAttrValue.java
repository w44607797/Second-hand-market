package com.mar.bean.mapper;

import com.mar.bean.dto.SpuSaleAttrValueDTO;

import java.util.List;

public interface SpuSaleAttrValue {
    List<SpuSaleAttrValueDTO> selectBySpuIdAndAttrId(Long spuId, Long baseSaleAttrId);
}
