package com.mar.bean.mapper;

import com.mar.bean.dao.SkuImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author LBJ
* @description 针对表【sku_image】的数据库操作Mapper
* @createDate 2022-05-06 10:04:50
* @Entity com.mar.bean.dao.SkuImage
*/
public interface SkuImageMapper extends BaseMapper<SkuImage> {

    List<SkuImage> selectBySkuId(Long skuId);
}




