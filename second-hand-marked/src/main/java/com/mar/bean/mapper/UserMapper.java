package com.mar.bean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mar.bean.dao.UserDao;
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
public interface UserMapper extends BaseMapper<UserDao> {
    String getParamByPhone(Map map);
    int registerUser(@Param("phone")String phone,
                     @Param("password")String password,
                     @Param("salt")String salt,
                     @Param("name")String name);
    int checkIsRegister(String phone);
}