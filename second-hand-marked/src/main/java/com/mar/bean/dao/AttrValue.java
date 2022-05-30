package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sku_value
 */
@TableName(value ="sku_value")
@Data
public class AttrValue implements Serializable {
    /**
     * 
     */
    private Long attrId;

    /**
     * 
     */
    private Long valueId;

    /**
     * 
     */
    private String attrValue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}