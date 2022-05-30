package com.mar.service;

import com.mar.bean.vo.ResponseResult;

public interface ItemService {
    /**
     * 完成功能5，商品详情
     * @param skuId
     * @return
     */
    ResponseResult getDetailsInfo(String skuId);
}
