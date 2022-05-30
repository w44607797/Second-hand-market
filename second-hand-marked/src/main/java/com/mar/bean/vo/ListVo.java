package com.mar.bean.vo;

import com.mar.bean.dao.Trademark;
import com.mar.bean.dto.AttrDTO;
import com.mar.bean.dto.GoodDTO;
import lombok.Data;

import java.util.List;

@Data
public class ListVo {
    private List<Trademark> trademarkList;
    private List<AttrDTO> attrList;
    private List<GoodDTO> goodsList;
    private Long total;
    private Long pageSize;
    private Long pageNo;
    private Long totalPages;
}
