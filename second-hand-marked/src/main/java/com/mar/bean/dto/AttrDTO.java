package com.mar.bean.dto;

import com.mar.bean.dao.AttrValue;
import lombok.Data;

import java.util.List;

@Data
public class AttrDTO {

    private Long attrId;

    private String attrName;

    private List<AttrValue> attrValueList;
}
