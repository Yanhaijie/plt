package com.supermoney.loan.api.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2017-12-29.
 */
public class NomalUntil {
    private static final Logger logger = LoggerFactory.getLogger(NomalUntil.class);
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
     * 检查手机号码格式
     * @param mobile
     * @return
     */
    public  static  boolean checkMobileNumber(String mobile){
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(mobile.length() != 11){
            return  false;
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(mobile);
            return  m.matches();
        }
    }

    /**
     * 校验印尼手机号码格式
     * @param mobile
     * @return
     */
    public  static  boolean checkYnNumber(String mobile){

        if(StringUtils.isBlank(mobile)){
            return  false;
        }
        if(mobile.length()<12 || mobile.length()> 13){
            return  false;
        }
         return  mobile.substring(0,2).equals("63");
    }

    /**
     * 生成几位随机数
     * @param length 位数
     * @return
     */
    public  static  String generateRandomNum(int length){
        int ws=(int)Math.pow(10,length);
        int num=(int)((Math.random()*9+1)*ws);
        return  String.valueOf(num);
    }

    /**
     * map获取字符
     * @param map
     * @param key
     * @return
     */
    public static String getString(Map<String,Object> map, String key){
        if(map.get(key)!=null){
            if(map.get(key) instanceof String){
                return (String)map.get(key);
            }else{
                return null;
            }
        }else{
            return "";
        }
    }

    /**
     *
     * @param val
     * @return
     */
    public static  String decimalString(BigDecimal val){
        return  val==null? "0":val.toString();
    }

    public  static  BigDecimal decimalNull(BigDecimal val){return  val==null? BigDecimal.ZERO:val;}

    public  static  Integer integerNull(Integer val){return  val==null? 0:val;}
    /**
     *
     * @param val
     * @return
     */
    public  static  String noneString(String val){
        return  StringUtils.isBlank(val)? "":val;
    }

    /**
     *
     * @param obj
     * @return
     */
    public  static  String objString(Object obj){
        return  obj==null? "":obj.toString();
    }

    /**
     * 反转义字符
     * @param str
     * @return
     */
    public  static  String decoderStr(String str){
            try {
              return   URLDecoder.decode(str,"UTF-8");
            }catch (Exception ex){

            }
            return "";
    }

    private  static  String clickKey="10086219";
    /**
     * 生成加密ClickId参数
     * @param userId
     * @param bountyId
     * @param offId
     * @param chanelId
     * @param source
     * @return
     */
    public  static  String encodeClickId(Integer userId,Integer bountyId,String offId,String chanelId,String source){
        try {
            String dtStr=getNowDateString();
            String str=String.format("%s_%s_%s_%s_%s_%s",
                    userId.toString(),
                    bountyId.toString(),
                    offId,
                    chanelId,
                    source,
                    dtStr
            );
            EncryptUtil des = new EncryptUtil(clickKey, "utf-8");
            String rd=des.encode(str);
            rd=symbolEncoder(rd);
            return  rd;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  null;
    }
    /**
     * 生成加密的shareCode
     */
    public static String encodeShareCode(String maCode){
        try {
            EncryptUtil des = new EncryptUtil(clickKey, "utf-8");
            String rd=des.encode(maCode);
            rd=symbolEncoder(rd);
            return  rd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解密ClickId参数
     * @param str
     * @return
     */
    public  static  Map<String,Object> decoderClickId(String str){
        try {
//            str=str.indexOf("%")>-1? NomalUntil.decoderStr(str):str;
            logger.info("待解密的字符串为："+str);
            EncryptUtil des = new EncryptUtil(clickKey, "utf-8");
            str=symbolDencoder(str);
            logger.info("转义之后的字符串为："+str);
            String  deStr=des.decode(str);
            logger.info("解密之后的数据为："+deStr);
            String [] ary=deStr.split("_");
            Map<String,Object> param=new HashMap<>();
            param.put("userId",ary[0]);
            param.put("bountyId",ary.length>1? ary[1]:"");
            param.put("offId",ary.length>2 ? ary[2]:"");
            param.put("chanelId",ary.length>3 ? ary[3]:"");
            param.put("source",ary.length>4 ? ary[4]:"");
            param.put("dtm",ary.length>5? ary[5]:"");
            return  param;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  null;
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
     *
     * @return
     */
    public  static  String getNowDateString(){
        Date dt=new Date();
        return  getDateStirng(dt);
    }

    /**
     * 分页map
     * @param page
     * @param size
     * @return
     */
    public  static  Map<String,Object> getPageMap(int page,int size){
        Map<String,Object> param=new HashMap<>();
        int takeSize=(page-1)*size;
        param.put("takesize",takeSize);
        param.put("size",size);
        return  param;
    }

    /**
     *  符号处理
     * @param data
     * @return
     */
    public  static   String  symbolEncoder(String data){
//            data=data.replaceAll("\\/","-");
            data=data.replaceAll("\\+","_");
            return URLEncoder.encode(data);
    }

    /**
     * 符号反处理
     * @param data
     * @return
     */
    public  static   String  symbolDencoder(String data) {
//        data=data.replaceAll("-","\\/");
//        data=data.replaceAll("_","\\+");
//        return URLDecoder.decode(data);
        return data.replaceAll("_","\\+");
    }

    /**
     * 去除表情符号
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (source != null && source.length() > 0) {
            return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
        } else {
            return source;
        }
    }
    /**
     * 替换四个字节的字符 '\xF0\x9F\x98\x84\xF0\x9F）的解决方案 😁
     * @author ChenGuiYong
     * @data 2015年8月11日 上午10:31:50
     * @param content
     * @return
     */
    public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {
                    conbyte[i+j]=0x30;
                }
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
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
     *  对象 转 MAP
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj)  {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        if(obj == null)
            return null;
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        }catch (Exception ex){
             throw  new RuntimeException(ex);
        }
        return map;
    }

    public static String decoderShareCode(String shareCode) {
        try {
            String  deStr = "";
            if(StringUtils.isNotBlank(shareCode)){
                EncryptUtil des = new EncryptUtil(clickKey, "utf-8");
                shareCode=symbolDencoder(shareCode);
                deStr=des.decode(shareCode);
                return deStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(decoderClickId("kZRD32ozP1kMIl%2BB%2FBehoip5Xxi%2Fk%2FYCLbCQXMALmmiibxwF%2BHT0zQ%3D%3D"));
    }
}
