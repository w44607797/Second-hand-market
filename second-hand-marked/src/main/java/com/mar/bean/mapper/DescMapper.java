package com.mar.bean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mar.bean.dao.Desc;

public interface DescMapper extends BaseMapper<Desc> {
    String selectByDescId(Long descId);
}
