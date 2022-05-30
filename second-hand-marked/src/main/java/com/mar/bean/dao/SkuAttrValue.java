package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sku_attrvalue
 */
@TableName(value ="sku_attr_value")
@Data
public class SkuAttrValue implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long attrid;

    /**
     * 
     */
    private Long valueid;

    /**
     * 
     */
    private Long skuid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}