package com.supermoney.loan.mg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.supermoney.loan.mg.service.GoogleapisService;
import com.supermoney.loan.mg.utils.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Service
public class GoogleapisServiceImpl  implements GoogleapisService{

    private static final Logger logger = LoggerFactory.getLogger(GoogleapisServiceImpl.class);

    private  static   String[]  SCOPES=new String[]{"https://www.googleapis.com/auth/firebase.messaging"};

    /**
     * 授权账号 - service account
     * @param appName
     * @return
     */
    public static InputStream serviceAccount(String appName){
        String json=null;
        if(appName.equals("superpinjam")){
          json="{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"superpinjam\",\n" +
                "  \"private_key_id\": \"fb0fd318b89c7030b4cb9367e2efe9cad08f507a\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaHTn03nvmdPSr\\n0mVioEe5uNh/YRJkxxCJl7LTjMMt2oityZNOsVnAoHWg0ev9tmPVeu3EWx+5SA/S\\ncupxzpKhK6X4sXCpWgWG3UsiYBgBFJBUO5JsO0pMrGgpVAvhjWmZVX/chQviupyX\\nFB3TOmrHjV2hczshJSnTjbHeAc/a5tHc7oTdIwzLgMo9ZIfJUtCk8XoA+9om/HRr\\nj+ITmNskFFZHZHq6iP/bvEqz8FodgGLlesMYSkM2nbsR8IggGyVIhwyHeUZF3cG7\\nHU2teC1wJ970L2a90MoCz37HWbjF4G2pWzuao9ECCB4Ta8pb/XqZcV2N3qtZbqyU\\nphEKu+3pAgMBAAECggEAIQKQluwOwuKbQ7BpqfHl2xiGOuZ6b56Z4rbdBWYZqlks\\nPHk4NbtUG6fi+iLstufZw3C0vAG+rkuKpAucPus9ygrU3ot3M7cGBwigXskRay3R\\n6lDmQIuEkx68otmPP+0vyLmhDQVscdCB2PRknQletMaJXxsU97kDl/46MnDV6i+A\\nNyI8MJ6F2Q7v0PDnipfkLHf7AKK1JGDhxMZYY4uxgqzQ0SMMANDm7icMcm0nAE4F\\nuqKiqtIOtxWpJoSQwk4hwKyjmQYvK2HbpCdI4rv4sGhT3T/ZaKFg0pqdGnRKq2DT\\naqe2R+GezG4oKOgj1E+eJkyS977qYKopzI3uoT2cDwKBgQDXTddSy91WhofdgDsl\\nFtu4urhV5mwF5HSwShESkd10F6zfIElS8uDT6bmYXxtP7VhtV0XC6Iwbufw7R4As\\nMmySgGLSF7J3fVpCd0IbcFgZLWaY5lEqlqcnTdZSCIJMTBjHJYiqSas70Clpa9FM\\nagnXZSA+mwg9+seGNHhOpXJB5wKBgQC3PoVizI1K01CleLJKPdUi1Q53oGFPmnV9\\neCCkO5Bvee3HtLMX4T62Zg1WBE409E+3BwwGrfu/pL6/MB+slVXeQjPs3Pb1arY7\\nTh/8KDEmdp7ND269suy8AsqMZ9zY0VzN7NZRBUDcgAGxA+m8aEOrtEo9oFO4qxU4\\nG2mVAD33rwKBgQDONebCjTFL+wPL+VIsfs0pC+UbmNrjdeDxK69YWs6Ar2BKQFEa\\nGjeNn7C7qHOvRMLJzqmpOTNgJuzXFe9D5XOpiT+cu0eaPDwjprdxMlOeoaY/UXBx\\n2uLzuRBJd47cZpHgwyk29+ICIqhXKQXIK1OLsnaEn9XwDu3kswJ7SOi7BwKBgQCF\\nHHaaoYrV2xFPUpD5G1eTeP+MaMWnpMVKQpQ7mUU/pWvUUXBznV0H9LQlUvVYxEyP\\n4R+cTpB803j4u0WXAdghnyfHHXCMMFt2Uzcq3ituDGcpANfqF2qe3Mw0GFK2ccef\\nkzbQHifSxW31H6mQApF9znbLs4Z9tFGysz8MTOO24wKBgFbhsfy55RfYsEOnBcqP\\nCnkzp211dCyHejeBh1Epyzh/UVe3jXfcanDdnKORajFu6YeB1wsYx+c7ldZL1WxU\\n4zMsH8K/nwQFo4ed1OTUjZMLddcl9TGbWtLUr6HTSbF65u9RDGJVuNychY/4aVaZ\\nCbAOdbQshloZE94//7IXBurC\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"firebase-adminsdk-sa8bs@superpinjam.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"113207661861205874282\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-sa8bs%40superpinjam.iam.gserviceaccount.com\"\n" +
                "}";
        }else {
            logger.error("GoogleapisService-serviceAccount: have not appName");
            return  null;
        }
        return  new ByteArrayInputStream(json.getBytes());
    }
    /**
     * 获取授权TOKEN
     * @return
     * @throws IOException
     */
    private static String getAccessToken(String appName) throws IOException {
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(serviceAccount(appName))
                .createScoped(Arrays.asList(SCOPES));
        googleCredential.refreshToken();
        logger.info("googleapis-getAccessToken:"+googleCredential.getAccessToken());
        return googleCredential.getAccessToken();
    }
    /**
     * 推送消息
     * @param appName 应用名称
     * @param title 标题
     * @param body 内容
     * @param pushTo 推送对象: 设备ID 标签名称  标签表达式
     * @param pushType 推送类型:  0设备推送 1标签推送 2推送所有用户
     */
    @Override
    public void sendMsgToDevice(String appName,String title,String body,String pushTo,Integer pushType){

        if(StringUtils.isBlank(appName) || StringUtils.isBlank(title) || StringUtils.isBlank(body)){
            logger.info("sendMsgToDevice-agument-faild: that appName or title or body ,someone is null!");
            return;
        }

        if(StringUtils.isBlank(pushTo) && !(Constants.Google.PUSH_ALL_USER.equals(pushType))){
            logger.info("sendMsgToDevice-agument-faild: pushTo is null!");
            return;
        }

        //https://fcm.googleapis.com/v1/projects/<应用名称>/messages:send
        String url="https://fcm.googleapis.com/v1/projects/"+appName+"/messages:send";

        JSONObject notification=new JSONObject();
        notification.put("body",body);
        notification.put("title",title);
        JSONObject message=new JSONObject();


        //分组发送优先,单个设备发送其后
        if(Constants.Google.PUSH_FLAG.equals(pushType)){
            message.put("topic",pushTo);
        }else if(Constants.Google.PUSH_DIVICE.equals(pushType)) {
            message.put("token",pushTo);
        }else if(Constants.Google.PUSH_CONDITION.equals(pushType)){
            message.put("condition",pushTo);
        }else  if (Constants.Google.PUSH_ALL_USER.equals(pushType)){
            message.put("condition","!( 'nothing893' in topics )");
        }

        message.put("notification",notification);
        JSONObject json=new JSONObject();
        json.put("message",message);

        String jsonStr=  apiPost(appName,url,json.toJSONString());
        logger.info("googleapis-sendMsgToDevice:"+jsonStr);
    }
    /**
     * API 请求
     * @param appName
     * @param url
     * @param jsonStr
     * @return
     */
    public String apiPost(String appName,String url, String jsonStr){
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            String token="Bearer " + getAccessToken(appName);
            logger.info("googleapis-Authorization:"+token);
            logger.info("googleapis-jsonStr:"+jsonStr);
            headers.add("Authorization", token);
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

            /**
             * 请求
             */
            //postForObject
            HttpEntity<String> requestEntity = new HttpEntity<String>(jsonStr, headers);
            ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  "";
    }
    /**
     * 发送测试用例
     * doc-url: https://firebase.google.com/docs/cloud-messaging/send-message
     */
    public  void  testCase(){
        String appName="superpinjam";
        //设备
       sendMsgToDevice(appName,"RON-DEVICE","it's message for device send!","cq_9Z9WtiqI:APA91bH__d65j5DbDDTAjjfHLunJmm-R5_j5RWwD9xdLb_dazuJxCs9af_xaPpxILKyLN9bCEMkixUXBHaCiAMgWezwaLvYIP9vOFvD_W50BPycy7-eaNWKM4f_bRZDE4DtrCkYgNMaO", Constants.Google.PUSH_DIVICE);
        //分组
      sendMsgToDevice(appName,"RON-FLAG","it's message for flag send!","news",Constants.Google.PUSH_FLAG);
        //条件:发送到music或者movie组
        sendMsgToDevice(appName,"RON-CONDITION","it's message for condition send!","'music' in topics || 'movie' in topics",Constants.Google.PUSH_CONDITION);
        //所有用户
        sendMsgToDevice(appName,"RON-ALLUSER","it's message for all users send!","",Constants.Google.PUSH_ALL_USER);
    }


}
