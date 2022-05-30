package com.mar.bean.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName trademark
 */
@TableName(value ="trademark")
@Data
@AllArgsConstructor
public class Trademark implements Serializable {
    /**
     * 
     */
    @TableId
    private Long tmId;

    /**
     * 
     */
    private String tmName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}