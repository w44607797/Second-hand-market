package com.mar.bean.mapper;

import com.mar.bean.dto.SpuSaleAttrList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author LBJ
* @description 针对表【spusaleattrlist】的数据库操作Mapper
* @createDate 2022-05-04 23:52:05
* @Entity com.mar.bean.dto.SpuSaleAttrList
*/
public interface SpuSaleAttrListMapper extends BaseMapper<SpuSaleAttrList> {

    List<SpuSaleAttrList> selectBySpuId(Long spuId);
}




