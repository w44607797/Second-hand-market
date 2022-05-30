package com.mar.service.impl;

import com.mar.bean.dao.*;
import com.mar.bean.dto.SkuInfoDTO;
import com.mar.bean.dto.SpuSaleAttrList;
import com.mar.bean.dto.SpuSaleAttrValueDTO;
import com.mar.bean.mapper.*;
import com.mar.bean.vo.CategoryVo;
import com.mar.bean.vo.DetailsVo;
import com.mar.bean.vo.ResponseResult;
import com.mar.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private SpuSaleAttrListMapper spuSaleAttrListMapper;
    @Resource
    private SpuSaleAttrValue spuSaleAttrValueMapper;
    @Resource
    private SkuMapper skuMapper;
    @Resource
    private SkuImageMapper skuImageMapper;
    @Resource
    private SkuAttrValueMapper skuAttrValueMapper;
    @Resource
    private DescMapper descMapper;

    @Override
    public ResponseResult getDetailsInfo(String skuId) {
        //数据库实体
        Sku sku = skuMapper.selectBySkuId(Long.valueOf(skuId));
        //返回给视图层对象
        DetailsVo detailsVo = new DetailsVo();
        detailsVo.setPrice(sku.getPrice());
        //获取categoryView
        detailsVo.setCategoryView(categoryView(sku.getCategory3Id()));
        //获取spu相关属性
       // detailsVo.setSpuSaleAttrList(getSaleAttrList(sku.getSpuId()));
        //获取skuInfo属性
        detailsVo.setSkuInfo(getSkuInfo(sku));
        System.out.println("DetailsVo:"+detailsVo);
        return ResponseResult.success(detailsVo);
    }

    private SkuInfoDTO getSkuInfo(Sku sku) {
        SkuInfoDTO skuInfoDTO = new SkuInfoDTO();
        //拷贝相同名字的属性
        BeanUtils.copyProperties(sku,skuInfoDTO);
        //获得商品简述

        //图片列表
       // skuInfoDTO.setSkuImageList(getImageList(sku.getSkuId()));
        //属性列表
       // skuInfoDTO.setSkuAttrValueList(getSkuAttrValueList(sku.getSkuId()));
        return skuInfoDTO;
    }

    private List<SkuImage> getImageList(Long skuId){
        return skuImageMapper.selectBySkuId(skuId);
    }

    private List<SkuAttrValue> getSkuAttrValueList(Long skuId){
        return skuAttrValueMapper.selectBySkuId(skuId);
    }

    /**
     * 根据三级目录ID，得到三层目录
     * @param category3Id
     * @return
     */
    private CategoryVo categoryView(Long category3Id){
        CategoryVo categoryVo = new CategoryVo();
        Category category3 = categoryMapper.selectById(category3Id);
        Category category2 = categoryMapper.selectById(category3.getParentId());
        Category category1 = categoryMapper.selectById(category2.getParentId());
        categoryVo.setCategory1Id(category1.getCid());
        categoryVo.setCategory1Name(category1.getCategoryName());
        categoryVo.setCategory2Id(category2.getCid());
        categoryVo.setCategory2Name(category2.getCategoryName());
        categoryVo.setCategory3Id(category3.getCid());
        categoryVo.setCategory3Name(category3.getCategoryName());
        return categoryVo;
    }

    //获取spu属性列表
    private List<SpuSaleAttrList> getSaleAttrList(Long spuId){
        //根据spuId查找
        List<SpuSaleAttrList> spuSaleAttrLists = spuSaleAttrListMapper.selectBySpuId(spuId);
        //获得了基本属性，再根据spuId和baseSaleAttrId查找具体选项
        for(SpuSaleAttrList s:spuSaleAttrLists){
            s.setSpuSaleAttrValueList(getSaleAttrValue(s));
        }
        return spuSaleAttrLists;
    }

    //获取spu属性
    private List<SpuSaleAttrValueDTO> getSaleAttrValue(SpuSaleAttrList s){
        List<SpuSaleAttrValueDTO> list = spuSaleAttrValueMapper.selectBySpuIdAndAttrId(s.getSpuId(),s.getBaseSaleAttrId());
        for(SpuSaleAttrValueDTO value:list){
            value.setSaleAttrName(s.getSaleAttrName());
        }
        return list;
    }


}
