package com.supermoney.loan.market.utils;

import org.apache.commons.codec.binary.Base64;


import java.util.UUID;

public class BussCodeGenerate {

    /**
     * 秘钥生成
     * @return
     */
    public  static  String generateSecret(){
        return genCode("SuperSecret");
    }

    /**
     * token生成
     * @return
     */
    public static  String generateToken(){
        return genCode("SuperToken");
    }

    /**
     * 生成唯一CODE
     * UUID+TIME
     * @param pre
     * @return
     */
    public static  String genCode(String pre){
        try {
            Thread.sleep(30);
            String token= UUID.randomUUID().toString().replaceAll("-","");
            String dateString =Long.toString(System.currentTimeMillis());
            token=token+dateString;
            byte[] encodeBase64 = Base64.encodeBase64(token.getBytes("UTF-8"));
            String result = pre+"_" + new String(encodeBase64);
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        genCode("123456");
    }

    /**
     * 秘钥转换为签名请求头参数
     * @param scretkey
     * @return
     */
    public static String scretkeyToAuthorization(String scretkey) {
        try {
            String str = scretkey + ":";
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            String userAgent = "Basic " + new String(encodeBase64);
            return  userAgent;
        }catch (Exception ex){
            return  "";
        }
    }

    /**
     * 签名请求头参数转换为秘钥
     * @param authorization
     * @return
     */
    public  static  String authorizationToScretkey(String authorization){
        authorization=authorization.replace("Basic","").replaceAll(" ","");
        String decodeStr=new String(Base64.decodeBase64(authorization));
        decodeStr=decodeStr.substring(0,decodeStr.length()-1);
        return decodeStr;
    }
}
