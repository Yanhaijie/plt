package com.supermoney.open.platform.services;

import com.alibaba.fastjson.JSON;
import com.supermoney.open.platform.entity.SBlackList;
import com.supermoney.open.platform.entity.SCallRecord;
import com.supermoney.open.platform.entity.SInterface;
import com.supermoney.open.platform.entity.SOcr;
import com.supermoney.open.platform.entity.vo.respond.BlacklistRespondVo;
import com.supermoney.open.platform.service.*;
import com.supermoney.open.platform.utils.AdvanceAIUtils;
import com.supermoney.open.platform.utils.Result;
import com.supermoney.open.platform.utils.ResultCode;
import com.supermoney.open.platform.utils.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdvanceAIService {

    @Value("${advance.ai.accessKey}")
    private String accessKey;
    @Value("${advance.ai.secretKey}")
    private String secretKey;

    @Resource
    private SCallRecordService sCallRecordService;
    @Resource
    private SOcrService sOcrService;
    @Resource
    private SBlackListService sBlackListService;
    @Resource
    private SInterfaceService sInterfaceService;
    @Resource
    private FileServer fileServer;


    @Transactional
    public Result identityOCR(String imgBase, String merchantId){

        //参数校验
        if (StringUtils.isBlank(imgBase)){
            return ResultGenerator.genResult(ResultCode.FILE_ERROR_EMPTY.code, ResultCode.FILE_ERROR_EMPTY.msg);
        }

        //保存图片
        Result result = fileServer.imgUpdate(imgBase);
        if (result.getCode() !=  ResultCode.SUCCESS.code){
            return ResultGenerator.genResult(ResultCode.FILE_ERROR_UPLOAD.code, ResultCode.FILE_ERROR_UPLOAD.msg);
        }
        String imgUrl = ((Map<String,String>)result.getData()).get("fileName");

        //ocr识别
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(Base64.decodeBase64(URLDecoder.decode(imgBase,"utf-8").split(",")[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = AdvanceAIUtils.identityOCR(inputStream,accessKey,secretKey);

        //查询接口
        SInterface sInterface = sInterfaceService.getInterface(2);

        //添加调用记录
        SCallRecord callRecord = new SCallRecord();
        callRecord.setInterfaceId(sInterface.getId());
        callRecord.setMerchantId(merchantId);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ocr_img",imgUrl);
        callRecord.setParamJson(JSON.toJSONString(paramMap));

        if (result.getCode() == ResultCode.SUCCESS.code){
            //ocr记录
            Map<String, Object> tempMap = (Map<String, Object>)result.getData();
            SOcr sOcr = new SOcr();
            sOcr.setSource(sInterface.getSource());
            sOcr.setNik(tempMap.get("idNumber").toString());
            sOcr.setName(tempMap.get("name").toString());
            sOcr.setBloodType(tempMap.get("bloodType").toString());
            sOcr.setReligion(tempMap.get("religion").toString());
            sOcr.setGender(tempMap.get("gender").toString());
            sOcr.setPlaceDateBirth(tempMap.get("birthPlaceBirthday").toString());
            sOcr.setProvince(tempMap.get("province").toString());
            sOcr.setCity(tempMap.get("city").toString());
            sOcr.setRegion(tempMap.get("district").toString());
            sOcr.setVillage(tempMap.get("village").toString());
            sOcr.setRtRw(tempMap.get("rtrw").toString());
            sOcr.setAddress(tempMap.get("address").toString());
            sOcr.setOccupation(tempMap.get("occupation").toString());
            sOcr.setExpiryDate(tempMap.get("expiryDate").toString());
            sOcr.setNationality(tempMap.get("nationality").toString());
            sOcr.setMaritalStatus(tempMap.get("maritalStatus").toString());
            sOcr.setIdentityInfoStatus(0);
            sOcrService.save(sOcr);

            callRecord.setRequestStatus(0);
            callRecord.setResultId(sOcr.getId());

        }
        else {
            callRecord.setRequestStatus(1);
        }

        callRecord.setRespondJson(JSON.toJSONString(result));
        sCallRecordService.save(callRecord);

        return result;
    }


    @Transactional
    public Result blacklistCheck(String name,String idNumber,String phoneNumber, String merchantId){
        Result result;

        //参数校验
        if (StringUtils.isBlank(name)){
            return ResultGenerator.genResult(ResultCode.PARAM_EMPTY_NAME.code ,ResultCode.PARAM_EMPTY_NAME.msg);
        }
        else if (StringUtils.isBlank(idNumber)){
            return ResultGenerator.genResult(ResultCode.PARAM_EMPTY_ID.code ,ResultCode.PARAM_EMPTY_ID.msg);
        }
        else if (StringUtils.isBlank(phoneNumber)){
            return ResultGenerator.genResult(ResultCode.PARAM_EMPTY_PHONE.code ,ResultCode.PARAM_EMPTY_PHONE.msg);
        }

        //查询接口
        SInterface sInterface = sInterfaceService.getInterface(1);

        //添加调用记录
        SCallRecord callRecord = new SCallRecord();
        callRecord.setInterfaceId(sInterface.getId());
        callRecord.setMerchantId(merchantId);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("idNumber", idNumber);
        paramMap.put("phoneNumber", phoneNumber);
        callRecord.setParamJson(JSON.toJSONString(paramMap));

        //本地查询
        Map<String, Object> param = new HashMap<>();
        param.put("idNumber",idNumber);
        param.put("phoneNumber",phoneNumber);
        List<SBlackList> lists = sBlackListService.selectBlackListByParam(param);
        if (lists != null && lists.size() > 0){
            callRecord.setRequestStatus(0);
            result = ResultGenerator.genSuccessResult(new BlacklistRespondVo(lists));
        }
        //advance.ai 查询
        else {
            result = AdvanceAIUtils.blacklistCheck(name,idNumber,phoneNumber,accessKey,secretKey);

            if (result.getCode() == ResultCode.SUCCESS.code) {
                //黑名单记录
                Map<String, Object> tempMap = (Map<String, Object>) result.getData();
                if (result.getCode() == ResultCode.SUCCESS.code && tempMap.get("result").equals(true)) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tempMap.get("defaultListResult");
                    List<SBlackList> resultList = new ArrayList<>();
                    for (Map<String, Object> temp : list) {
                        SBlackList sBlackList = new SBlackList();
                        sBlackList.setCallRecordId(callRecord.getId());
                        sBlackList.setIdNumber(idNumber);
                        sBlackList.setName(name);
                        sBlackList.setPhoneNumber(phoneNumber);
                        sBlackList.setRecommendation(temp.get("recommendation").toString());
                        sBlackList.setEventTime(temp.get("eventTime").toString());
                        sBlackList.setHitReason(temp.get("hitReason").toString());
                        sBlackList.setProductType(temp.get("productType").toString());
                        sBlackList.setReasonCode(temp.get("reasonCode").toString());
                        sBlackListService.save(sBlackList);
                        resultList.add(sBlackList);
                    }
                    callRecord.setRequestStatus(0);
                    result = ResultGenerator.genSuccessResult(new BlacklistRespondVo(resultList));
                }
                else {
                    result = ResultGenerator.genFailResult("未命中");
                    callRecord.setRequestStatus(1);
                }
            }
            else {
                callRecord.setRequestStatus(1);
            }
        }

        callRecord.setRespondJson(JSON.toJSONString(result));
        sCallRecordService.save(callRecord);

        return result;
    }

}
