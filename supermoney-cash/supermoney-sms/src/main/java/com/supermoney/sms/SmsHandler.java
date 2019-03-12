package com.supermoney.sms;

/**
 * Created by admin on 2018-01-09.
 */
public abstract  class SmsHandler {

    /**
     * 发送短信
     * @param mobile
     * @param content
     * @return
     */
    public  Boolean sendMsg(String mobile,String content){
        return  false;
    }

    /**
     * 发送短信-运营商的某短信产品
     * @param mobile
     * @param content
     * @param product
     * @return
     */
    public Boolean sendMsgByProduct(String mobile,String content,String product){return  false;}
}
