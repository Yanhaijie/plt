package com.supermoney.open.platform.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public final class BaiduAIUtils {
    private static final Logger logger = LoggerFactory.getLogger(BaiduAIUtils.class);

    public static void main(String[] args){
        Date beginTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        Result result = null;
        try {
            File file = new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\ocr 测试身份证\\SuperUang-API-V1.12.docx");
            FileInputStream idInputStream = new FileInputStream(file);
            FileInputStream faceInputStream = new FileInputStream(new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\ocr 测试身份证\\2.jpg"));

            String access_token = getAccessToken("https://aip.baidubce.com/oauth/2.0/token" ,"2dgGU9kZdDr1Ay6vTsBr5Fgl","ztHoYua5mX6dyy5d9gli7rGXaZdLBVEH");

            result = checkLivePeopleByByte("https://aip.baidubce.com/rest/2.0/face/v3/faceverify",FileUtil.readFileByFileStream(faceInputStream),access_token);
//            result = baiduCompareByByte("https://aip.baidubce.com/rest/2.0/face/v3/match",FileUtil.readFileByFileStream(idInputStream),FileUtil.readFileByFileStream(faceInputStream),access_token);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 百度活体检测
     * @return
     */
    public static Result checkLivePeopleByByte(String livingUrl, byte[] faceBase64String,String access_token) {
        Result result;

        if (faceBase64String == null){
            logger.error("Bese64 fail");
            return ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg + ",Bese64 fail");
        }
        if (access_token == null) {
            logger.error("get baidu access_token fail");
            return ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg + ",get access_token fail");
        }

        String url = livingUrl + "?access_token=" + access_token;

        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //请求体
        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("image",Base64Util.encode(faceBase64String));
        tempMap.put("image_type","BASE64");
        List tempJsonList = new ArrayList();
        tempJsonList.add(tempMap);
        //发起请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity<String>(JSONObject.toJSONString(tempJsonList), headers),
                byte[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            byte[] responseData = response.getBody();
            Map<String, Object> responsMap = (Map<String, Object>) JSON.parse(responseData);

            //万分之一误拒率的阈值 frr_1e-4    ，千分之一误拒率的阈值  frr_1e-3，
            if(responsMap.get("result") != null && responsMap.get("error_code").toString().equals("0") && !responsMap.get("result").equals("null")){
                Map resultMap = (Map)responsMap.get("result");
                if (resultMap.size() > 0){
                    result = ResultGenerator.genSuccessResult(resultMap);
                }
                else {
                    //图片存在问题，无法检测到头像
                    result = ResultGenerator.genResult(ResultCode.FILE_ERROR_NOT_FACE.code, ResultCode.FILE_ERROR_NOT_FACE.msg);
                }
            }
            else {
                //文件类型错误，或者检测不到人脸图像
                result = ResultGenerator.genResult(ResultCode.FILE_ERROR_NOT_FACE.code, ResultCode.FILE_ERROR_NOT_FACE.msg);
            }
        } else {
            logger.error("百度活体检测失败 code：" + response.getStatusCode());
            result = ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg);
        }

        return result;
    }

    public static Result baiduCompareByByte(String compareUrl ,byte[] idBase64String,byte[] faceBase64String ,String access_token) {
        Result result;

        if (faceBase64String == null || idBase64String == null){
            logger.error("Bese64 fail");
            return ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg + ",Bese64 fail");
        }
        if (access_token == null) {
            logger.error("get baidu access_token fail");
            return ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg + ",get access_token fail");
        }

        String url = compareUrl + "?access_token=" + access_token;

        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //请求体
        Map<String, Object> idMap = new HashMap<>();
        idMap.put("image",Base64Util.encode(idBase64String));
        idMap.put("image_type","BASE64");
        idMap.put("face_type","CERT");
        Map<String, Object> faceMap = new HashMap<>();
        faceMap.put("image",Base64Util.encode(faceBase64String));
        faceMap.put("image_type","BASE64");
        faceMap.put("face_type","CERT");
        List tempJsonList = new ArrayList();
        tempJsonList.add(idMap);
        tempJsonList.add(faceMap);

        //发起请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity<String>(JSONObject.toJSONString(tempJsonList), headers),
                byte[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            byte[] responseData = response.getBody();
            Map<String, Object> responsMap = (Map<String, Object>) JSON.parse(responseData);
            System.out.println(responsMap.toString());

            Map resultMap = (Map) responsMap.get("result");

            if (resultMap != null && resultMap.size() > 0 && responsMap.get("error_code").toString().equals("0") && !responsMap.get("result").equals("null")){
                Double score = Double.parseDouble(resultMap.get("score").toString());
                List<Map<String, Object>> faceList = (List)resultMap.get("face_list");
                Map<String, Object> face1 = faceList.get(0);
                Map<String, Object> face2 = faceList.get(1);
                Map<String, Object> map = new HashMap<>();
                map.put("score", score);
                map.put("faceToken1", face1.get("face_token"));
                map.put("faceToken2", face2.get("face_token"));
                result = ResultGenerator.genSuccessResult(map);
            }
            else {
                //图片存在问题，无法检测到头像
                result = ResultGenerator.genResult(ResultCode.FILE_ERROR_NOT_FACE.code, ResultCode.FILE_ERROR_NOT_FACE.msg);
            }
        } else {
            logger.error("百度人脸对比失败 code：" + response.getStatusCode());
            result = ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code ,ResultCode.INTERNAL_SERVER_ERROR.msg);
        }
        return result;
    }

    /**
     * 获取access
     * @return
     */
    public static String getAccessToken(String accessTokenUrl , String baiduApiKey, String baiduSecretKey){
        String access_token = null;
        String url = accessTokenUrl + "?grant_type=client_credentials&client_id=" + baiduApiKey + "&client_secret=" + baiduSecretKey;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<byte[]>(headers),
                byte[].class);
        if (response.getStatusCode() == HttpStatus.OK){
            byte[] responseData = response.getBody();
            Map<String,Object> responsMap = (Map<String,Object>)JSON.parse(responseData);
            access_token = (String) responsMap.get("access_token");
        }

        return access_token;
    }
}
