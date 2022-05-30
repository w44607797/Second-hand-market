package com.mar.service;

import com.mar.bean.vo.ResponseResult;

public interface AdminService {
    ResponseResult getAll();

    ResponseResult deleteOne(Long skuId);

    ResponseResult passOne(Long skuId);

}
