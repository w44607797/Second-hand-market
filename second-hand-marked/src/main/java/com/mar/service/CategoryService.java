package com.mar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mar.bean.dao.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<Category> categoryList();
}
