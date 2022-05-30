package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sku_image
 */
@TableName(value ="sku_image")
@Data
public class SkuImage implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long skuId;

    /**
     * 
     */
    private String imgName;

    /**
     * 
     */
    private String imgUrl;

    /**
     * 
     */
    private Long spuImgId;

    /**
     * 
     */
    private String isDefault;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}