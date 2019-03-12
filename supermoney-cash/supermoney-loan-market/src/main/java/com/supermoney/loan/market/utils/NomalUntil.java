package com.supermoney.loan.market.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017-12-29.
 */
public class NomalUntil {

    /**
     * 字符串转时间
     * @param str
     * @return
     */
    public static  Date strToDate(String str,String format){
        if(StringUtils.isBlank(format)){
            //yyyy-MM-ddTHH:mm:ss.000Z
            format="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleformat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleformat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * json空值处理
     * @param json
     * @param key
     * @return
     */
    public  static String  josnVal(JSONObject json,String key){
        return json.get(key) == null ? null : json.get(key).toString();
    }

    /**
     * json空值处理
     * @param json
     * @param key
     * @return
     */
    public  static String  josnValEmpty(JSONObject json,String key){
        return json.get(key) == null ? "" : json.get(key).toString();
    }

    /**
     * 加敏字符串
     * @param str
     * @return
     */
    public  static  String SensitiveStr(String str){
        if(str.length()<6){
            return  str;
        }
        int c= str.length()/3;
        String m="";
        for(int i=0;i<c;i++){
            m+="*";
        }
        return  str.substring(0,c)+m+str.substring(c+m.length(),str.length());
    }
}
