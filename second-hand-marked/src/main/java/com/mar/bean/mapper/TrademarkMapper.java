package com.mar.bean.mapper;

import com.mar.bean.dao.Trademark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author LBJ
* @description 针对表【trademark】的数据库操作Mapper
* @createDate 2022-05-07 13:24:24
* @Entity com.mar.bean.dao.Trademark
*/
public interface TrademarkMapper extends BaseMapper<Trademark> {

    /**
     * 根据id查名字
     * @param tmId
     * @return
     */
    String selectNameById(Long tmId);
}




