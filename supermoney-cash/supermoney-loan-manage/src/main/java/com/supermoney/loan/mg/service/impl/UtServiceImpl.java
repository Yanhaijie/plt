package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.service.UtService;
import com.supermoney.loan.mg.utils.ExcelUtil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.sms.Constants;
import com.supermoney.sms.SmsClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtServiceImpl implements UtService {

    private static final Logger logger = LoggerFactory.getLogger(UtService.class);

    /**
     * 导入文件处理
     * @param file
     * @return
     */
    @Override
    public Result importNumbers(MultipartFile file){
        try {
            if (file.isEmpty()) {
                return ResultGenerator.genFailResult("文件不能为空");
            }
            String returnUrL = "";   //返回图片地址
            String fileName = file.getOriginalFilename();// 获取文件名
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1); // 获取文件的后缀名
            //过滤图片类型
            if(suffixName.equals("xls")){
                //return  ResultGenerator.genFailResult("file suffixName faild!");
            }
            //过滤图片大小
            if(file.getSize()>20000){
                //return  ResultGenerator.genFailResult("file size faild!");
            }
            //读取Excel
            List<Object> list= ExcelUtil.getListByExcel(file.getInputStream(),fileName,0,1);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception ex) {
            return ResultGenerator.genFailResult("上传异常:" + ex.getMessage());
        }
    }
    /**
     * 推送营销
     * @param numbers
     * @param content
     * @return
     */
    @Override
    public  Result pushMsg(String[] numbers,String content){
        if(numbers.length<1){
            return ResultGenerator.genFailResult("电话号码不能为空!");
        }
        if(StringUtils.isBlank(content)||StringUtils.isEmpty(content) || content.length()<20){
            return  ResultGenerator.genFailResult("发送内容不能为空,大于10个字符!");
        }
        SmsClient sms=new SmsClient();
        //线程异步处理
        new Thread(){
            public  void  run(){
                for(String number:numbers){
                    String mobile=addAreaCodeMobile(number);
                    sms.sendMsg(mobile,content, Constants.PRODUCT_PAASOO_MARKETING);
                    logger.info("send pushmsg:"+mobile+"|"+content);
                }
            }
        }.start();
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 号码添加区号
     * @param mobile
     * @return
     */
    public  String  addAreaCodeMobile(String mobile){
        String code="62";
        //08开头，印尼
        if(mobile.indexOf("08")==0){
            mobile=mobile.substring(1,mobile.length());
            mobile=code+mobile;
        }
        return  mobile;
    }
}
