package com.supermoney.loan.mg.utils;

import org.apache.commons.codec.binary.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BussCodeGenerate {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    /**
     * 订单编号前缀
     */
    private  static String PRE_ORDER="10";
    /**
     * 放款订单前缀
     */
    private  static String PRE_GRANT="11";

    /**
     * 还款订单编号前缀
     */
    private  static String PRE_REPAY="12";
    /**
     * API商户订单
     */
    private  static  String PRE_APIORDER="13";

    /**
     * 获取毫秒编号
     * @return
     */
    public  static  String getMsCode(){
        try{
            Thread.sleep(186);
            String dateString = formatter.format(new Date());
            return  dateString;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 获取订单编号
     * @return
     */
    public  static  String getOrderCode(){
        Long code=Long.valueOf(PRE_ORDER+getMsCode());
        return  PRE_ORDER+getMsCode();
    }

    /**
     * 获取放款订单编号
     * @return
     */
    public  static  String getGrantCode(){
        Long code=Long.valueOf(PRE_GRANT+getMsCode());
        return  PRE_GRANT+getMsCode();
    }


    /**
     * 获取还款编号
     * @return
     */
    public static  String getRepayCode(){
        Long code=Long.valueOf(PRE_REPAY+getMsCode());
        return  PRE_REPAY+getMsCode();
    }

    /**
     * 获取订单编号
     * @return
     */
    public  static  String getApiOrderCode(){
        return  PRE_APIORDER+getMsCode();
    }

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

}
