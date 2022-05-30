package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sku_attr
 */
@TableName(value ="sku_attr")
@Data
public class Attr implements Serializable {
    /**
     * 
     */
    @TableId
    private Long attrId;

    /**
     * 
     */
    private String attrName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}