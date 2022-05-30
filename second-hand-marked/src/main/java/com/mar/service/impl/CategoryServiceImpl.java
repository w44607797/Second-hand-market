package com.mar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mar.bean.dao.Category;
import com.mar.bean.mapper.*;
import com.mar.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> categoryList() {
        //1、先找出所有信息
        List<Category> list = categoryMapper.selectList(null);
        //2、三个层级犹如树形结构
        List<Category> level1 = list.stream().filter(Category ->
                Category.getParentId() == 0
        ).map((level)->{
            level.setCategoryChild(getChildren(level,list));
            return level;
        }).collect(Collectors.toList());
        return level1;
    }

    //递归查找所有层级的商品
    private List<Category> getChildren(Category root, List<Category> tree){
        List<Category> list = tree.stream().filter(Category ->{
            //比较自身id和储存的父id
            return Objects.equals(Category.getParentId(),root.getCid());
        }).map((category) -> {
            category.setCategoryChild(getChildren(category,tree));
            return category;
        }).collect(Collectors.toList());
        return list;
    }
}
