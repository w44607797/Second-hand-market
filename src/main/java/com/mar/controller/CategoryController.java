package com.mar.controller;

import com.mar.bean.dao.Category;
import com.mar.bean.vo.ResponseResult;
import com.mar.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/product/getBaseCategoryList")
    public ResponseResult list(){
        List<Category> list = categoryService.categoryList();
        return new ResponseResult("200","成功",list,true);
    }
}
