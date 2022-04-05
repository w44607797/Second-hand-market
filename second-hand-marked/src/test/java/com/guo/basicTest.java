package com.guo;

import com.mar.utils.MD5Util;
import org.junit.Test;

/**
 * @author guokaifeng
 * @createDate: 2022/4/5
 **/

public class basicTest {
    @Test
    public void getMD5(){
        String md5 = MD5Util.getMD5("123456salt");
        System.out.println(md5);
    }
}
