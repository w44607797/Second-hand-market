package com.guo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mar.MainApplication;
import com.mar.bean.dao.UserDao;
import com.mar.bean.mapper.UserMapper;
import com.mar.bean.vo.UserLoginVO;
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

import java.sql.Wrapper;
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
    UserMapper userMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void demoException() throws TotalException {
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
    @Test
    public void TextMybatisPlus(){
        UserDao userDao = userMapper.selectById("17759048528");
        System.out.println(userDao);
        UserDao dao = new UserDao();
        dao.setPhone("17759048528");
        boolean exists = userMapper.exists(null);
        System.out.println(exists);

    }

    @Test
    public void demoUserService(){
        System.out.println(userService.count());


    }
    @Test
    public void demoUserLogin(){
        UserDao userDao = new UserDao();
        userDao.setName("nog");
        userDao.setSalt("aaa");
        userDao.setPassword("ddd");
        userDao.setPermission("admin");
        userService.save(userDao);
    }
    @Test
    public void demoplus(){
        QueryWrapper<UserDao> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone","17759048528");
        queryWrapper.select("permission");
        System.out.println(userMapper.selectOne(queryWrapper));
    }
    @Test
    public void demoplus2(){
        QueryWrapper<UserDao> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("password", "salt","headshot");
        queryWrapper.eq("phone","17759048528");
        UserDao userDao = userMapper.selectOne(queryWrapper);
        String password = userDao.getPassword();
        String salt = userDao.getSalt();
        System.out.println(password);
        System.out.println(salt);
        queryWrapper.clear();
        queryWrapper.select("password", "salt");
        queryWrapper.eq("phone","15880411165");
        UserDao userDao1 = userMapper.selectOne(queryWrapper);
        System.out.println(userDao1);
    }

    @Test
    public void demoUpdateData(){
        UpdateWrapper<UserDao> updateWrapper = new UpdateWrapper<UserDao>();
        updateWrapper.eq("phone","17759048528");
        UserDao userDao = new UserDao();
        userDao.setHeadshot("demodemo");
        userMapper.update(userDao,updateWrapper);

    }
}
