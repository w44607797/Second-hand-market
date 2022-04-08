package com.guo;

import com.mar.MainApplication;
import com.mar.bean.vo.UserLoginVO;
import com.mar.exception.DatabaseException;
import com.mar.exception.TotalException;
import com.mar.service.RedisService;
import com.mar.service.UserService;
import com.mar.utils.RedisUtils;
import com.mar.utils.StateEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/4/5
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class TotalTest {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void demoException() throws DatabaseException, TotalException {
        System.out.println("开始抛异常");
        throw new TotalException(StateEnum.SHOP_ERROR_FAILTOADDCART.getCode(),
                StateEnum.SHOP_ERROR_FAILTOADDCART.getMessage(),
                StateEnum.SHOP_ERROR_FAILTOADDCART.getMessage());
    }

    @Test
    public void demoLogin(){
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setPhone("1362106037");


    }
    @Test
    public void setCode(){
        redisService.storeCode("17759048528","1111");
    }

    @Test
    public void getAllCart(){
        Map<Object, Object> objectObjectMap = redisUtils.hGetAll("17759048528:cart");

    }
    @Test
    public void getMapKey(){
        Map<Object, Object> skuIdMap = redisUtils.hGetAll("17759048528:cart");
        for (Object o : skuIdMap.keySet()) {
            System.out.println((String) o);
            int o1 = Integer.parseInt((String) skuIdMap.get(o));
            System.out.println(o1);

        }

    }

}
