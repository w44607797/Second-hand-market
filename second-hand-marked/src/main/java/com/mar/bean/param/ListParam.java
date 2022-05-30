package com.mar.bean.param;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListParam {
    private String category1Id;
    private String category2Id;
    private String category3Id;
    private String categoryName;
    private String keyword;
    private String[] props;
    private String trademark;
    private String order;
    private Integer pageNo;
    private Integer pageSize;

}
