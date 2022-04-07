package com.guo;

import com.mar.MainApplication;
import com.mar.bean.vo.UserLoginVO;
import com.mar.exception.DatabaseException;
import com.mar.service.RedisService;
import com.mar.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

//    @Test
//    public void demoException() throws DatabaseException {
//        System.out.println("开始抛异常");
//        throw new DatabaseException(ExceptionUtil.DATABASE_MESSAGE,ExceptionUtil.DATABASE_MESSAGE_USER);
//    }

    @Test
    public void demoLogin(){
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setPhone("1362106037");


    }
    @Test
    public void setCode(){
        redisService.storeCode("17759048528","1111");
    }

}
