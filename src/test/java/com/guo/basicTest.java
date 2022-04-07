package com.guo;

import com.mar.utils.MD5Util;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author guokaifeng
 * @createDate: 2022/4/5
 **/

@SpringBootTest
public class basicTest {
    @Test
    public void getMD5(){
        String md5 = MD5Util.getMD5("123456salt");
        System.out.println(md5);
    }
}
