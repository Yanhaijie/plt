package com.supermoney.loan.api.utils;

import com.google.common.collect.Maps;
import com.supermoney.loan.api.entity.STransfertoKey;
import com.supermoney.loan.api.service.SExchangeRateService;
import com.supermoney.loan.api.service.STransfertoKeyService;
import com.supermoney.loan.api.service.STransfertoTopupHisService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Component
@Transactional
public class TransferToUtil {

    private static final Logger logger = LoggerFactory.getLogger(TransferToUtil.class);

    @Value("${transferto.apiKey}")
    private String apiKey;
    @Value("${transferto.apiSecret}")
    private String apiSecret;
    @Value("${transferto.login}")
    private String login;
    @Value("${transferto.token}")
    private String token;
    @Value("${transferto.topup.url}")
    private String topup;

    @Value("${transferto.topup.topupAction}")
    private String topupAction;
    @Autowired
    private STransfertoKeyService sTransfertoKeyService;
    @Autowired
    private STransfertoTopupHisService sTransfertoTopupHisService;
    @Resource
    private SExchangeRateService sExchangeRateService;


    public STransfertoKey findKeyByType(String type) {
        return sTransfertoKeyService.findBy("type", type);
    }

    public void updateKey(STransfertoKey sTransfertoKey) {
        sTransfertoKeyService.update(sTransfertoKey);
    }

    public Map<String, String> msisdnInfo(String phoneNumber) {
        STransfertoKey sTransfertoKey = this.findKeyByType("msisdn_info");
        String md5 = "";
        boolean isRuning = true;
        String result = "";
        long keyL = System.currentTimeMillis();
        long paramKeyL = 0;
        int key = (int) (keyL - paramKeyL) / 100;
        logger.info("msisdnInfo key============" + key);
        //设置请求参数
        String md5Key = login + token + key;
        md5 = Md5Util.md5Hex(md5Key);
        Map<String, Object> reqData = Maps.newHashMap();
        reqData.put("login", login);
        reqData.put("key", key);
        reqData.put("md5", md5);
        reqData.put("action", "msisdn_info");
        reqData.put("destination_msisdn", phoneNumber);
        reqData.put("return_service_fee", "1");
        result = restTemplatePost(topup, reqData, HttpMethod.POST, MediaType.MULTIPART_FORM_DATA);
        Map<String, String> map = this.stringToMap(result, "\\r\\n");
        return map;
    }


    public Map<String, String> reserveId() {
        STransfertoKey sTransfertoKey = this.findKeyByType("reserve_id");
        // int key = sTransfertoKey.getStartValue();
        String md5 = "";
        boolean isRuning = true;
        String result = "";
        long keyL = System.currentTimeMillis();
        long paramKeyL = 1531912000000L;
        int key = (int) (keyL - paramKeyL) / 100;
        logger.info("reserveId key============" + key);
        //设置请求参数
        String md5Key = login + token + key;
        md5 = Md5Util.md5Hex(md5Key);
        Map<String, Object> reqData = Maps.newHashMap();
        reqData.put("login", login);
        reqData.put("key", key);
        reqData.put("md5", md5);
        reqData.put("action", "reserve_id");
        result = restTemplatePost(topup, reqData, HttpMethod.POST, MediaType.MULTIPART_FORM_DATA);
        System.out.println(result);
       /* try {
            sTransfertoKey.setStartValue(key);
            this.updateKey(sTransfertoKey);
        }catch (Exception ex){
        }*/
        return this.stringToMap(result, "\\r\\n");
    }


    public Map<String, String> topUp(Integer userId, String phoneNumber, Integer product, String msisdn, String yesOrNo, String smsTxt, String sendYesOrNo, String sSmsTxt, String transactionPrice) {
        BigDecimal transactionPriceUsd = sExchangeRateService.indoneslaToUsd(new BigDecimal(transactionPrice));
        boolean isRuning = true;
        String result = "";
        int count = 0;
        Map<String, String> map = null;
        long keyL = System.currentTimeMillis();
        long paramKeyL = 0L;
        int key = (int) (keyL - paramKeyL) / 100;
        logger.info("topUp key============" + key);
        String md5Key = login + token + key;
        String md5 = Md5Util.md5Hex(md5Key);
        Map<String, Object> reqData = Maps.newHashMap();
        reqData.put("login", login);
        reqData.put("key", key);
        reqData.put("md5", md5);
        //真实充话费 TOPUPCONFIG
        //topupAction=simulation 模拟接口，
//        topupAction=topup; //线上接口，
        reqData.put("action", topupAction);
        reqData.put("destination_msisdn", phoneNumber);
        reqData.put("msisdn", msisdn);
        reqData.put("product", product.toString());
        reqData.put("send_sms", yesOrNo);
        reqData.put("sms", smsTxt);
        reqData.put("sender_sms", sendYesOrNo);
        reqData.put("sender_text", sSmsTxt);
        result = restTemplatePost(topup, reqData, HttpMethod.POST, MediaType.MULTIPART_FORM_DATA);
        logger.info(result);
        map = this.stringToMap(result, "\\r\\n");
        if (map != null) {
            map.put("response_body", result);
            map.put("user_id", userId.toString());
            map.put("transaction_price", transactionPrice);
            map.put("transaction_price_usd", transactionPriceUsd.toString());
            sTransfertoTopupHisService.saveTopUpRecord(map);
        }
        return map;
    }


    /**
     * restTemplate Post 表单请求
     *
     * @param url
     * @param reqData
     * @return
     */
    public String restTemplatePost(String url, Map<String, Object> reqData, HttpMethod requestType, MediaType mediaType) {
        //url = "https://api.transferto.com/v1.1/ping";
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            String reqBody = mapToString(reqData);
            logger.info("reqBody:" + reqBody);
            String nonce = NomalUntil.getMsCode();
            String data = apiKey + nonce;
            /**
             * 拼接秘钥字符串
             */
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] encodeBase64 = Base64.encodeBase64(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
            /**
             * 请求头
             */
            headers.add("X-TransferTo-apikey", apiKey);
            headers.add("X-TransferTo-nonce", nonce);
            headers.add("X-TransferTo-hmac", new String(encodeBase64));
            headers.setContentType(mediaType);
            /**
             * 添加参数
             */
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
            if (reqData != null && reqData.keySet() != null) {
                for (String key : reqData.keySet()) {
                    params.add(key, reqData.get(key) == null ? "" : reqData.get(key));
                }
            }
            /**
             * 请求
             */
            ResponseEntity<String> response = null;
            if (requestType == HttpMethod.PATCH) {
                HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
                client.setRequestFactory(requestFactory);
            }
            response = client.exchange(url, requestType, new HttpEntity<MultiValueMap<String, Object>>(params, headers), String.class);
            logger.info("resetRP:" + response.getBody());
            return response.getBody();
        } catch (Exception ex) {
            logger.debug("restTemplatePost post err:" + ex.getMessage());
            ex.printStackTrace();
        }
        return "";

    }

    /**
     * map转post值
     *
     * @param reqData
     * @return
     */
    public String mapToString(Map<String, Object> reqData) {
        if (reqData == null) {
            return "{}";
        }
        Set<String> keySet = reqData.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String k : keyArray) {
            if (reqData.get(k) != null) // 参数值为空，则不参与签名
                sb.append('"' + k + '"').append(":").append(reqData.get(k)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }


    public Map<String, String> stringToMap(String val, String splitStr) {
        val = val.trim();
        if (org.springframework.util.StringUtils.isEmpty(val)) {
            return null;
        }
        Map<String, String> result = Maps.newHashMap();
        String[] strArr = val.split(splitStr);
        for (String str : strArr) {
            logger.info(str);
            String key = "";
            String value = "";
            try {
                key = str.split("=")[0];
                value = str.split("=")[1];
            } catch (Exception ex) {
            }
            result.put(key, value);
        }
        return result;
    }

    //根据手机号获取充值产品列表
    public Map<String, String> msisdn_info(String phoneNumber) {
        String result;
        long keyL = System.currentTimeMillis();
        long paramKeyL = 0;
        int key = (int) (keyL - paramKeyL) / 100;
        logger.info("topUp key============" + key);
        String md5Key = login + token + key;
        String md5 = Md5Util.md5Hex(md5Key);
        Map<String, Object> reqData = Maps.newHashMap();
        reqData.put("login", login);
        reqData.put("key", key);
        reqData.put("md5", md5);
        reqData.put("action", "msisdn_info");
        reqData.put("destination_msisdn", phoneNumber);
        result = restTemplatePost(topup, reqData, HttpMethod.POST, MediaType.MULTIPART_FORM_DATA);
        logger.info("msisdn_info result:" + result + "手机号为：" + phoneNumber);
        return this.stringToMap(result, "\\r\\n");
    }
}
