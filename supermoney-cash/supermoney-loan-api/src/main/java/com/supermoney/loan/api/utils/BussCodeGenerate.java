package com.supermoney.loan.api.utils;

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
        return  PRE_ORDER+getMsCode();
    }

    /**
     * 获取订单编号
     * @return
     */
    public  static  String getApiOrderCode(){
        return  PRE_APIORDER+getMsCode();
    }


    /**
     * 获取借款编号
     * @return
     */
    public static  String getRepayCode(){
        return  PRE_REPAY+getMsCode();
    }



}
