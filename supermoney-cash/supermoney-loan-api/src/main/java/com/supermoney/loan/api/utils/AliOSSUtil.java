package com.supermoney.loan.api.utils;

import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
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
import org.bouncycastle.jcajce.provider.digest.MD2;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Calendar;
import com.supermoney.loan.api.utils.Md5Util;
/**
 * @ClassName: AliOSSUtil
 * @Author: yanhaijie
 * @CreateDate: 2019-02-19 18:34
 * @Version: 1.0
 */
@Slf4j
public class AliOSSUtil {


    public static String ENDPOINT = "oss-cn-zhangjiakou.aliyuncs.com";
    public static String ACCESSKEY="LTAIndEfNQC3Y6wq";
    public static String ACCESSKEYSECRET="zfIzHhVHRxm2kUzvOL07IdLcwQuuvl";
    public static String BUCKETNAME="supermoney";
    public static String PATH="https://supermoney.oss-cn-zhangjiakou.aliyuncs.com/";
    public static String COUNTRY="PHL";
    /**
     * 获取阿里云OSS客户端对象
     * @return ossClient
     */
    public static OSS getOSSClient(){
        return  new OSSClientBuilder().build(ENDPOINT,ACCESSKEY,ACCESSKEYSECRET);
    }


    public static void main(String[] args) {
        String a="https://supermoney.oss-cn-zhangjiakou.aliyuncs.com/PHL/2019/2/20/01ea4b71c58bb326754bcb3afad39e1ejpeg";
        System.out.println(a.length());
    }

    /**
     * 创建文件夹
     * @param ossClient
     * @param bucketName
     * @param folder
     * @return
     */
    public  static String createFolder(String extName){
        Calendar calendar = Calendar.getInstance();
        String folder = new StringBuilder().append(COUNTRY + "/").append(calendar.get(Calendar.YEAR) + "/")
                .append(calendar.get(Calendar.MONTH) + 1 + "/").append(calendar.get(Calendar.DATE) + "/").toString();
        String objectName=Md5Util.md5Hex(UUID.randomUUID().toString().replaceAll("-",""));
        //判断文件夹是否存在，不存在则创
        if (!getOSSClient().doesObjectExist(BUCKETNAME, folder)){
            //创建文件夹
            getOSSClient().putObject(BUCKETNAME, folder, new ByteArrayInputStream(new byte[0]));
            //得到文件夹名
            OSSObject object = getOSSClient().getObject(BUCKETNAME, folder);
            folder=object.toString();
            return folder+objectName+extName;
        }
        return folder+objectName+"."+extName;

    }

    public  static String upload(byte[] fileBytes,String extName){
        String folder=createFolder(extName);
        log.info("路径+名称"+folder);
        getOSSClient().putObject(BUCKETNAME,folder,new ByteArrayInputStream(fileBytes));
        try{
            if(getOSSClient().doesObjectExist(BUCKETNAME,folder)){
                return PATH+folder;
            }
        }
        catch (RuntimeException e){
            e.printStackTrace();
            log.info("图片上传异常");
        }
        return "";
    }
}
