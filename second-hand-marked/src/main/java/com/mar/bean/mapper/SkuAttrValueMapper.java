package com.mar.bean.mapper;

import com.mar.bean.dao.SkuAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author LBJ
* @description 针对表【sku_attrvalue】的数据库操作Mapper
* @createDate 2022-05-06 10:11:48
* @Entity com.mar.bean.dao.SkuAttrValue
*/
public interface SkuAttrValueMapper extends BaseMapper<SkuAttrValue> {

    List<SkuAttrValue> selectBySkuId(Long skuId);
}




