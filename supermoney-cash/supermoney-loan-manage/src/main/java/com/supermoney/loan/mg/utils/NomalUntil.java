package com.supermoney.loan.mg.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2017-12-29.
 */
public class NomalUntil {

    /**
     * 密码加密
     * @param password
     * @return
     */
    public  static String passwordEncoder(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return  hashedPassword;
    }

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
     * 时间改变后转字符
     * @param d:时间
     * @param calendarType:添加类型(): Calendar.DATE
     * @param num
     * @return
     */
    public static String DateChangeToStr(Date d,Integer calendarType ,Integer num){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currdate = format.format(d);
        Calendar ca = Calendar.getInstance();
        ca.add(calendarType, num);
        d = ca.getTime();
        String enddate = format.format(d);
        return enddate;
    }

    /**
     * 时间转字符
     * @param d
     * @return
     */
    public static String DateToStr(Date d,String format){
        if(StringUtils.isBlank(format)){
            format="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        String currdate = formatDate.format(d);
        return  currdate;
    }

    /**
     * 生成
     * @param key
     * @param ids
     * @return
     */
    public static String inIdsStr(String key,String[] ids){
        String str=key+" in(";
        for(String item:ids){
            str+=item+",";
        }
        str=str.substring(0,str.length()-1)+")";
        return  str;
    }

    /**
     *随机bigDecimal
     * @param s
     * @param e
     * @return
     */
    public static BigDecimal randomDecimal(double s, double e){
        double d = s+ Math.random() * e % (e - s + 1);
        BigDecimal val=new BigDecimal(d).setScale(4,BigDecimal.ROUND_HALF_UP);
        return  val;
    }

    private static SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat  formatter2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public  static  String getDateStirng(Date dt){
        String dateString = formatter.format(dt);
        return  dateString;
    }

    /**
     * 获取毫秒编号
     * @return
     */
    public  static  String getMsCode(){
        String dateString = formatter2.format(new Date());
        return  dateString;
    }

    /**
     * 时间区间集合
     * @param start
     * @param end
     * @return
     */
    public static List<Map<String,Object>> DateAreaByDay(String start,String end) {
        List<Map<String,Object>> list=new ArrayList<>();
        int max=2000,i=0;
        Date s=  strToDate(start,"yyyy-MM-dd");
        Date e=strToDate(end,"yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();

        while (e.getTime()>= s.getTime() && i<max){
            Map<String,Object> item=new HashMap<>(Constants.App.MAP_MIN_SIZE);
            item.put("theDay",DateToStr(e,""));
            list.add(item);
            ca.setTime(e);
            ca.add(Calendar.DATE, -1);
            e = ca.getTime();
           i++;
        }
        return  list;
    }

}
