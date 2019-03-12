package com.supermoney.open.platform.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    /**
     * 获取请求参数-2
     * @param request
     * @return
     */
    public static Map<String, Object> getParameters(HttpServletRequest request) {
        if (request == null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        Map<String, Object> p = new HashMap<String, Object>();
        Map req = request.getParameterMap();
        if ((req != null) && (!req.isEmpty())) {

            Collection keys = req.keySet();
            for (Iterator i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                Object value = req.get(key);
                Object v = null;
                if ((value.getClass().isArray())
                        && (((Object[]) value).length > 0)) {
                    v = ((Object[]) value)[0];
                } else {
                    v = value;
                }
                if ((v != null) && ((v instanceof String))) {
                    String s = (String) v;
                    if (s.length() > 0) {
                        p.put(key, s);
                    }
                }
            }
            return p;
        }
        return p;
    }

    /**
     * 获取所有请求头
     * @return
     */
    public static Map<String, String> getHeads(){

        return null;
    }

    /**
     * 获取某个请求头
     * @param key
     * @return
     */
    public  static String getHeadByKey(String key){
        HttpServletRequest request=request();
        return request.getHeader(key);
    }
}
