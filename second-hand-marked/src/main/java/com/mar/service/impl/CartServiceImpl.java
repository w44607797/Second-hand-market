package com.mar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mar.bean.dao.Commodity;
import com.mar.bean.doo.CommodityShopDO;
import com.mar.bean.mapper.CommodityMapper;
import com.mar.bean.vo.ShoppingCartVO;
import com.mar.exception.TotalException;
import com.mar.service.CartService;
import com.mar.service.RedisService;
import com.mar.utils.JWTUtil;
import com.mar.utils.RedisUtils;
import com.mar.utils.StateEnum;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author guokaifeng
 * @createDate: 2022/4/6
 **/
@Service
public class CartServiceImpl implements CartService {


    @Autowired
    Mapper dozerMapper;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RedisService redisService;

    @Autowired
    CommodityMapper commodityMapper;


    @Override
    public synchronized ShoppingCartVO[] getShoppingCartByPhone(String phone) {
        String key = phone + ":cart";
        Boolean aBoolean = redisUtils.hasKey(key);
        if(!aBoolean){
            return null;
        }
        /**
         * 在redis获取商品id
         * 在数据库查询商品信息
         */

        Map<Object, Object> skuIdMap = redisUtils.hGetAll(key);
        Map<String,Object> map = new HashMap<>();
        map.put("condition","sku_id,sku_num,sku_price,sku_name,img_url");
        Commodity[] commodities = new Commodity[skuIdMap.size()];
        ShoppingCartVO[] shoppingCartVOS = new ShoppingCartVO[skuIdMap.size()];
        int index = 0;
        int totalPrice = 0;
        for (Object o : skuIdMap.keySet()) {
            int cartPrice = 0;
            map.put("skuId",o);
            int num = Integer.parseInt((String) skuIdMap.get(o));
            Commodity commodity = commodityMapper.getCommodityByParam(map);
            if(commodity==null){
                continue;
            }
            /**
             * 实体类之间的转换
             */
            int skuPrice = Integer.parseInt(commodity.getSkuPrice());
            cartPrice += skuPrice*num;
            totalPrice += cartPrice;
            commodities[index] = commodity;
            CommodityShopDO commodityShopDO = dozerMapper.map(commodity, CommodityShopDO.class);
            commodityShopDO.setCartPrice(String.valueOf(cartPrice));
            commodityShopDO.setUserId(phone);
            commodityShopDO.setQuantity(String.valueOf(num));
            shoppingCartVOS[index] = dozerMapper.map(commodityShopDO,ShoppingCartVO.class);
            index++;
        }
//        List<ShopCartDAO> shopCartListByPhone = shopMapper.getShopCartListByPhone(phone);
//        ShoppingCartVO[] shoppingCartVOS = new ShoppingCartVO[shopCartListByPhone.size()];
//        int temp = 0;
//        for(ShopCartDAO shopCartDAO : shopCartListByPhone){
//            shoppingCartVOS[temp++] = dozerMapper.map(shopCartDAO,ShoppingCartVO.class);
//        }
//        return shoppingCartVOS;
        return shoppingCartVOS;
    }

    @Override
    public void addToShoppingCart(String token,String skuId,String skuNum) throws TotalException {
        String phone = redisService.getUserPhoneByToken(token);
        /**
         * key格式 phone:cart
         */

        /**
         * 若用户没有购物车，生成一个
         *
         */
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
            throw new TotalException(StateEnum.SHOP_ERROR_FAILTOADDCART.getCode(),
                    StateEnum.SHOP_ERROR_FAILTOADDCART.getMessage(),
                    StateEnum.SHOP_ERROR_FAILTOADDCART.getMessage());
        }
        redisUtils.hPut(key,skuId,skuNum);
    }

    /**
     * 删除对应购物车
     * @param token
     * @param skuId
     * @throws TotalException
     */

    @Override
    public void deleteCart(String token,String skuId) throws TotalException {
        String phone = redisService.getUserPhoneByToken(token);
        String hkey = phone+":cart";
        if(redisUtils.hasKey(hkey)){
            redisUtils.hDelete(hkey,skuId);
        }else {
            throw new TotalException(StateEnum.SHOP_ERROR_NOSUCHCART.getCode(),
                    StateEnum.SHOP_ERROR_NOSUCHCART.getMessage(),
                    StateEnum.SHOP_ERROR_NOSUCHCART.getMessage());
        }
    }

    /**
     * 修改购物车选中状态
     * key:phone:cart:skuId
     *
     * @param phone
     * @param skuId
     * @param isChecked
     */

    @Override
    public void changeCartStatus(String phone,String skuId, String isChecked) {
        String key = phone+":cart:"+skuId;
        redisUtils.hPut(key,"status",isChecked);

    }
}
