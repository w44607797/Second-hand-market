package com.mar.bean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mar.bean.dao.Commodity;
import com.mar.bean.dao.UserDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/4/7
 **/
@Mapper
@Repository
public interface CommodityMapper extends BaseMapper<UserDao> {
    Commodity getCommodityByParam(Map<String,Object> map);

}
