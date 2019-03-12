package com.supermoney.loan.mg.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class NIKCheckUtils {


    /**
     * 查询身份信息
     *{
     *  "nik": "3672080209920003",
     *  "name": "Ra*******tiandi",
     *  "address": "Link*************ng brangbang",
     *  "bod": "1992-09-02"
     *}
     *
     * @param NIK
     * @return
     */
    public static Result checkNIK(String NIK){
        String url = String.format("http://159.65.141.233?nik=%s",NIK);

        Result result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key","IM38FEdklq34nfdV1VY41eCzrHFCeFdv");

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<byte[]> response = restTemplate.exchange(url,
                    HttpMethod.GET,
                    new HttpEntity<byte[]>(headers),
                    byte[].class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] responseData = response.getBody();
                String json = new String(responseData);
                JSONObject jsonObject = JSONObject.parseObject(json);
                result = ResultGenerator.genSuccessResult(jsonObject);
            }
            else {
                result = ResultGenerator.genFailResult("请求错误！");
            }
        }
        catch (RuntimeException e){
            e.printStackTrace();
            result = ResultGenerator.genFailResult("请求错误！");
        }

        return result;
    }

    public static void main(String[] args){
        String[] list = new String[]{
                "3204162201990002",
                "1671045802880013"
        };

        StringBuilder stringBuilder = new StringBuilder();
        for (String nik :list){
            if (nik.length() < 16) continue;
            Result result = checkNIK(nik);
            JSONObject jsonObject;
            if (result.getCode()== ResultCode.SUCCESS.code){
                jsonObject = (JSONObject) result.getData();
            }
            else {
                jsonObject = null;
            }
            String resultStr = jsonObject == null || jsonObject.equals("null") ? "false" : jsonObject.toJSONString();
            stringBuilder.append("\n" + nik + "    " + resultStr);
        }

        System.out.println(stringBuilder.toString());
    }
}
