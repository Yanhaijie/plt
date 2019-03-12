package com.supermoney.loan.api.service.impl;

import com.netflix.discovery.util.StringUtil;
import com.supermoney.loan.api.service.SXenditPayService;
import com.supermoney.loan.api.utils.AppProperties;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by tangwenchi on 2018/1/16.
 */
@Service
@Transactional
public class SXenditPayServiceImpl implements SXenditPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SXenditPayService.class);

    @Autowired
    private AppProperties appProperties;

    // 文档地址 https://xendit.github.io/apireference

    /**
     * 打款请求
     */
    private String STRUAL = "https://api.xendit.co/disbursements";
    /**
     * 实名校验地址
     */
    private String NAMEVAILD_URL="https://api.xendit.co/bank_account_data_requests";

    private Integer connectTimeoutMs = 8000;

    private Integer readTimeoutMs = 10000;

    /**
     * disbursements
     * @param reqData
     * @return
     */
    @Override
    public Result requestWithoutCert(Map<String, String> reqData)  {
        LOGGER.info("disbursements-start");
        //xenditPost(STRUAL,reqData);
        //restTemplatePost(STRUAL,reqData);
        String  result= restTemplatePost(STRUAL,reqData);
        if(org.apache.commons.lang.StringUtils.isBlank(result)){
            LOGGER.info("requestWithoutCert err");
            return  ResultGenerator.genFailResult(" requestWithoutCert err");
        }
        LOGGER.info("disbursements-end");
        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * 银行卡实名账户校验
     * @param reqData
     * @return
     */
    @Override
    public  Result nameValidator(Map<String, String> reqData){
        String  result= xenditPost(NAMEVAILD_URL,reqData);
        if(result.isEmpty()){
            LOGGER.info("Name Validator err");
            return  ResultGenerator.genFailResult(" Name Validator err");
        }
        return ResultGenerator.genSuccessResult(result);
    }
    /**
     * 银行卡实名账户错误码
     * @param failureReason
     * @return
     */
    @Override
    public  String NameValidatorErrors(String failureReason){
        Map<String,String> map=new HashMap<>();
        map.put("MAX_RETRY_TIMES_EXCEED_ERROR","Our system has retried the request several times but still failed because of an unknown error response from the bank.");
        map.put("RECIPIENT_NOT_FOUND_ERROR","There’s no bank recipient with this account number.");
        map.put("UNSUPPORTED_BANK_CODE_ERROR","Destination bank is not supported, request is using the wrong bank code. ");
        return  map.get(failureReason);
    }

    /**
     * xendit Post request
     * @param url
     * @param reqData
     * @return
     */
    public  String xenditPost(String url, Map<String, String> reqData){
        try{
            LOGGER.info("xendit-post-start:");
            String UTF8 = "UTF-8";
            String reqBody = mapToString(reqData);
            LOGGER.info("xendit-post-body:"+reqBody);
            URL httpUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(connectTimeoutMs);
            httpURLConnection.setReadTimeout(readTimeoutMs);
            /**
             * 拼接秘钥字符串
             */
            LOGGER.info("secretKey:"+appProperties.getXenditSecretKey());
            String str = appProperties.getXenditSecretKey() + ":";
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            String userAgent = "Basic " + new String(encodeBase64);
            httpURLConnection.setRequestProperty("Authorization", userAgent);
            httpURLConnection.setRequestProperty("X-IDEMPOTENCY-KEY", reqData.get("external_id"));
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            LOGGER.info("xendit-post-connect-befor:");
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(reqBody.getBytes(UTF8));
            LOGGER.info("xendit-post-connect-after");
            //获取内容
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
            final StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            String resp = stringBuffer.toString();
            LOGGER.info("xendit-post-resp:"+resp);
            if (stringBuffer != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return resp;
        }catch (Exception ex){
            LOGGER.debug("xendit post err:"+ex.getMessage());
            ex.printStackTrace();
        }
        return  "";
    }

    /**
     * restTemplate Post 表单请求
     * @param url
     * @param reqData
     * @return
     */
    public  String  restTemplatePost(String url,Map<String,String> reqData){
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            String reqBody = mapToString(reqData);
            LOGGER.info("reqBody:"+reqBody);
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
            headers.add("X-IDEMPOTENCY-KEY", reqData.get("external_id").toString());
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            LOGGER.info("Authorization:"+userAgent);
            LOGGER.info("X-IDEMPOTENCY-KEY:"+reqData.get("external_id").toString());
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
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
            LOGGER.info("resetRP:"+response.getBody());
            return response.getBody();
        }catch (Exception ex){
            LOGGER.debug("restTemplatePost post err:"+ex.getMessage());
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
    private static String mapToString(Map<String, String> reqData) {
        Set<String> keySet = reqData.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String k : keyArray) {
            if (reqData.get(k)!=null && reqData.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append('"' + k + '"').append(":").append('"' + reqData.get(k).trim() + '"').append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }



}
