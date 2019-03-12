package com.supermoney.loan.api.utils;

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

public final class BaiduFaceCheckUtil{
    private static final Logger logger = LoggerFactory.getLogger(BaiduFaceCheckUtil.class);
    /**
     * 百度活体检测
     * @return
     */
    public static Result checkLivePeopleByByte(String livingUrl, byte[] faceBase64String,String access_token) {
        Result result;

        if (faceBase64String == null){
            throw new RuntimeException("Bese64 fail");
        }
        if (access_token == null) {
            throw new RuntimeException("get baidu access_token fail");
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
            if(responsMap.get("result") != null){
                Map resultMap = (Map)responsMap.get("result");
                if (resultMap.size() > 0){
                    Map thresholdsMap = (Map) resultMap.get("thresholds");
//                    BigDecimal face_liveness = (BigDecimal) resultMap.get("face_liveness");
//                    BigDecimal frr_1e_3 = (BigDecimal)thresholdsMap.get("frr_1e-3");
                    BigDecimal face_liveness=new BigDecimal(resultMap.get("face_liveness").toString());
                    BigDecimal frr_1e_3 =(BigDecimal) thresholdsMap.get("frr_1e-3");
                    if (face_liveness.compareTo(frr_1e_3) >= 0){
                        result = ResultGenerator.genSuccessResult(face_liveness);
                    }
                    else {
                        //不是活体
                        result = ResultGenerator.genFailResult("Foto identifikasi wajah bukan Orang Asli");
                    }
                }
                else {
                    //图片存在问题，无法检测到头像
                    result = ResultGenerator.genFailResult("Foto identifikasi wajah tidak jelas, sehingga tidak bisa dikenali");
                }
            }
            else {
                result = ResultGenerator.genFailResult(responsMap.get("error_msg").toString());
            }
        } else {
            result = ResultGenerator.genFailResult("baidu living fail ！");
        }


        return result;
    }

    public static Result baiduCompareByByte(String compareUrl ,byte[] idBase64String,byte[] faceBase64String ,String access_token) {
        Result result;

        if (faceBase64String == null || idBase64String == null){
            return ResultGenerator.genFailResult("Bese64 fail ！");
        }
        if (access_token == null) {
            return ResultGenerator.genFailResult("get baidu access_token fail！");
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

            if (resultMap != null && resultMap.size() > 0){
                Double score = Double.parseDouble(resultMap.get("score").toString()) ;
                if (score >= 50){
                    result = ResultGenerator.genSuccessResult();
                }
                else {
                    //人证不匹配
                    result = ResultGenerator.genFailResult("Foto identifikasi wajah tidak sesuai dengan foto KTP");
                }
            }
            else {
                //图片存在问题，无法检测到头像
                result = ResultGenerator.genFailResult("Foto wajah di KTP tidak jelas, sehingga tidak bisa dikenali");
            }
        } else {
            result = ResultGenerator.genFailResult("baidu compare fail ！");
        }
        return result;
    }

    //人脸检测
    public static Result baiduFaceCheck(InputStream imageStream,String access_token) throws UnsupportedEncodingException {
        Result result = new Result();

        String idBase64String = getBase64ByString(imageStream);

        if (idBase64String == null){
            return ResultGenerator.genFailResult("Bese64 fail ！");
        }
        if (access_token == null) {
            return ResultGenerator.genFailResult("get baidu access_token fail！");
        }

        String url = "http://aip.baidubce.com/rest/2.0/face/v2/detect?access_token=" + access_token;

        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
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

            List tempList = (List) responsMap.get("result");

            if (tempList.size() > 0){
                Map<String, Object> resultMap = (Map<String, Object>) tempList.get(0);
                //人脸置信度
                Double faceProbability = Double.parseDouble(resultMap.get("face_probability").toString()) ;
                if (faceProbability == 1){
                    result = ResultGenerator.genSuccessResult();
                }
                else {
                    result = ResultGenerator.genFailResult("检测人脸失败");
                }
            }
            else {
                result = ResultGenerator.genFailResult("图片存在问题，无法检测到头像");
            }
        } else {
            result = ResultGenerator.genFailResult("百度较验失败！");
        }

        return result;
    }

    /**
     * 获取access
     * @return
     */
    public static String getAccessToken(String baiduApiKey, String baiduSecretKey){
        String access_token = null;
        String url = "http://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + baiduApiKey + "&client_secret=" + baiduSecretKey;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<byte[]>(headers),
                byte[].class);
        if (response.getStatusCode() == HttpStatus.OK){
            byte[] responseData = response.getBody();
            String temp = new String(responseData);
            Map<String,Object> responsMap = (Map<String,Object>)JSON.parse(responseData);
            access_token = (String) responsMap.get("access_token");
        }

        return access_token;
    }

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
//            inputStream.close();
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
    private static Result creatLocalFile(String fileUrl){
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


            File file = new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\2018年5月\\RQX7Z{DNW@O0[PPZ~NG]9$L.png");
            FileInputStream idInputStream = new FileInputStream(file);
//            byte[] faceFileByte = FileUtil.readFileByFileStream(idInputStream);
            String base64 = getBase64ByString(idInputStream);
            base64 = URLEncoder.encode(base64,"utf-8");
            System.out.println(base64);
//            FileInputStream faceInputStream = new FileInputStream(new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\2018年5月\\OKTUDF5@BSX}SO8)R%BJ4RD.png"));

//            String access_token = getAccessToken("2dgGU9kZdDr1Ay6vTsBr5Fgl","ztHoYua5mX6dyy5d9gli7rGXaZdLBVEH");

//            pathList.add(file.getName());
//            xunfeiOcr(idInputStream);
//            result = checkLivePeopleByByte("https://aip.baidubce.com/rest/2.0/face/v3/faceverify",faceFileByte,access_token);
//            System.out.println(result);
//            result = baiduCompareByByte("https://aip.baidubce.com/rest/2.0/face/v3/match",FileUtil.readFileByFileStream(idInputStream),FileUtil.readFileByFileStream(faceInputStream),access_token);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
