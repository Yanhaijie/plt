package com.wi.data.clearapi.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017-09-08.
 */
public  class RequestUntil {
    /**
     * 获取当前request
     * @return
     */
    public  static HttpServletRequest request(){
        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return  request;
    }

    /**
     * 获取请求参数
     * @return
     */
    public static Map getParams(){
        Map<String,String> result=new HashMap<>();
        HttpServletRequest request=request();
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    if(paramName.equals("search")){
                        Object json= JSON.parseObject(paramValue);
                        map.put(paramName, json);
                    }else {
                        map.put(paramName, paramValue);
                    }
                }
            }
        }

        return map;
    }


}
