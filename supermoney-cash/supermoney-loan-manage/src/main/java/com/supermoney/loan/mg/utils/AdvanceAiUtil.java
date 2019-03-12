package com.supermoney.loan.mg.utils;

import ai.advance.sdk.client.OpenApiClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Configurable
public final class AdvanceAiUtil extends OpenApiClient{

    private static String apiHost = "https://api.advance.ai";
    private static String apiOcrName = "/openapi/face-recognition/v1/ocr-ktp-check";
    private static String apiBlackListName = "/openapi/anti-fraud/v3/blacklist-check";

    private AdvanceAiUtil(String apiHost, String accessKey, String secretKey) {
        super(apiHost, accessKey, secretKey);
    }

    /**
     *  Advance.AI OCR
     * @param idImgUrl
     * @return
     */
    public static Result identityOCR(String idImgUrl,String accessKey, String secretKey){
        Result result;

        if (!StringUtils.isEmpty(idImgUrl)){
            Result identityResult = creatFileStream(idImgUrl);
            if (identityResult.getCode() == ResultCode.SUCCESS.code){
                InputStream identityFile = (InputStream) identityResult.getData();

               result = identityOCR(identityFile,accessKey,secretKey);
            }
            else {
                result = identityResult;
            }
        }
        else {
            result = ResultGenerator.genFailResult("查询证件链接失败！");
        }

        return result;
    }

    /**
     *  Advance.AI OCR
     * @return
     */
    public static Result identityOCR(InputStream identityFile,String accessKey, String secretKey){
        Result result;

        AdvanceAiUtil client = new AdvanceAiUtil(apiHost, accessKey , secretKey);

        Map<String, InputStream> imagesMap = new HashMap<>();
        imagesMap.put("ocrImage", identityFile);
        String response = client.requestByInputStream(apiOcrName, null, imagesMap);
        Map<String,Object> responsMap = (Map<String,Object>)JSON.parse(response);
        if (responsMap.get("code").equals("SUCCESS")){
            result = ResultGenerator.genSuccessResult(responsMap.get("data"));
        }
        else if (responsMap.get("code").equals("TEST_ACCOUNT_REACHED_LIMITATION")){
            result = ResultGenerator.genFailResult("欠费请充值");
            result.setCode(201);
        }
        else {
            result = ResultGenerator.genFailResult((String) responsMap.get("message"));
        }

        return result;
    }

    /**
     *  黑名单
     * @return
     */
    public static Result blacklistCheck(String name,String idNumber,String phoneNumber,String accessKey, String secretKey){
        Result result = ResultGenerator.genFailResult("匹配失败");
        if (StringUtils.isBlank(name)){
            return ResultGenerator.genFailResult("name 不能为空");
        }
        else if (StringUtils.isBlank(idNumber)){
            return ResultGenerator.genFailResult("idNumber 不能为空");
        }
        else if (StringUtils.isBlank(phoneNumber)){
            return ResultGenerator.genFailResult("phoneNumber 不能为空");
        }

        if (phoneNumber.startsWith("0")){
            phoneNumber = phoneNumber.substring(1);
        }

        try {
            AdvanceAiUtil client = new AdvanceAiUtil(apiHost, accessKey , secretKey);

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
                    detailMap.put("recommendation",dataMap.get("recommendation"));
                    result = ResultGenerator.genSuccessResult(detailMap);
                }
                //电话匹配（收费）
                else if (dataMap.get("recommendation").equals("NEEDS_VERIFICATION")){
                    Map<String, Object> detailMap = (Map<String, Object>)dataMap.get("defaultResult");
                    detailMap.put("result",true);
                    detailMap.put("recommendation",dataMap.get("recommendation"));
                    result = ResultGenerator.genSuccessResult(detailMap);
                }
                //身份证匹配、身份证加电话或名字、全匹配、电话和名字匹配（收费）
                else if (dataMap.get("recommendation").equals("REJECT")){
                    Map<String, Object> detailMap = (Map<String, Object>)dataMap.get("defaultResult");
                    detailMap.put("result",true);
                    detailMap.put("recommendation",dataMap.get("recommendation"));
                    result = ResultGenerator.genSuccessResult(detailMap);
                }
            }
            else if (responsMap.get("code").equals("TEST_ACCOUNT_REACHED_LIMITATION")) {
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
            AdvanceAiUtil client = new AdvanceAiUtil(apiHost, accessKey , secretKey);
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

    /**
     * 生成文件流
     * @param fileUrl
     * @return
     */
    private static Result creatFileStream(String fileUrl){
        Result result = null;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        List list = new ArrayList<>();
        list.add(MediaType.valueOf("application/octet-stream"));
        headers.setAccept(list);

        try {
            //获取图片
            ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl,
                    HttpMethod.GET,
                    new HttpEntity<byte[]>(headers),
                    byte[].class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] responseData = response.getBody();
                InputStream inputStream = new ByteArrayInputStream(responseData);
                result = ResultGenerator.genSuccessResult(inputStream);
            }
            else {
                throw new RuntimeException("获取原图失败！");
            }
        }
        catch (RuntimeException e){
            e.printStackTrace();
            result = ResultGenerator.genFailResult(e.getMessage());
        }

        return result;
    }

    public static void main(String[] args){
//        ocr 测试
        Date beginTime = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

//        Result result = identityOCR("http://52.221.182.1/group1/M00/04/B8/rB8JB1s1mX6ATMP7AADet5LgWII08.jpeg","972a20dd25fb6075","bad947e25dc9e1d4");

//        System.out.println("begin time :" + dateFormat.format(beginTime) +"\n" +  "end time :" + dateFormat.format(new Date()));

//        Result result = blacklistCheck("TEGUH HERDIANSYAH SUBARKAH","3671090201910007","081511737034","972a20dd25fb6075","bad947e25dc9e1d4");
        Result result = creditScore("PUTRI RATNA SARI","3578286710940001","85708608454", new ArrayList<>(), new ArrayList<>(),"972a20dd25fb6075","bad947e25dc9e1d4");
        System.out.println("“”");
    }
}
