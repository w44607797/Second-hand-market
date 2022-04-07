package com.mar.bean.mapper;

import com.mar.bean.dao.Commodity;

import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/4/7
 **/

public interface CommodityMapper {
    Commodity getCommodityByParam(Map<String,Object> map);
}
