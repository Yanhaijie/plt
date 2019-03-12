package com.supermoney.loan.api.service;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.entity.SXenditVirtualAccount;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;
import org.springframework.http.HttpMethod;

import java.util.Map;


/**
 * Created by wenyuhao on 2018/06/04.
 */
public interface SXenditVirtualAccountService extends Service<SXenditVirtualAccount> {

    /**
     * 调用Xendit APi创建虚拟账户
     * @param reqData
     * @return
     */
    public Result requestCreateVirtualAccount(Map<String, Object> reqData);



    /**
     * 为指定用户创建Xendit虚拟账户
     * @param userId
     * @param virtualName   只能是英文的，不然会错误
     * @ammount 指定还款金额, ammount为null时，不指定还款金额
     * @return
     */
    public Result createVirtualAccount(Integer userId,Integer orderId,String virtualName,String bankCode,Integer ammount,Integer accountType);

    /**
     * 更新指定Xendit虚拟账户，还款金额
    */
    public Result updateVirtualAccount(String virtualId,Integer ammount);

    /**
     * 用户向虚拟账户付款后回调方法
     * @param obj
     * @param callBackToken
     * @return
     */
    public  Result virtualAccountPaymentCallBack(JSONObject obj, String callBackToken) throws Exception;

    public  String  restTemplatePost(String url, Map<String,Object> reqData, HttpMethod requestType);

}
