package com.supermoney.loan.api.utils;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by wenyuhao on 2018-04-11.
 */
public class ClassUtils {

    // 把一个字符串的第一个字母大写、效率是最高的、
    public static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }


    public static Map<String,Object> parseToMap(Object obj) throws Exception{
        Map<String,Object> paraMap = Maps.newHashMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fileName = field.getName();
            Method m = (Method) obj.getClass().getMethod("get" + getMethodName(fileName));
            paraMap.put(fileName,m.invoke(obj));
        }
        return paraMap;
    }

}
