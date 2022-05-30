package com.mar.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDTO {
    private Long id;
    private String defaultImg;
    //就是sku的skuName
    private String title;
    private String price;
    private String createTime;
    private Long tmId;
    private String tmName;
    private String category1Id;
    private String category1Name;
    private String category2Id;
    private String category2Name;
    private String category3Id;
    private String category3Name;
    private Long hotScore;
    private String attrs;
}
