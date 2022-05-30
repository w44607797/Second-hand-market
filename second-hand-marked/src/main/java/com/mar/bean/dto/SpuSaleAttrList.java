package com.mar.bean.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName spusaleattrlist
 */
@TableName(value ="spusaleattrlist")
@Data
public class SpuSaleAttrList implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long spuId;

    /**
     * 
     */
    private Long baseSaleAttrId;

    /**
     * 
     */
    private String saleAttrName;

    /**
     * 该属性在数据库中不存在
     */
    @TableField(exist = false)
    private List<SpuSaleAttrValueDTO> spuSaleAttrValueList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}