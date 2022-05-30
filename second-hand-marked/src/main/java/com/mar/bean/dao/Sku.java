package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sku
 */
@TableName(value ="sku")
@Data
public class Sku implements Serializable {

    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO)
    private Long skuId;

    /**
     * 
     */
    private Long spuId;

    /**
     * 
     */
    private Long category3Id;

    /**
     * 
     */
    private Long stock;

    /**
     * 
     */
    @TableField
    private String price;

    /**
     * 
     */
    private String skuDefaultImg;

    /**
     * 
     */
    private String skuName;

    /**
     * 
     */
    private String weight;

    /**
     * 
     */
    private String skudesc;

    /**
     * 
     */
    private Long tmId;

    /**
     *
     */
    private String resource;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Long status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}