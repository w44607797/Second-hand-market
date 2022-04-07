package com.mar.bean.mapper;

import com.mar.bean.dao.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/4/7
 **/
@Mapper
@Repository
public interface CommodityMapper {
    Commodity getCommodityByParam(Map<String,Object> map);
}
