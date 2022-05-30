package com.mar.bean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mar.bean.dao.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 方便查找而写的查询
     */
    List<Category> mySelectList(long cid);

    /**
     * 根据Cid查找名字
     * @param cid
     * @return
     */
    String selectNameByCid(long cid);
}
