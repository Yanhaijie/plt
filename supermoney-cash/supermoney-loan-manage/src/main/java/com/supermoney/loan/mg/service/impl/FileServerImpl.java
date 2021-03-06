package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.service.FileServer;
import com.supermoney.loan.mg.utils.AppProperties;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import com.supermoney.util.UploadUtil;

/**
 * Created by admin on 2018-02-12.
 */
@Service
public class FileServerImpl implements FileServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServerImpl.class);
    @Autowired
    private AppProperties appProperties;


    /**
     * 上传文件
     * @param file
     * @param type
     * @return
     */
    public Result fileUpdate(MultipartFile file, String type){
        try {
            if (file.isEmpty()) {
                return ResultGenerator.genFailResult("文件不能为空");
            }
            String returnUrL = "";   //返回图片地址
            String fileName = file.getOriginalFilename();// 获取文件名
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1); // 获取文件的后缀名
            //过滤图片类型
            if(!hasSuffix(suffixName,type)){
                return  ResultGenerator.genFailResult("file suffixName faild!");
            }
            //过滤图片大小
            if(!hasSize(file.getSize(),type)){
                return  ResultGenerator.genFailResult("file size faild!");
            }
            //图片转为流
            String upfileName= UploadUtil.upload(file.getBytes(), suffixName,appProperties.getBucketname(),appProperties.getCountry()
                    ,appProperties.getPath(),appProperties.getEndpoint(),appProperties.getAccesskey(),appProperties.getAccesskeysecret());
            if(StringUtils.isBlank(upfileName)){
                return  ResultGenerator.genFailResult("upload file faild!");
            }

            Map<String,String> param=new HashMap<>();
            param.put("fileName",upfileName);
            param.put("url",upfileName);
            LOGGER.info("fileUrl:"+upfileName);
            return ResultGenerator.genSuccessResult(param);
        } catch (Exception ex) {
            return ResultGenerator.genFailResult("上传异常:" + ex.getMessage());
        }
    }

    /**
     * 上传base64img
     * @param baseStr
     * @return
     */
    public Result imgUpdate(String  baseStr){
        try {
            String[] ary=baseStr.split(",");
            int s=ary[0].indexOf("image/")+6;
            int e=ary[0].indexOf(";");
            String suffixName=ary[0].substring(s,e);
            baseStr=ary[1];
            if (StringUtils.isBlank(baseStr)) {
                return ResultGenerator.genFailResult("base64Str is null");
            }
            //过滤图片大小
            if(!hasSize(Long.valueOf(baseStr.length()),"baseStr")){
                return  ResultGenerator.genFailResult("base64Str size faild!");
            }
            String upfileName= UploadUtil.upload(baseStr.getBytes(),suffixName,appProperties.getBucketname(),appProperties.getCountry()
                    ,appProperties.getPath(),appProperties.getEndpoint(),appProperties.getAccesskey(),appProperties.getAccesskeysecret());
            if(StringUtils.isBlank(upfileName)){
                return  ResultGenerator.genFailResult("upload file faild!");
            }

            Map<String,String> param=new HashMap<>();
            param.put("fileName",upfileName);
            param.put("url",upfileName);
            return ResultGenerator.genSuccessResult(param);
        } catch (Exception ex) {
            return ResultGenerator.genFailResult("上传异常:" + ex.getMessage());
        }
    }

    /**
     * 后缀名是否符合
     * @param suffixName
     * @param type
     * @return
     */
    public  boolean hasSuffix(String suffixName,String type){
        if(StringUtils.isBlank(type))return  false;

        return  true;
    }
    /**
     * 文件大小是否符合
     * @param size
     * @param type
     * @return
     */
    public  boolean hasSize(Long size,String type){
        if(StringUtils.isBlank(type))return  false;
        int k=1024;
        if(type=="baseStr" && (size/k)>32000){
            return  false;
        }

        return  true;
    }

}
