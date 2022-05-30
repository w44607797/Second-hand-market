package com.mar.bean.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author guokaifeng
 * @createDate: 2022/4/5
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String skuId;
    private String skuNum;
    private String skuPrice;
    private String skuName;
    private String categoryName;
    private String category1Id;
    private String category1Name;
    private String category2Id;
    private String category2Name;
    private String category3Id;
    private String category3Name;
    private String keyword;
    private String props;
    private String trademark;
    private String imgUrl;
    private String order;
    private Integer pageNo;
    private Integer pageSize;
}
