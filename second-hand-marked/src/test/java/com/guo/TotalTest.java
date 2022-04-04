package com.guo;

import com.mar.MainApplication;
import com.mar.exception.DatabaseException;
import com.mar.utils.ExceptionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guokaifeng
 * @createDate: 2022/4/5
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class TotalTest {

    @Test
    public void demoException() throws DatabaseException {
        System.out.println("开始抛异常");
        throw new DatabaseException(ExceptionUtil.DATABASE_MESSAGE,ExceptionUtil.DATABASE_MESSAGE_USER);
    }

}
