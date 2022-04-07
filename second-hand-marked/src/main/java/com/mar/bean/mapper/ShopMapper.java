package com.mar.bean.mapper;

import com.mar.bean.dao.Commodity;
import com.mar.bean.dao.ShopCartDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/
@Mapper
@Repository
public interface ShopMapper {
    List<ShopCartDAO> getShopCartListByPhone(@Param("userId")String phone);
    List<ShopCartDAO> getShopCartListByChecked(@Param("isChecked")String status);

}
