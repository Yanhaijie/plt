package com.supermoney.loan.mg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.supermoney.loan.mg.dao.SXenditVirtualAccountMapper;
import com.supermoney.loan.mg.entity.SXenditVirtualAccount;
import com.supermoney.loan.mg.service.SXenditVirtualAccountService;
import com.supermoney.loan.mg.utils.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by wenyuhao on 2018/06/07.
 */
@Service
@Transactional
public class SXenditVirtualAccountServiceImpl extends AbstractService<SXenditVirtualAccount> implements SXenditVirtualAccountService {

    private static final Logger logger = LoggerFactory.getLogger(SXenditVirtualAccountService.class);

    @Autowired
    private AppProperties appProperties;

    private String xenditUrl = "https://api.xendit.co";

    /**
     * 创建虚拟账号请求
     */
    private String CREATE_VIRTUAL_ACCOUNT_URL = "https://api.xendit.co/callback_virtual_accounts";

    private String BAND_CODE_MANDIRI = "MANDIRI";
    private String BAND_CODE_BRI = "BRI";
    private String BAND_CODE_BNI = "BNI";


    @Resource
    private SXenditVirtualAccountMapper sXenditVirtualAccountMapper;

    public Result requestCreateVirtualAccount(Map<String, Object> reqData) {
        if (reqData == null) {
            logger.info("Create Virtual Account reqParam Is Null");
            return ResultGenerator.genFailResult("Create Virtual Account reqParam Is Null");
        }
        logger.info("Create Virtual Account Start");
        String result = this.restTemplatePost(CREATE_VIRTUAL_ACCOUNT_URL, reqData, HttpMethod.POST);
        if (result.isEmpty()) {
            logger.info("Busy please try again later!");
            return ResultGenerator.genFailResult("Busy please try again later!");
        }
        logger.info("Create Virtual Account End");
        return ResultGenerator.genSuccessResult(result);
    }


    public Result payToVirtualAccout(String virtualAccountId, Integer amount) {
        SXenditVirtualAccount virtualAccount = this.findBy("id", virtualAccountId);
        if (virtualAccount == null) {
            return ResultGenerator.genFailResult("virtual account is not exist!");
        }
        String externalId = virtualAccount.getExternalId();
        String payUrl = xenditUrl + "/callback_virtual_accounts/external_id=" + externalId + "/simulate_payment";
        Map<String, Object> reqData = Maps.newHashMap();
        reqData.put("amount", amount + "");
        logger.info("payment start");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String sn = formatter2.format(new Date());
        String result = this.restTemplatePost(payUrl, reqData, HttpMethod.POST);
        if (result.isEmpty()) {
            logger.info("Busy please try again later!");
            return ResultGenerator.genFailResult("Busy please try again later!");
        }
        logger.info("payment end");
        return ResultGenerator.genSuccessResult(result);
    }

    public Result createVirtualAccount(Integer userId, Integer orderId, String virtualName, String bandCode, Integer ammount, Integer acountType) {
        logger.info("Create Virtual Account Init For UserId:" + userId);
        Map<String, Object> reqData = new HashMap<>();
        String sn = NomalUntil.getMsCode();
        reqData.put("external_id", sn);
        reqData.put("bank_code", bandCode);
        //指定还款金额
        if (ammount != null) {
            reqData.put("is_closed", true);
            reqData.put("expected_amount", ammount);
        }
        if (virtualName.isEmpty()) {
            logger.info("Create Virtual Account Err: virtualName is empty");
            return ResultGenerator.genFailResult("Create Virtual Account Err: virtualName is empty");
        }
        //virtualName 只能是英文的，不然会错误
        reqData.put("name", virtualName);
        Result result = requestCreateVirtualAccount(reqData);
        //请求成功
        if (result.getCode() == ResultCode.SUCCESS.code) {
            JSONObject obj = JSONObject.parseObject(result.getData().toString());
            String id = obj.getString("id");
            String ownerId = obj.getString("owner_id");
            String externalId = obj.getString("external_id");
            String bankCode = obj.getString("bank_code");
            String merchantCode = obj.getString("merchant_code");
            String name = obj.getString("name");
            String accountNumber = obj.getString("account_number");
            String suggestedAmount = obj.getString("suggested_amount");
            String isClosed = obj.getBoolean("is_closed") + "";
            String expectedAmount = obj.getString("expected_amount");
            String isSingleUse = obj.getBoolean("is_single_use") + "";
            String status = obj.getString("status");
            //记录入平台
            SXenditVirtualAccount virtualAccount = new SXenditVirtualAccount();
            virtualAccount.setId(id);
            virtualAccount.setOwnerId(ownerId);
            virtualAccount.setExternalId(externalId);
            virtualAccount.setBankCode(bankCode);
            virtualAccount.setMerchantCode(merchantCode);
            virtualAccount.setName(name);
            virtualAccount.setAccountNumber(accountNumber);
            virtualAccount.setSuggestedAmount(suggestedAmount);
            virtualAccount.setIsClosed(isClosed);
            virtualAccount.setExpectedAmount(expectedAmount);
            virtualAccount.setIsSingleUse(isSingleUse);
            virtualAccount.setStatus(status);
            virtualAccount.setUserId(userId);
            virtualAccount.setOrderId(orderId);
            virtualAccount.setAccountType(acountType);
            virtualAccount.setXenditsecretkey(appProperties.getXenditSecretKey());
            this.save(virtualAccount);
            return ResultGenerator.genSuccessResult(id);
        } else {
            return result;
        }
    }

    /**
     * 更新与你账户还款资金
     * @param virtualId
     * @param ammount
     * @return
     */
    public Result updateVirtualAccount(String virtualId, Integer ammount) {
        logger.info("update Virtual Account start");
        Map<String, Object> reqData = new HashMap<>();
        reqData.put("expected_amount", ammount);
        String result = this.restTemplatePost(CREATE_VIRTUAL_ACCOUNT_URL + "/" + virtualId, reqData, HttpMethod.PATCH);
        logger.info("update Virtual Account start");
        if (result.isEmpty()) {
            logger.info("Busy please try again later!");
            return ResultGenerator.genFailResult("Busy please try again later!");
        }
        logger.info("update Virtual Account End");
        JSONObject obj = JSONObject.parseObject(result);
        String id = obj.getString("id");
        String ownerId = obj.getString("owner_id");
        String externalId = obj.getString("external_id");
        String bankCode = obj.getString("bank_code");
        String merchantCode = obj.getString("merchant_code");
        String name = obj.getString("name");
        String accountNumber = obj.getString("account_number");
        String suggestedAmount = obj.getString("suggested_amount");
        String isClosed = obj.getBoolean("is_closed") + "";
        String expectedAmount = obj.getString("expected_amount");
        String isSingleUse = obj.getBoolean("is_single_use") + "";
        String status = obj.getString("status");
        //记录入平台
        SXenditVirtualAccount virtualAccount = new SXenditVirtualAccount();
        virtualAccount.setId(id);
        virtualAccount.setOwnerId(ownerId);
        virtualAccount.setExternalId(externalId);
        virtualAccount.setBankCode(bankCode);
        virtualAccount.setMerchantCode(merchantCode);
        virtualAccount.setName(name);
        virtualAccount.setAccountNumber(accountNumber);
        virtualAccount.setSuggestedAmount(suggestedAmount);
        virtualAccount.setIsClosed(isClosed);
        virtualAccount.setExpectedAmount(expectedAmount);
        virtualAccount.setIsSingleUse(isSingleUse);
        virtualAccount.setStatus(status);
        this.update(virtualAccount);
        return ResultGenerator.genSuccessResult(id);
    }

    /**
     * restTemplate Post 表单请求
     * @param url
     * @param reqData
     * @return
     */
    public  String  restTemplatePost(String url,Map<String,Object> reqData,HttpMethod requestType){
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            String reqBody = this.mapToString(reqData);
            logger.info("reqBody:"+reqBody);
            /**
             * 拼接秘钥字符串
             */
            String str = appProperties.getXenditSecretKey() + ":";
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            String userAgent = "Basic " + new String(encodeBase64);
            /**
             * 请求头
             */
            headers.add("Authorization", userAgent);
            String sn = NomalUntil.getMsCode();
            headers.add("X-IDEMPOTENCY-KEY",sn );
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            logger.info("Authorization:"+userAgent);
            logger.info("X-IDEMPOTENCY-KEY:"+sn);
            /**
             * 添加参数
             */
            MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
            for(String key:reqData.keySet()){
                params.add(key,reqData.get(key)==null? "":reqData.get(key).toString());
            }
            /**
             * 请求
             */
            //postForObject
            ResponseEntity<String> response = null;
            if(requestType == HttpMethod.PATCH){
                HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
                client.setRequestFactory(requestFactory);
            }
            response = client.exchange(url, requestType,  new HttpEntity<MultiValueMap<String, String>>(params, headers), String.class);
            logger.info("resetRP:"+response.getBody());
            return response.getBody();
        }catch (Exception ex){
            logger.debug("restTemplatePost post err:"+ex.getMessage());
            ex.printStackTrace();
        }
        return  "";
    }

    /**
     * map转post值
     *
     * @param reqData
     * @return
     */
    private static String mapToString(Map<String, Object> reqData) {
        Set<String> keySet = reqData.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String k : keyArray) {
            if (reqData.get(k)!=null) // 参数值为空，则不参与签名
                sb.append('"' + k + '"').append(":").append(reqData.get(k)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

}
