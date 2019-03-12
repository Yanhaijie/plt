package com.supermoney.loan.api;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

public class ApiTest {


     private  String FMT_DEV_ID="657260705023";
     private  String FMT_SERVICE_SCRET="AAAAmQfKdP8:APA91bHIk0383ol6V9tPJj_B5SPxj5C5Ri5u6syKPpafA52-cZZrvIms0nLuD4OvzkvkgBNJtXhtexsla2klVa6Y6ELI7-LDYi2036rHUmf_S7bdvLNVClWztIG_4Hp1lDSlgMN9MP1J";
     private  String FMT_SERVICE_OLD_SCRET="AIzaSyB-p9vPDg8r4CcFiI8lDATDQED60To9IWs";

    /**
     * 特定设备发送消息
     */
     public void sendMsgToDevice(){
         String url="https://fcm.googleapis.com/v1/projects/myproject-b5ae1/messages:send";

     }


    /**
     * API 请求
     * @param url
     * @param reqData
     * @return
     */
    public String apiPost(String url, Map<String,String> reqData){
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

           // headers.add("APISIGN", scretkeyToApiSign(secret_key));
           // headers.add("S-PUNIQUE-KEY", UUID.randomUUID().toString().replace("-", ""));
           // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
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
            return response.getBody();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  "";
    }
}
