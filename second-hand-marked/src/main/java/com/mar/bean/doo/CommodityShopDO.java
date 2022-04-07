package com.mar.bean.doo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guokaifeng
 * @createDate: 2022/4/7
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityShopDO {
    private String id;
    private String skuId;
    private String userId;
    private String skuNum;
    private String trademark;
    private String imgUrl;
    private String isChecked;
    private String skuPrice;

}
