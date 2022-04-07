package com.mar.bean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mar.bean.dao.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

}
