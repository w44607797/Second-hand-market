package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 数据库可以建一个商品目录表
 */

public class Category {

    /**
     * 唯一辨识id
     */
    private Long cid;

    private Integer categoryId;

    private String categoryName;
    /**
     * 父分类id
     */
    private Long parentId;

    /**
     * 该属性在数据库中不存在
     */
    @TableField(exist = false)
    private List<Category> categoryChild;




}

