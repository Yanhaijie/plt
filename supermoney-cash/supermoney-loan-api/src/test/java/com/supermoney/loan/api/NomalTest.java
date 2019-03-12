package com.supermoney.loan.api;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.SpringApplication;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class NomalTest {

    public static void main(String[] args) {
//        System.out.println(compeToVersion("2.0.5","2.0.5"));
//        System.out.println(compeToVersion("2.0.5","2.0.6"));
//        System.out.println(compeToVersion("2.0.5","2.0.3"));
//        System.out.println(compeToVersion("2.0.5",""));
//        System.out.println(compeToVersion("2.0.5","2.0.3.8"));
//        System.out.println(compeToVersion("2.0.5.9","2.0.3"));
        sha256_hash_hmac();
    }

    public static boolean compeToVersion(String version,String appversion){
        String[] versionArray=version.split("\\.");
        String[] appvercionArray=appversion.split("\\.");
        if(versionArray.length!=appvercionArray.length){
            return  false;
        }
        int maxVal=5;
        if(versionArray.length>maxVal){
            return  false;
        }
        int a=0,b=0;
        for(int i=0;i<versionArray.length;i++){
             a+=(Integer.valueOf(versionArray[i])+1)*Math.pow(10,(maxVal-i));
             b+=(Integer.valueOf(appvercionArray[i])+1)*Math.pow(10,(maxVal-i));
        }
        return a>b;
    }

    public static  void sha256_hash_hmac(){
        try {
            String secret = "4fddfdb37d178a9489b8494be3f9a6ca";// 加密使用的key
            String message = "AQBiDt7Q-XckzIwPTu29It4GbdPffIrN8UoU3I_R0ZwbcjePgAauAJAy5y0Zw9CR8sjuvCiWD2No7gu3LnxD8swy8vH-50jMqCk4XBfhzHsoG8O9X82XS4UuzQgp13dl-U23IXF6uBwak8oMincTuUONjU5iaA0iSwE-2TmmVVonoW3B0rFfmuk6utUnJ5Dg6U5z-spDGLsM8WUZDVxLN5Z9wwAQf6lWJ0EHWxAyEK73slC1W4unkTc6RMnUHUnT5I0xTG8ZrUWzS6ODWvTLaG5U";// 需要加密的字符串（本项目是 "{uuid}_{timestamp}" ）

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));// 重点
            System.out.println(hash);
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }

}
