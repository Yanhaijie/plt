package com.supermoney.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.util.UUID;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Calendar;
import com.supermoney.util.Md5Util;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UploadUtil
 * @Author: yanhaijie
 * @CreateDate: 2019-02-19 18:34
 * @Version: 1.0
 */
@Slf4j
public class UploadUtil {

//    private static String endpoint;
//
//    private static String accesskey;
//
//    private static String accesskeysecret;
//
//    private static String bucketname;
//
//    private static  String path;
//
//    private static String country;

//    private static String ENDPOINT;
//
//    private static String ACCESSKEY;
//
//    private static String ACCESSKEYSECRET;
//
//    private static String BUCKETNAME;
//
//    public static String getENDPOINT() {
//        return ENDPOINT;
//    }
//
//    public static String getACCESSKEY() {
//        return ACCESSKEY;
//    }
//
//    public static String getACCESSKEYSECRET() {
//        return ACCESSKEYSECRET;
//    }
//
//    public static String getBUCKETNAME() {
//        return BUCKETNAME;
//    }
//
//    public static String getPATH() {
//        return PATH;
//    }
//
//    public static String getCOUNTRY() {
//        return COUNTRY;
//    }
//
//    private static  String PATH;
//
//    private static String COUNTRY;
//
//    @Value("${conf.endpoint}")
//    public void setEndpoint(String endpoint) {
//        ENDPOINT= endpoint;
//
//    }
//
//    @Value("${conf.accesskey}")
//    public void setAccesskey(String accesskey) {
//        ACCESSKEY = accesskey;
//    }
//
//    @Value("${conf.accesskeysecret}")
//    public void setAccesskeysecret(String accesskeysecret) {
//        ACCESSKEYSECRET = accesskeysecret;
//    }
//
//    @Value("${conf.bucketname}")
//    public void setBucketname(String bucketname) {
//        BUCKETNAME = bucketname;
//    }
//
//    @Value("${conf.path}")
//    public void setPath(String path) {
//        PATH = path;
//    }
//
//    @Value("${conf.country}")
//    public void setCountry(String country) {
//        COUNTRY = country;
//    }

//    static {
//        endpoint=PropertiesUtil.get("conf.endpoint");
//        accesskey= PropertiesUtil.get("conf.accesskey");
//        accesskeysecret= PropertiesUtil.get("conf.accesskeysecret");
//        bucketname= PropertiesUtil.get("conf.bucketname");
//        path= PropertiesUtil.get("conf.path");
//        country= PropertiesUtil.get("conf.country");
//    }

//    public UploadUtil(String endpoint,String accesskey,String accesskeysecret,String bucketname,String path,String country){
//        this.endpoint=endpoint;
//        this.accesskey=accesskey;
//        this.accesskeysecret=accesskeysecret;
//        this.bucketname=bucketname;
//        this.path=path;
//        this.country=country;
//    }

    /**
     * 获取阿里云OSS客户端对象
     * @return ossClient
     */
    public static OSS getOSSClient(String endpoint,String accesskey,String accesskeysecret){
        return  new OSSClientBuilder().build(endpoint,accesskey,accesskeysecret);
    }


    public static void main(String[] args) {

    }
    /**
     * 创建文件夹
     * @param OSS
     * @param bucketName
     * @param extName
     * @param country
     * @return
     */
    public  static String createFolder(String extName,String country,String bucketname,OSS oss){
        Calendar calendar = Calendar.getInstance();
        String folder = new StringBuilder().append(country + "/").append(calendar.get(Calendar.YEAR) + "/")
                .append(calendar.get(Calendar.MONTH) + 1 + "/").append(calendar.get(Calendar.DATE) + "/").toString();
        String objectName=Md5Util.md5Hex(UUID.randomUUID().toString());

        //判断文件夹是否存在，不存在则创
        if (!oss.doesObjectExist(bucketname, folder)){
            //创建文件夹
            oss.putObject(bucketname, folder, new ByteArrayInputStream(new byte[0]));
            //得到文件夹名
            OSSObject object = oss.getObject(bucketname, folder);
            folder=object.toString();
            return folder+objectName+extName;
        }
        return folder+objectName+"."+extName;

    }


    /**
     *
     * @param fileBytes 文件
     * @param extName 后缀
     * @param bucketname 空间名称
     * @param country 国家
     * @param path 存放路径
     * @param endpoint
     * @param accesskey
     * @param accesskeysecret
     * @return
     */
    public  static String upload(byte[] fileBytes,String extName,String bucketname,String country,String path,String endpoint,String accesskey,String accesskeysecret){
        //获取OSS
        OSS oss=getOSSClient(endpoint,accesskey,accesskeysecret);
        //创建文件夹
        String folder=createFolder(extName,country,bucketname,oss);
        log.info("common-file=====UploadUtil=====upload=====folder:"+folder);
        try{
            oss.putObject(bucketname,folder,new ByteArrayInputStream(fileBytes));
            if(oss.doesObjectExist(bucketname,folder)){
                return path+folder;
            }
        }
        catch (RuntimeException e){
            e.printStackTrace();
            log.info("common-file=====UploadUtil=====upload:upload fail");
        }
        return "";
    }

}
