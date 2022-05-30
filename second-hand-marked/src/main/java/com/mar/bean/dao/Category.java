package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName category
 */
@TableName(value ="category")
@Data
public class Category implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long cid;

    /**
     * 
     */
    private Integer categoryId;

    /**
     * 
     */
    private String categoryName;

    /**
     * 
     */
    private Long parentId;

    /**
     * 该属性在数据库中不存在
     */
    @TableField(exist = false)
    private List<Category> categoryChild;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}