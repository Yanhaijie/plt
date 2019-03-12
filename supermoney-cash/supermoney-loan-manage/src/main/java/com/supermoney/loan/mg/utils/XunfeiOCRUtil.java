package com.supermoney.loan.mg.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public final class XunfeiOCRUtil {
    private static final Logger logger = LoggerFactory.getLogger(XunfeiOCRUtil.class);



    /**
     * 把流转base64
     * @param inputStream
     * @return
     */
    public static String getBase64ByString(InputStream inputStream) {
        byte[] data = null;
        try {
            if (inputStream == null || inputStream.available() == 0 ){
                return null;
            }
            data = new byte[inputStream.available()];
            inputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    /**
     * 生成文件流
     * @param fileUrl
     * @return
     */
    public static Result creatLocalFile(String fileUrl){
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
                throw new RuntimeException("get img fail!");
            }
        }
        catch (RuntimeException e){
            e.printStackTrace();
            result = ResultGenerator.genFailResult(e.getMessage());
        }

        return result;
    }



    public static void main(String[] args){
        Date beginTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        Result result = null;
        try {

            String basePath = "C:\\Users\\Chaplin\\Desktop\\临时文件\\ocr 测试身份证";
            File baseDir = new File(basePath);
            File tempFile;
            File[] files = baseDir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    tempFile = files[i];
                    FileInputStream inputStream = new FileInputStream(tempFile);
                    xunfeiOcr(inputStream);
                }

//            File file = new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\ocr 测试身份证\\30.jpg");
//            FileInputStream idInputStream = new FileInputStream(file);
//            FileInputStream faceInputStream = new FileInputStream(new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\2018年5月\\OKTUDF5@BSX}SO8)R%BJ4RD.png"));

//            String access_token = getAccessToken("2dgGU9kZdDr1Ay6vTsBr5Fgl","ztHoYua5mX6dyy5d9gli7rGXaZdLBVEH");

//            pathList.add(file.getName());
//            xunfeiOcr(idInputStream);
//            result = checkLivePeopleByByte("https://aip.baidubce.com/rest/2.0/face/v3/faceverify",FileUtil.readFileByFileStream(faceInputStream),access_token);
//            result = baiduCompareByByte("https://aip.baidubce.com/rest/2.0/face/v3/match",FileUtil.readFileByFileStream(idInputStream),FileUtil.readFileByFileStream(faceInputStream),access_token);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    //OCR
    public static Result xunfeiOcr(InputStream imageStream) throws UnsupportedEncodingException {
        Result result = ResultGenerator.genFailResult("fail");

        String idBase64String = getBase64ByString(imageStream);

        if (idBase64String == null){
            return ResultGenerator.genFailResult("Bese64 fail ！");
        }

        String url = "http://webapi.xfyun.cn/v1/service/v1/ocr/general";
        String curTime = (System.currentTimeMillis()/1000) + "";
        String xParam = "eyJsYW5ndWFnZSI6ImVuIiwibG9jYXRpb24iOiJmYWxzZSJ9";
        String xAppid = "5b30abfb";
        String Appkey = "41de9ea11db33579c5b12e50b5ffeb32";

        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-Param",xParam);
        headers.set("X-Appid",xAppid);
        headers.set("X-CurTime",curTime);
        headers.set("X-CheckSum", DigestUtils.md5DigestAsHex((Appkey + curTime + xParam).getBytes()));

        //请求体
        String bodyString = "image=" + URLEncoder.encode(idBase64String,"utf-8");
        //发起请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity<String>(bodyString, headers),
                byte[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            byte[] responseData = response.getBody();
            Map<String, Object> responsMap = (Map<String, Object>) JSON.parse(responseData);
            if (responsMap.get("code").toString().equals("0")){
                Map<String, Object> dataMap = (Map<String, Object>)responsMap.get("data");
                if (dataMap != null){
                    Map<String, Object> blockMap = ((List<Map<String, Object>>)dataMap.get("block")).get(0);
                    List<Map<String, Object>> lineMap = (List<Map<String, Object>>)blockMap.get("line");

                    for (Map<String, Object> tempLineMap: lineMap) {
                        for (Map tempWordMap :(List<Map>)tempLineMap.get("word")) {
                            if (tempWordMap.get("content").toString().startsWith(": ")){
                                tempWordMap.put("content",tempWordMap.get("content").toString().replaceAll(": " ,""));
                            }
                            if (tempWordMap.get("content").toString().startsWith("：")){
                                tempWordMap.put("content",tempWordMap.get("content").toString().replaceAll("：" ,""));
                            }
                            if (tempWordMap.get("content").toString().length() == 16 && tempWordMap.get("content").toString().matches("^[0-9b]*$")){
                                result = ResultGenerator.genSuccessResult();
                                result.setData(tempWordMap.get("content").toString());
                                break ;
                            }
                        }
                    }
                }
            }
            else {
                logger.error("讯飞接口返回错误:  " + responsMap.get("code").toString() + responsMap.get("desc"));
                result = ResultGenerator.genFailResult("讯飞接口返回错误");
                result.setCode(Integer.parseInt(responsMap.get("code").toString()));
            }
        } else {
            logger.error("讯飞接口调用错误");
            result = ResultGenerator.genFailResult("讯飞接口调用错误");
        }

        return result;
    }


    //OCR
    public static Result xunfeiOcr(String imageUrl) throws UnsupportedEncodingException {

        Result imgResult = creatLocalFile(imageUrl);

        if (imgResult.getCode() == ResultCode.SUCCESS.code){
            InputStream imageStream = (InputStream) imgResult.getData();
            imgResult = xunfeiOcr(imageStream);
        }

        return imgResult;
    }
}
