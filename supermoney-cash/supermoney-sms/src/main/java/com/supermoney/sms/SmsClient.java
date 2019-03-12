package com.supermoney.sms;

import com.supermoney.sms.handler.PaaSooHandler;

/**
 * Created by admin on 2018-01-09.
 */
public class SmsClient  {
    /**
     * 发送短信
     * @param mobile  手机号
     * @param content 发送内容
     * @param product 运营商
     * @return
     */
    public static boolean sendMsg(String mobile,String content,String product){
        if(product==null || product.isEmpty()){
            product=Constants.PRODUCT_PAASOO;
        }
        SmsHandler handler;
        if(product.equals(Constants.PRODUCT_PAASOO)){
            handler=new PaaSooHandler();
            return  handler.sendMsg(mobile,content);
        }
        else if(product.equals(Constants.PRODUCT_PAASOO_MARKETING)){
            handler=new PaaSooHandler();
            return  handler.sendMsgByProduct(mobile,content,Constants.PRODUCT_PAASOO_MARKETING);
        }
        else {
            handler=new PaaSooHandler();
            return  handler.sendMsg(mobile,content);
        }
    }
}
