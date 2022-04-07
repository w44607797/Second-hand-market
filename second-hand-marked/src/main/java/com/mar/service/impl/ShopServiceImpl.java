package com.mar.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.mar.bean.dao.ShopCartDAO;
import com.mar.bean.mapper.ShopMapper;
import com.mar.bean.vo.ShoppingCartVO;
import com.mar.exception.TotalException;
import com.mar.service.ShopService;
import com.mar.utils.DozerUtils;
import com.mar.utils.JWTUtil;
import com.mar.utils.RedisUtils;
import com.mar.utils.StateEnum;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    Mapper dozerMapper;

    @Autowired
    RedisUtils redisUtils;


    @Override
    public synchronized ShoppingCartVO[] getShoppingCartByPhone(String phone) {
        Boolean aBoolean = redisUtils.hasKey(phone + ":cart");
        if(!aBoolean){
            return null;
        }



        List<ShopCartDAO> shopCartListByPhone = shopMapper.getShopCartListByPhone(phone);
        ShoppingCartVO[] shoppingCartVOS = new ShoppingCartVO[shopCartListByPhone.size()];
        int temp = 0;
        for(ShopCartDAO shopCartDAO : shopCartListByPhone){
            shoppingCartVOS[temp++] = dozerMapper.map(shopCartDAO,ShoppingCartVO.class);
        }
        return shoppingCartVOS;
    }

    @Override
    public synchronized ShoppingCartVO[] getShoppingCartByChecked(String isChecked) {
        List<ShopCartDAO> shopCartListByChecked = shopMapper.getShopCartListByChecked(isChecked);
        ShoppingCartVO[] shoppingCartVOS = new ShoppingCartVO[shopCartListByChecked.size()];
        int temp = 0;
        for(ShopCartDAO shopCartDAO : shopCartListByChecked){
            shoppingCartVOS[temp++] = dozerMapper.map(shopCartDAO,ShoppingCartVO.class);
        }
        return shoppingCartVOS;
    }

    @Override
    public void addToShoppingCart(String token,String skuId,String skuNum) throws TotalException {
        String phone = JWTUtil.getPhone(token);
        String key = phone+":cart";
        if(!redisUtils.hasKey(key)){
            redisUtils.hPut(key,skuId,skuNum);
            return;
        }
        Map<Object,Object> objectObjectMap = redisUtils.hGetAll(skuId);
        long num = Long.parseLong(skuNum);
        if(objectObjectMap.containsKey(skuId)){
            redisUtils.hIncrBy(key,skuId,num);
            return;
        }
        if(num<=0){
            TotalException totalException = new TotalException(StateEnum.SHOP_ERROR_FAILTOADDCART.getMessage());
            totalException.setCode(StateEnum.SHOP_ERROR_FAILTOADDCART.getCode());
            throw totalException;
        }
        redisUtils.hPut(key,skuId,skuNum);
    }
}
