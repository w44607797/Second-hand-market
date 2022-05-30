package com.mar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mar.bean.dao.Category;
import com.mar.bean.vo.ResponseResult;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<Category> categoryList();

    /**
     * 功能5，获取详细信息
     * @param skuId
     */
}
