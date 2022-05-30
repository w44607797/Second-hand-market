package com.mar.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mar.bean.dao.*;
import com.mar.bean.dto.AttrDTO;
import com.mar.bean.dto.GoodDTO;
import com.mar.bean.mapper.*;
import com.mar.bean.param.ListParam;
import com.mar.bean.vo.ListVo;
import com.mar.bean.vo.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListService {

    @Resource
    private SkuMapper skuMapper;
    @Resource
    private AttrMapper attrMapper;
    @Resource
    private AttrValueMapper attrValueMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private TrademarkMapper trademarkMapper;


    public ResponseResult list(ListParam listParam) {
        //放入标签列表
        ListVo listVo = new ListVo();
        System.out.println(listParam);
        //转换trademark
        if(listParam.getTrademark() != null){
            listVo.setTrademarkList(getTrademark(listParam));
        }
        //得到attrsList
        if(listParam.getProps() != null) {
            listVo.setAttrList(getAttrsList());
        }
        //得到goodsList，判断逻辑有点麻烦
        listVo.setGoodsList(getGoodsList(listParam));
        listVo.setTotal((long) listVo.getGoodsList().size());
        System.out.println(listVo);
        return ResponseResult.success(listVo);
    }

    /**
     * 看起来只有一个品牌，就写的比较草率了
     * @param listParam
     */
    public List<Trademark> getTrademark(ListParam listParam){
        String t = listParam.getTrademark();
        Long tmId = Long.valueOf(t.substring(0,t.indexOf(':')));
        String tmName = t.substring(t.indexOf(':')+1);
        List<Trademark> trademarkList = new ArrayList<>();
        trademarkList.add(new Trademark(tmId,tmName));
        return trademarkList;
    }

    public List<AttrDTO> getAttrsList(){
        List<AttrDTO> list = new ArrayList<>();
        //DTO先赋值俩基本属性，另一个List根据基本属性再次查询
        List<Attr> tempList = attrMapper.selectList(null);
        for (Attr attr : tempList) {
            AttrDTO attrDTO = new AttrDTO();
            BeanUtils.copyProperties(attr, attrDTO);
            attrDTO.setAttrValueList(getAttrValue(attrDTO.getAttrId()));
            list.add(attrDTO);
        }
        return list;
    }

    /**
     * 根据valueId查找对应数据
     * @param valueId
     * @return
     */
    public List<AttrValue> getAttrValue(Long valueId){
        LambdaQueryWrapper<AttrValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttrValue::getValueId,valueId);
        return attrValueMapper.selectList(queryWrapper);
    }

    private List<GoodDTO> getGoodsList(ListParam param){
        /**
         * 1、判断order，是否需要排序(说是不用了)
         * 2、判断keyword，如果有keyword，后续不用再管
         * 3、再判断trademark是否有传入
         * 4、判断categoryId，Name不用管
         * 情况多且杂，自己写一条sql比较实在
         */
        List<GoodDTO> goodDTOList = new ArrayList<>();
        List<Sku> skuList = new ArrayList<>();
        String keyword = param.getKeyword();
        if(keyword == null){
            keyword = "";
        }
        System.out.println("This is key:"+keyword);
        String trademark = param.getTrademark();
        String category1Id = param.getCategory1Id();
        String category2Id = param.getCategory2Id();
        String category3Id = param.getCategory3Id();
        Long tmId = null;
        if(param.getTrademark() != null){
            tmId = Long.valueOf(trademark.substring(0,trademark.indexOf(':')));
        }
        if(category3Id != null){
            if(tmId != null){
                skuList = skuMapper.searchByAll(keyword, tmId, Long.parseLong(category3Id));
            }else {
                skuList = skuMapper.searchByCid(keyword,Long.parseLong(category3Id));
            }
            //包装转化
            transfer(skuList, goodDTOList);
            return goodDTOList;
        }

        if(category2Id != null){
            QueryWrapper<Category> wrapper = new QueryWrapper<>();
            wrapper.eq("parentId",Long.parseLong(category2Id));
            List<Category> categories = categoryMapper.selectList(wrapper);
            if(tmId!=null){
                for (Category category : categories) {
                    skuList = skuMapper.searchByAll(keyword, tmId, category.getCid());
                }
            }else {
                for (Category category : categories) {
                    skuList = skuMapper.searchByCid(keyword, category.getCid());
                }
            }
            //包装转化
            transfer(skuList, goodDTOList);
            return goodDTOList;
        }

        if(category1Id != null){
            //先获取2级目录，再获取3级
            List<Category> categories2 = categoryMapper.mySelectList(Long.parseLong(category1Id));
            for (Category value : categories2) {
                QueryWrapper<Category> wrapper = new QueryWrapper<>();
                wrapper.eq("parent_Id", value.getCid());
                List<Category> categories = categoryMapper.selectList(wrapper);
                if (tmId != null) {
                    for (Category category : categories) {
                        skuList = skuMapper.searchByAll(keyword, tmId, category.getCid());
                    }
                } else {
                    for (Category category : categories) {
                        skuList = skuMapper.searchByCid(keyword, category.getCid());
                    }
                }
            }
            transfer(skuList, goodDTOList);
            return goodDTOList;
        }

       // List<Sku> skuList = skuMapper.search(keyword,null,category3Id);
        return goodDTOList;
    }

    private List<GoodDTO> transfer(List<Sku> skuList,List<GoodDTO> goodDTOList){
        for(Sku sku:skuList){
            GoodDTO goodDTO = new GoodDTO();
            BeanUtils.copyProperties(sku,goodDTO);
            //这里偷懒，只找了第三级目录的名称
            if(goodDTO.getCategory3Id() != null) {
                goodDTO.setCategory3Name(categoryMapper.selectNameByCid(Long.parseLong(goodDTO.getCategory3Id())));
            }
            if(goodDTO.getTmId() != null) {
                goodDTO.setTmName(trademarkMapper.selectNameById(goodDTO.getTmId()));
            }
            goodDTOList.add(goodDTO);
        }
        return goodDTOList;
    }
}
