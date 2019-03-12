package com.wi.data.clearapi.utils;

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
}
