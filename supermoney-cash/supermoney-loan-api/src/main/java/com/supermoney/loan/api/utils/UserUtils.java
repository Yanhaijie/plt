package com.supermoney.loan.api.utils;

import org.apache.commons.lang.StringUtils;

import java.net.URLDecoder;

/**
 * Created by bear on 2018/1/11.
 */
public class UserUtils {
    public static final String SESSION_USER = "user";
    private  static  String key="s1p2m3o4n5e6y7";

    /**
     * 生成AppSecret
     * @param userId
     * @param mobile
     * @return
     */
    public  static String toAppSecret(Integer userId,String mobile){
        try {
            String appStr=userId.toString()+"|"+mobile;
            EncryptUtil des = new EncryptUtil(key, "utf-8");
            appStr=des.encode(appStr);
            appStr=NomalUntil.symbolEncoder(appStr);
            return  appStr;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  null;
    }

    /**
     * 获取AppSecret中用户信息
     * @param appSecret
     * @return 0:userId 1:mobile
     */
    public  static  String[] deAppSecret(String appSecret){
        try {
            EncryptUtil des = new EncryptUtil(key, "utf-8");
            System.out.println("待解密字段为："+appSecret);
            appSecret= NomalUntil.symbolDencoder(appSecret);
            if(appSecret.contains("%")){
                appSecret = URLDecoder.decode(appSecret);
            }
            System.out.println("转义之后的字符串为："+appSecret);
            String  deStr=des.decode(appSecret);
            System.out.println("解密之后的字符串为："+deStr);
            return  deStr.split("\\|");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  null;
    }


    /**
     * 获取当前用户id
     *
     * @return
     */
    public static Integer getCurrentUserId() {
        try {
            String appSecret= RequestUntil.request().getParameter("appSecret");
            if(StringUtils.isBlank(appSecret)){
                return  null;
            }
            appSecret=appSecret.indexOf("%")>-1? NomalUntil.decoderStr(appSecret):appSecret;
            String[] appInfo=deAppSecret(appSecret);
            return Integer.valueOf(appInfo[0]);

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return  null;
    }

    public static String getCurrentMobile() {
        try {
            String appSecret= RequestUntil.request().getParameter("appSecret");
            if(StringUtils.isBlank(appSecret)){
                return  null;
            }
            appSecret=appSecret.indexOf("%")>-1? NomalUntil.decoderStr(appSecret):appSecret;
            String[] appInfo=deAppSecret(appSecret);
            return appInfo[1];

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return  null;
    }

    /**
     * 获取当前AccessToken
     * @return
     */
    public  static  String getCurrentAccessToken(){
        String accssToken= RequestUntil.request().getParameter("access_token");
        return  accssToken;
    }

}
