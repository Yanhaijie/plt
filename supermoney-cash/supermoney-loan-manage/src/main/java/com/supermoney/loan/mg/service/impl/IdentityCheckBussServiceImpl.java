package com.supermoney.loan.mg.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.mg.entity.SAtIdentity;
import com.supermoney.loan.mg.entity.SBussLabel;
import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.service.*;
import com.supermoney.loan.mg.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class IdentityCheckBussServiceImpl implements IdentityCheckBussService,AddMessageQueueTemplate<SAtIdentity> {

    private static final Logger logger = LoggerFactory.getLogger(IdentityCheckBussServiceImpl.class);

    private AddMessageQueueUtil messageQueueUtil = null;


    @Value("${advance.ai.accessKey}")
    private String accessKey;

    @Value("${advance.ai.secretKey}")
    private String secretKey;

    @Autowired
    private SAtBlackListService sAtBlackListService;

    @Autowired
    private SAtIdentityService sAtIdentityService;

    @Autowired
    private SLoanOrderService sLoanOrderService;

    @Autowired
    private SBlackListService sBlackListService;

    @Autowired
    private SUserService userService;

    @Autowired
    private SBussLabelService sBussLabelService;

    @Override
    public void addObject(List<SAtIdentity> obj){
        initMessageMQ();
        try {
            messageQueueUtil.addObject(obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObject(SAtIdentity obj){
        initMessageMQ();
        try {
            messageQueueUtil.addObject(obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void doBlackListCheck(){
        List<SLoanOrder> orderList = sLoanOrderService.selectBlackListOrderList();
        logger.info("=============================黑名单查询=============================" + orderList.size());
        for (SLoanOrder loanOrder :orderList ){
            SAtIdentity object = sAtIdentityService.getSuccessIdentity(loanOrder.getUserId());
            //判断是否在黑名单中
            if (object != null && object.getInBlacklist() == null){
                SUser user = userService.findById(object.getUserId());
                int bool = sBlackListService.blackListCheck1(user.getUserName());
                if (bool == 0){
                    bool = sAtBlackListService.blackListCheck2(object.getUserId(), object.getRealName(), object.getIdNumber(), user.getUserName());
                }

                if (bool == 1){
                    object.setInBlacklist(1);
                    sAtIdentityService.update(object);
                    //审核失败  备注
                    sLoanOrderService.audit(loanOrder.getId(),2,"Maaf, Pengajuan pinjaman anda gagal.");
                    logger.info( "命中黑名单 userId：" + object.getUserId() + "     orderId：" + loanOrder.getId());
                }
                else if (bool == 0){
                    object.setInBlacklist(0);
                    sAtIdentityService.update(object);
                    logger.info("未命中黑名单 userId：" + object.getUserId() + "     orderId：" + loanOrder.getId());
                }
                else if (bool == -2){
                    logger.error("Advance.ai 账户余额不足！  userId：" + object.getUserId() + "     orderId：" + loanOrder.getId() );
                }
                else {
                    logger.info("黑名单查询出错！ userId：" + object.getUserId() + "     orderId：" + loanOrder.getId() );
                }
            }
        }
    }

    @Override
    public void doCheck() {
        //查询未审核列表
        Map<String, Object> map1 = new HashMap<>();
        map1.put("identityStatus","0");
        map1.put("certType","1");
        map1.put("ocrCheck","0");
        List<SAtIdentity> list = sAtIdentityService.getList(map1);

        if (list != null && list.size() > 0){
            //每次最多100条
            if (list.size() > 100){
                list = list.subList(0,100);
            }

            //判断是否超过今日OCR验证限制
            SBussLabel bussLabel = sBussLabelService.getByName("OCRCount");
            JSONObject OCRCountJson = JSONObject.parseObject(bussLabel.getBussVal());

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date timeoutDate = dateFormat.parse(OCRCountJson.get("callDate").toString());
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(timeoutDate);

                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(new Date());

                if (calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)){

                }
                else {
                    OCRCountJson.put("advanceCount","0");
                    OCRCountJson.put("callDate",dateFormat.format(new Date()));
                    bussLabel.setBussVal(OCRCountJson.toJSONString());
                    sBussLabelService.update(bussLabel);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

            if (Integer.parseInt(OCRCountJson.get("advanceCount").toString()) < Integer.parseInt(OCRCountJson.get("advanceMaxCount").toString())){
                addObject(list);
                logger.info("=============================identity check begin=============================" + list.size());
            }
        }
    }

    private void initMessageMQ(){
        if (messageQueueUtil == null){
            messageQueueUtil = new AddMessageQueueUtil(this,1);
            try {
                messageQueueUtil.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void consumer(SAtIdentity object){
        object = sAtIdentityService.findById(object.getId());
        //身份证官网查询
//        if (object.getIdCheck() == null || object.getIdCheck() == 0){
//            GoIdVo goIdVo = goIdService.getGoIdByIdNumber(object.getIdNumber());
//            if (goIdVo == null){
//                object.setIdCheck(2);
//            }
//            else {
//                String name = goIdVo.getNama().replaceAll(" ","").toUpperCase();
//                String localName = object.getRealName().replaceAll(" ","").toUpperCase();
//                if (!name.equals(localName)){
//                    object.setIdCheck(2);
//                }
//                else {
//                    object.setIdCheck(1);
//
//                }
//            }
//
//            sAtIdentityService.update(object);
//        }

        //OCR
        if (object.getOcrCheck() == null || object.getOcrCheck() == 0){
            //OCR
            try {
                Result tempResult = XunfeiOCRUtil.creatLocalFile(object.getImgFront());
                if (tempResult.getCode() == ResultCode.SUCCESS.code){
                    InputStream imageInputStream = (InputStream)tempResult.getData();
                    tempResult = XunfeiOCRUtil.xunfeiOcr(imageInputStream);
                    if (tempResult.getCode() == ResultCode.SUCCESS.code){
                        String idNumber = (String) tempResult.getData();
                        if(idNumberCompare(idNumber,object.getIdNumber())){
                            object.setOcrCheck(1);
                            object.setIdentityStatus(3);
                        }
                        else {
                            object.setOcrCheck(2);
                            object.setIdentityStatus(0);
                        }
                        sAtIdentityService.update(object);
                    }
                    //讯飞调用量到达当日上限
                    else if (tempResult.getCode() == 11201) {
                        SBussLabel bussLabel = sBussLabelService.getByName("OCRCount");
                        JSONObject OCRCountJson = JSONObject.parseObject(bussLabel.getBussVal());

                        //Advance.ai 单日500次
                        if (Integer.parseInt(OCRCountJson.get("advanceCount").toString()) < Integer.parseInt(OCRCountJson.get("advanceMaxCount").toString())){
                            tempResult = AdvanceAiUtil.identityOCR(imageInputStream,accessKey,secretKey);
                            OCRCountJson.put("advanceCount",(Integer.parseInt(OCRCountJson.get("advanceCount").toString()) + 1) + "");
                            bussLabel.setBussVal(OCRCountJson.toJSONString());
                            sBussLabelService.update(bussLabel);

                            if (tempResult.getCode() == ResultCode.SUCCESS.code){
                                String idNumber = (String)((Map<String, Object>) tempResult.getData()).get("idNumber");
                                if(idNumberCompare(idNumber,object.getIdNumber())){
                                    object.setOcrCheck(1);
                                    object.setIdentityStatus(3);
                                }
                                else {
                                    object.setOcrCheck(2);
                                    object.setIdentityStatus(0);
                                }
                                sAtIdentityService.update(object);
                            }
                            //Advance.ai 欠费
                            else if (tempResult.getCode() == 201){
                                OCRCountJson.put("advanceCount",OCRCountJson.get("advanceMaxCount"));
                                bussLabel.setBussVal(OCRCountJson.toJSONString());
                                sBussLabelService.update(bussLabel);
                                logger.error("Advance.ai 欠费 请充值！！");
                            }
                            else{
                                object.setIdentityStatus(4);
                                object.setOcrCheck(2);
                                sAtIdentityService.update(object);
                                logger.error(tempResult.getMessage());
                            }
                        }
                        else {
                            logger.error("Advance.ai 超过单日限额");
                        }
                    }
                    else if (tempResult.getCode() == ResultCode.FAIL.code) {
                        object.setIdentityStatus(4);
                        object.setOcrCheck(2);
                        sAtIdentityService.update(object);
                        logger.error(tempResult.getMessage());
                    }
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        logger.info("_____do object even__________id:"+ object.getId());
    }

    @Override
    public Boolean stop(List<SAtIdentity> baseData, int i) {
        return false;
    }

    private static Boolean idNumberCompare(String idNumber1, String idNumber2){
        if (!idNumber1.matches("^[0-9]*$")){
            idNumber1 = idNumber1.replaceAll("b","6");
        }
        int sameCount = 0;
        char[] idChar1 = idNumber1.toCharArray();
        char[] idChar2 = idNumber2.toCharArray();
        for (int i = 0; i < 16; i++) {
            if(idChar1[i] == idChar2[i]){
                sameCount++;
            }
        }
        logger.info("ocrIdnumber:"+ idNumber1 + "          idNumber:" + idNumber2);
        if (sameCount >= 16){
            return true;
        }
        return false;
    }
}
