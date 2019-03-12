package com.supermoney.loan.api.service;

import com.supermoney.loan.api.utils.Result;

import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/16.
 */
public interface SXenditPayService {
    /**
     * Xendit - 打款请求
     * @param reqData
     * @return
     * @throws Exception
     */
    public Result requestWithoutCert(Map<String, String> reqData);
    /**
     * 银行卡实名账户校验
     * @param reqData
     * @return
     */
    public  Result nameValidator(Map<String, String> reqData);
    /**
     * 银行卡实名账户错误码
     * @param failureReason
     * @return
     */
    public  String NameValidatorErrors(String failureReason);

    public  String  restTemplatePost(String url,Map<String,String> reqData);


}
