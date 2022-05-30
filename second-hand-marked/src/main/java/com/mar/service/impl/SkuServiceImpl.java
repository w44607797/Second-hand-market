package com.mar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mar.bean.dao.Sku;
import com.mar.bean.mapper.SkuMapper;
import com.mar.service.SkuService;
import org.springframework.stereotype.Service;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {
}
