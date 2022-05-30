package com.mar.bean.mapper;

import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mar.bean.dao.Category;
import com.mar.bean.dao.OrderDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<OrderDao> {


}
