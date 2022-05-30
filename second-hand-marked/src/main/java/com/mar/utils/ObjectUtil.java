package com.mar.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ObjectUtil {
    /**
     * 判断该对象是否所有属性为空
     * 返回ture表示所有属性为null，返回false表示不是所有属性都是null
     */
    public boolean isAllFieldNull(Object object) {
        boolean flag = true;

        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            //设置属性是可以访问的(私有的也可以)
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
                // 只要有1个属性不为空,那么就不是所有的属性值都为空
                if (value != null) {
                    flag = false;
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }
}

