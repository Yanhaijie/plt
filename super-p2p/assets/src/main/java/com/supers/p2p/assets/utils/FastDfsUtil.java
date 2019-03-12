package com.supers.p2p.assets.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.util.Map;

/**
 * Created by admin on 2018-01-16.
 */
@Component
public class FastDfsUtil implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastDfsUtil.class);

    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient1 storageClient1 = null;
    public  static String fastDfsHost="http://192.168.1.50/";

    private static String defaultGroupName="group1";

    /**
     * 程序启动后执行
     * @param args
     * @throws Exception
     */
    @Override
    public  void  run(String... args) throws Exception{
        LOGGER.info(">>>>>>>>>>>>>>>初始化fastDfs Client<<<<<<<<<<<<<");
        FastDfsUtilInit();
    }
    /**
     * 初始化客户端
     */
    public  void  FastDfsUtilInit(){
        try{
            ClientGlobal.initByProperties("application-prd.properties");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
        }catch (Exception ex){
            LOGGER.error("fastdfs-client.properties loader exception:"+ex.getMessage());
        }
    }
    /**
     * 获取上传client1
     * @return
     */
    private static     StorageClient1 getClient1() throws  Exception{
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient1 = new StorageClient1(trackerServer, storageServer);
        return  storageClient1;
    }
    /**
     * 上传文件
     * @param fileBytes 文件流
     * @param extName 后缀名
     * @return
     */
    public static   String upload(byte[] fileBytes,String extName){
        return  upload(defaultGroupName,fileBytes,extName);
    }
    /**
     * 上传文件
     * @param groupName 组名
     * @param fileBytes 文件流
     * @param extName 后缀名
     * @return
     */
    public  static  String upload(String groupName,byte[] fileBytes,String extName){
        String resultUrl="";
        try{
            resultUrl=getClient1().upload_file1(fileBytes,extName,null);

        }catch (Exception ex){
            LOGGER.error("fastdfs-client upload file exception:"+ex.getMessage());
        }
        return  resultUrl;
    }
    /**
     * 上传BASE64编码图片
     * @param imgStr BASE64图片编码
     * @return
     */
    public  static  String  uploadByBase64Img(String imgStr,String extName){
        String result="";
        try {
            if(imgStr.equals(""))return "";
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(imgStr);
            return  upload(bytes,extName);

        }catch (Exception ex){
            LOGGER.error("fastdfs-client upload BASE64 IMG file exception:"+ex.getMessage());
        }
        return  result;
    }

    /**
     * 删除文件
     * @param groupName 组名
     * @param fileName 文件名
     * @return  0:文件删除成功，2：文件不存在 ，其它：文件删除出错
     */
    public static int  deleteFile(String groupName,String fileName){
        int result=2;
        try {
            result = getClient1().delete_file(groupName, fileName);
        }
        catch (Exception ex){
            LOGGER.error("fastdfs-client delete file exception:"+ex.getMessage());
            result=101;
        }
        return  result;
    }
    /**
     * 文件附加属性值
     * @param map
     * @return
     */
    public   NameValuePair[]  getMetalist(Map<String,String> map) {
        NameValuePair[] metaList=  new NameValuePair[4];

        return metaList;
    }

}