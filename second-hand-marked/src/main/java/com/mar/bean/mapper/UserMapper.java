package com.mar.bean.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/
@Mapper
@Repository
public interface UserMapper {
    String getParamByPhone(Map map);
    int registerUser(@Param("phone")String phone,
                     @Param("password")String password,
                     @Param("salt")String salt);
}
