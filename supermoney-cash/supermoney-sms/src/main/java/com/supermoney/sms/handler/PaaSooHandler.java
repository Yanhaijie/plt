package com.supermoney.sms.handler;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.sms.Constants;
import com.supermoney.sms.HttpRequestUtils;
import com.supermoney.sms.SmsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 2018-01-09.
 */
public class PaaSooHandler extends SmsHandler {
    private static final Logger logger = LoggerFactory.getLogger(PaaSooHandler.class);

    // 老域名 private  String host="https://api.paasoo.cn/json";
     private  String host="http://api.paasoocn.com/json";
    //短信账户
    String  api_key="9wahknvi";
    String secret="RcMhvhhi";

    //营销短信账户-稳定，但是贵 0.15RMB
//    String  api_key_m="vn8fb8r1";
//    String secret_m="qtTbs9dL";
//    //营销短信账户-不稳定，灰色便宜 0.05RMB
//    String  api_key_mh="qjkauimb";
//    String secret_mh="3S7yipeH";

    /**
     * 发送URL
     * from:部分国家支持自定义发件人，也称之为Sender id.字母或数字的字符串，最多11个字符。示例:PAASOOSMS
     * to:发送目标号码，格式为国家区号直接接手机号码，区号和手机号码均不能以0开头。
     * 		 如马来西亚号码为：01234567891；
     *       国家区号位60；
     *       发送的格式则为： 601234567891。
     *       区号与号码之间去0。
     * text:发送内容，需通过URLEncode方式进行UTF-8编码转换.
     * 测试模板:
     * 【无限云】您的验证码为：16151917，请在一分钟内使用，谢谢！
     * @param to
     * @param content
     * @return
     */
    public  String builderUrl(String api_key,String secret,String to,String content){
        String from="Hipeso";
        return  host+"?key="+api_key+"&secret="+secret+"&from="+from+"&to="+to+"&text="+content;
    }
    @Override
    /**
     * 发送短信
     * @param mobile
     * @param content
     * @return
     */
    public  Boolean sendMsg(String mobile,String content){
       return  sendMsgByProduct(mobile,content,"");
    }

    /**
     * 发送短信-运营商的某短信产品
     * @param mobile
     * @param content
     * @param product
     * @return
     */
    public Boolean sendMsgByProduct(String mobile,String content,String product){
        String url="";
        if(product.equals(Constants.PRODUCT_PAASOO_MARKETING)){
            //营销短信
            url=builderUrl(api_key,secret,mobile,HttpRequestUtils.textEncode(content));
        }else {
            //正常-短信验证码
             url=builderUrl(api_key,secret,mobile,HttpRequestUtils.textEncode(content));
        }

        logger.info(product+"send msg  url:"+url);
        //System.out.println("send msg  url:"+url);
        JSONObject result= HttpRequestUtils.httpGetJson(url);
        logger.info(product+"send msg result:"+result.toJSONString());
        //System.out.println("send msg result:"+result.toJSONString());
        String status=result.getString("status");
        //0成功
        if(status.equals("0")){
            String messageid=result.getString("messageid");
            return  true;
        }else {
            String messageid=result.getString("status_code");
            return  false;
        }
    }
}
