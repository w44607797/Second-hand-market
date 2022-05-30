package com.mar.service.impl;

import com.mar.bean.dao.OrderDao;
import com.mar.bean.mapper.OrderMapper;
import com.mar.bean.vo.OrderCommitVO;
import com.mar.bean.vo.OrderListVO;
import com.mar.service.CartService;
import com.mar.service.OrderService;
import com.mar.service.RedisService;
import com.mar.utils.RedisUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/4/19
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RedisService redisService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    Mapper dozerMapper;

    @Override
    public void addOrder(String phone, OrderCommitVO orderListVO) {
        OrderDao map = dozerMapper.map(orderListVO, OrderDao.class);
        map.setPhone(phone);
        orderMapper.insert(map);

    }

    @Override
    public OrderListVO getOrderList(String phone) {
        OrderDao orderDao = orderMapper.selectById(phone);
        OrderListVO map = dozerMapper.map(orderDao, OrderListVO.class);
        return map;
    }
}
