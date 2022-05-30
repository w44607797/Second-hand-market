package com.mar.service.impl;

import com.mar.bean.dao.Sku;
import com.mar.bean.mapper.SkuMapper;
import com.mar.bean.vo.ResponseResult;
import com.mar.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private SkuMapper skuMapper;

    @Override
    public ResponseResult getAll() {
        List<Sku> list =  skuMapper.selectExamine();
        return ResponseResult.success(list);
    }

    @Override
    public ResponseResult deleteOne(Long skuId) {
        int i = skuMapper.deleteBySkuId(skuId);
        if(i == 1) {
            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.failed("522","删除失败");
        }
    }

    @Override
    public ResponseResult passOne(Long skuId) {
        int i = skuMapper.updateBySkuId(skuId);
        if(i == 1) {
            return ResponseResult.success("审核通过");
        }else {
            return ResponseResult.failed("522","通过失败");
        }
    }
}
