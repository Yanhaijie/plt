package com.supermoney.open.platform.utils;

import ai.advance.sdk.client.OpenApiClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Configurable
public final class AdvanceAIUtils extends OpenApiClient{

    private static final Logger logger = LoggerFactory.getLogger(AdvanceAIUtils.class);

    private static String apiHost = "https://api.advance.ai";
    private static String apiOcrName = "/openapi/face-recognition/v1/ocr-ktp-check";
    private static String apiBlackListName = "/openapi/anti-fraud/v3/blacklist-check";

    private AdvanceAIUtils(String apiHost, String accessKey, String secretKey) {
        super(apiHost, accessKey, secretKey);
    }

    /**
     *  Advance.AI OCR
     * @return
     */
    public static Result identityOCR(InputStream identityFile,String accessKey, String secretKey){
        Result result;

        AdvanceAIUtils client = new AdvanceAIUtils(apiHost, accessKey , secretKey);

        Map<String, InputStream> imagesMap = new HashMap<>();
        imagesMap.put("ocrImage", identityFile);
        String response = client.requestByInputStream(apiOcrName, null, imagesMap);
        Map<String,Object> responsMap = (Map<String,Object>)JSON.parse(response);
        if (responsMap.get("code").equals("SUCCESS")){
            result = ResultGenerator.genSuccessResult(responsMap.get("data"));
        }
        else if (responsMap.get("code").equals("TEST_ACCOUNT_REACHED_LIMITATION")){
            logger.error("Advance.AI 欠费充值");
            result = ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg);
        }
        else {
            logger.info(responsMap.toString());
            result = ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg);
        }

        return result;
    }

    /**
     *  黑名单
     * @return
     */
    public static Result blacklistCheck(String name,String idNumber,String phoneNumber,String accessKey, String secretKey){
        Result result = ResultGenerator.genFailResult("匹配失败");

        //印尼手机前面为0判断
        if (phoneNumber.startsWith("0")){
            phoneNumber = phoneNumber.substring(1);
        }

        try {
            AdvanceAIUtils client = new AdvanceAIUtils(apiHost, accessKey , secretKey);

            Map<String, Object> phoneNumberMap = new HashMap<>();
            phoneNumberMap.put("countryCode", "+62");
            phoneNumberMap.put("areaCode", "");
            phoneNumberMap.put("number", phoneNumber);

            Map<String, Object> imagesMap = new HashMap<>();
            imagesMap.put("name", name);
            imagesMap.put("idNumber", idNumber);
            imagesMap.put("phoneNumber", phoneNumberMap);
            String response = client.request(apiBlackListName, JSONObject.toJSONString(imagesMap));
            Map<String,Object> responsMap = (Map<String,Object>)JSON.parse(response);
            System.out.println(responsMap.toString());
            if (responsMap.get("code").equals("SUCCESS")){
                //查看是否命中，保存黑名单
                Map<String,Object> dataMap = (Map<String,Object>)responsMap.get("data");
                //只有名字匹配或者全部不匹配（不收费）
                if (dataMap.get("recommendation").equals("PASS")){
                    Map<String, Object> detailMap = (Map<String, Object>)dataMap.get("defaultResult");
                    detailMap.put("result",false);
                    detailMap.put("defaultListResult",dataMap.get("defaultListResult"));
                    result = ResultGenerator.genSuccessResult(detailMap);
                }
                //电话匹配（收费）
                else if (dataMap.get("recommendation").equals("NEEDS_VERIFICATION")){
                    Map<String, Object> detailMap = (Map<String, Object>)dataMap.get("defaultResult");
                    detailMap.put("result",true);
                    detailMap.put("defaultListResult",dataMap.get("defaultListResult"));
                    result = ResultGenerator.genSuccessResult(detailMap);
                }
                //身份证匹配、身份证加电话或名字、全匹配、电话和名字匹配（收费）
                else if (dataMap.get("recommendation").equals("REJECT")){
                    Map<String, Object> detailMap = (Map<String, Object>)dataMap.get("defaultResult");
                    detailMap.put("result",true);
                    detailMap.put("defaultListResult",dataMap.get("defaultListResult"));
                    result = ResultGenerator.genSuccessResult(detailMap);
                }
            }
            else if (responsMap.get("code").equals("TEST_ACCOUNT_REACHED_LIMITATION")) {
                result = ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg);
            }
            else {
                result = ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            result = ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg);
        }

        return result;
    }


    /**
     *  Advance Score  信用分
     * @return
     */
    public static Result creditScore(String name,String idNumber,String phone, List<Map<String, Object>> smsList, List<Map<String, Object>> callList ,String accessKey, String secretKey){

        String transactionId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+ UUID.randomUUID().toString();
        String timezoneId = "Asia/Jakarta";

        if (phone.startsWith("0")){
            phone = phone.substring(1);
        }
        Map<String, Object> phoneNumber = new HashMap<>();
        phoneNumber.put("countryCode", "+62");
        phoneNumber.put("areaCode", "");
        phoneNumber.put("number", phone);


        Map<String, Object> param = new HashMap<>();
        param.put("transactionId", transactionId);
        param.put("idNumber", idNumber);
        param.put("name", name);
        param.put("timezoneId", timezoneId);
        param.put("callList", callList);
        param.put("smsList", smsList);
        param.put("phoneNumber", phoneNumber);

        Result result = ResultGenerator.genFailResult("查询失败");
        try {
            AdvanceAIUtils client = new AdvanceAIUtils(apiHost, accessKey , secretKey);
            String respond = client.request("/openapi/anti-fraud/v1/scoring", JSON.toJSONString(param));
            Map<String,Object> responsMap = (Map<String,Object>)JSON.parse(respond);
            System.out.println(responsMap.toString());
            if (responsMap.get("code").equals("SUCCESS")){
                Integer score = (Integer) ((Map<String, Object>)responsMap.get("data")).get("score");
                String grade = (String) ((Map<String, Object>)responsMap.get("data")).get("grade");
            }
            else if (responsMap.get("code").equals("TEST_ACCOUNT_REACHED_LIMITATION")){
                result = ResultGenerator.genFailResult("欠费请充值");
                result.setCode(201);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            ResultGenerator.genFailResult("匹配失败，内部错误");
        }


        return result;
    }
}
