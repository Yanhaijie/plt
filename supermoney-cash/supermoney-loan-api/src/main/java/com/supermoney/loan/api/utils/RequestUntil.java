package com.supermoney.loan.api.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by admin on 2017-09-08.
 */
public  class RequestUntil {
    private static final Logger logger = LoggerFactory.getLogger(RequestUntil.class);
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

    /**
     * 获取UserAgent请求头参数
     * @return
     */
    public static Map<String,String> getUserAgentParams(){
        String flag="PACKAGE";
        Map<String,String> result=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        HttpServletRequest request=request();
        String userAgent=request.getHeader("User-Agent");
//        if(!userAgent.contains(flag)){
//            return  result;
//        }
        userAgent=userAgent.contains(flag)? userAgent.substring( userAgent.indexOf(flag)):"";
        String source=request.getParameter("source");
        logger.info("Parameter  source:" + source);
        if(StringUtils.isBlank(source) && request.getAttribute("share")!=null){
            source=request.getAttribute("share").toString();
            logger.info("Attribute  source:" + source);
        }
        userAgent=userAgent+" shar/"+source;
        result.put("PARAMS",userAgent);
        try {
            result.put("IP",getIpAddress(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] ars=userAgent.split(" ");
        for(String ar:ars){
                String[] item=ar.split("/");
                if(item.length<2){
                    continue;
                }
                logger.info("请求头中内容："+item[0]+"----"+item[1]);
                result.put(item[0],item[1]);
        }
        return  result;
    }
    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        if (logger.isInfoEnabled()) {
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

}
