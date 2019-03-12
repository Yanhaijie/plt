package com.supermoney.loan.market.utils;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.market.service.impl.NIKBussService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class NIKCheckUtils {

    private static final Logger logger = LoggerFactory.getLogger(NIKCheckUtils.class);

    final static private String NIK_CHECK_URL = "http://159.65.141.233";

    final static private String NIK_CHECK_KEY = "IM38FEdklq34nfdV1VY41eCzrHFCeFdv";

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
        String url = String.format(NIK_CHECK_URL + "?nik=%s",NIK);
        logger.info("checkNIK-url:"+url);
        Result result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key",NIK_CHECK_KEY);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<byte[]> response = restTemplate.exchange(url,
                    HttpMethod.GET,
                    new HttpEntity<byte[]>(headers),
                    byte[].class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] responseData = response.getBody();
                String json = new String(responseData);
                logger.info("checkNIK-json:"+json);
                JSONObject jsonObject = JSONObject.parseObject(json);
                result = ResultGenerator.genSuccessResult(jsonObject);
            }
            else {
                result = ResultGenerator.genFailResult("请求错误！");
            }
        }
        catch (RuntimeException e){
            e.printStackTrace();
            result = ResultGenerator.genResult(ResultCode.NIK_ERROR.code , ResultCode.NIK_ERROR.msg);
        }

        return result;
    }

}
