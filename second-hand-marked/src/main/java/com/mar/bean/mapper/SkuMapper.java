package com.mar.bean.mapper;

import com.mar.bean.dao.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author LBJ
* @description 针对表【sku】的数据库操作Mapper
* @createDate 2022-05-06 12:26:48
* @Entity com.mar.bean.dao.Sku
*/
public interface SkuMapper extends BaseMapper<Sku> {

    Sku selectBySkuId(Long skuId);

    Sku selectByCategory3Id(Long cid);

    /**
     * 三者兼具
     * @param keyword
     * @param tmId
     * @param cid
     * @return
     */
    List<Sku> searchByAll(String keyword, Long tmId, Long cid);

    /**
     * 根据第三级目录查询
     * @param keyword
     * @param cid
     * @return
     */
    List<Sku> searchByCid(String keyword, Long cid);

    /**
     * 返回需要审核的商品
     * @return
     */
    List<Sku> selectExamine();

    int deleteBySkuId(Long skuId);

    int updateBySkuId(Long skuId);
}




