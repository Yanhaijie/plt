package com.supermoney.loan.market.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.market.entity.SBussLabel;
import com.supermoney.loan.market.entity.SMerchantUserProduct;
import com.supermoney.loan.market.entity.SNikCallRecord;
import com.supermoney.loan.market.entity.SNikInfo;
import com.supermoney.loan.market.entity.respondVo.NIKCheckVo;
import com.supermoney.loan.market.service.SBussLabelService;
import com.supermoney.loan.market.service.SMerchantUserProductService;
import com.supermoney.loan.market.service.SNikCallRecordService;
import com.supermoney.loan.market.service.SNikInfoService;
import com.supermoney.loan.market.utils.*;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class NIKBussService {

    private static final Logger logger = LoggerFactory.getLogger(NIKBussService.class);

    @Resource
    private SNikInfoService sNikInfoService;

    @Resource
    private SNikCallRecordService sNikCallRecordService;

    @Resource
    private SMerchantUserProductService sMerchantUserProductService;

    @Resource
    private SBussLabelService sBussLabelService;

    @Value("${advance.ai.accessKey}")
    private String accessKey;
    @Value("${advance.ai.secretKey}")
    private String secretKey;

    private  String testMerchantId="992bdbe4c30048bda168d773908cb83b";

    @Transactional
    public Result check(String name, String nik, String merchantId) {

        if(merchantId.equals(testMerchantId)){
          return  ResultGenerator.genSuccessResult(checkTestData());
        }

        if (StringUtils.isBlank(name)){
            return ResultGenerator.genResult(ResultCode.NIK_PARAM_ERROR.code, ResultCode.NIK_PARAM_ERROR.msg);
        }

        if (StringUtils.isBlank(nik) || nik.length() != 16){
            return ResultGenerator.genResult(ResultCode.NIK_PARAM_ERROR.code, ResultCode.NIK_PARAM_ERROR.msg);
        }

        //商户产品次数校验
       SMerchantUserProduct merchantProduct=sMerchantUserProductService.getMerchantProduct(merchantId,"API_001");
        if(merchantProduct==null){
            return  ResultGenerator.genFailResult(" merchant have not product!");
        }
        Integer useCount=sNikCallRecordService.getCoutRecordBySuccess(merchantId);
        if(merchantProduct.getLastNum().compareTo(useCount)<0){
            logger.info("merchantId:"+merchantId+" useCount:"+useCount+" lastNum:"+merchantProduct.getLastNum());
            return  ResultGenerator.genFailResult(" product num  limit!");
        }

        SBussLabel bussLabel = sBussLabelService.getByLabelName("nikSourceTag");
        JSONObject jsonObject = JSON.parseObject(bussLabel.getBussVal());
        Integer sourceTag = Integer.parseInt(jsonObject.getString("sourceTag"));

        SNikCallRecord nikCallRecord = new SNikCallRecord();
        nikCallRecord.setMerchantId(merchantId);
        nikCallRecord.setNik(nik);
        nikCallRecord.setCallStatus(1);
        nikCallRecord.setSourceTag(sourceTag);
        sNikCallRecordService.save(nikCallRecord);

        SNikInfo nikInfo = sNikInfoService.selectNikInfoByNik(nik);
        Result result;
        if (nikInfo == null){
            Result tempResult;
            if (sourceTag == 0){
                tempResult = AdvanceAIUtils.idCheck(name ,nik,accessKey,secretKey);
            }
            else {
                tempResult = NIKCheckUtils.checkNIK(nik);
            }


            if (tempResult.getCode() == ResultCode.SUCCESS.code){
                JSONObject object = (JSONObject) tempResult.getData();

                SNikInfo newNikInfo = new SNikInfo();
                newNikInfo.setNikId(UUID.randomUUID().toString().replaceAll("-",""));

                if (sourceTag == 0){
                    newNikInfo.setName(NomalUntil.josnValEmpty(object,"name"));
                    newNikInfo.setNik(NomalUntil.josnValEmpty(object,"idNumber"));
                    newNikInfo.setBod("");
                    newNikInfo.setAddress(
                            NomalUntil.josnValEmpty(object,"province") + NomalUntil.josnValEmpty(object,"city") +
                                    NomalUntil.josnValEmpty(object,"district") + NomalUntil.josnValEmpty(object,"village"));
                    newNikInfo.setCreatedAt("");
                    newNikInfo.setUpdatedAt("");
                }
                else {
                    newNikInfo.setName(NomalUntil.josnValEmpty(object,"name"));
                    newNikInfo.setNik(NomalUntil.josnValEmpty(object,"nik"));
                    newNikInfo.setBod(NomalUntil.josnValEmpty(object,"bod"));
                    newNikInfo.setAddress(NomalUntil.josnValEmpty(object,"address"));
                    newNikInfo.setCreatedAt(NomalUntil.josnValEmpty(object,"created_at"));
                    newNikInfo.setUpdatedAt(NomalUntil.josnValEmpty(object,"updated_at"));
                }

                newNikInfo.setSourceTag(sourceTag);
                sNikInfoService.save(newNikInfo);

                result = ResultGenerator.genSuccessResult(NIKCheckVo.createVo(newNikInfo));

                nikCallRecord.setNikId(newNikInfo.getNikId());
                nikCallRecord.setCallStatus(0);
                sNikCallRecordService.update(nikCallRecord);
            }
            else if(ResultCode.NIK_ERROR.code == tempResult.getCode()){
                result = tempResult;
            }
            else {
                result = ResultGenerator.genResult(ResultCode.FAIL.code, ResultCode.FAIL.msg);
            }
        }
        else {
            result = ResultGenerator.genSuccessResult(NIKCheckVo.createVo(nikInfo));
            nikCallRecord.setNikId(nikInfo.getNikId());
            //已存在的数据，判断商户是否已请求过，已请求过不算量
            if(sNikCallRecordService.hasRequestNik(merchantId,nik)){
                nikCallRecord.setCallStatus(2);
            }else {
                nikCallRecord.setCallStatus(0);
            }
            sNikCallRecordService.update(nikCallRecord);
        }

        return result;
    }



    public  NIKCheckVo checkTestData(){
        SNikInfo nikInfo = new SNikInfo();
        nikInfo.setName("Ke******Dewi");
        nikInfo.setNik("1671045802880013");
        nikInfo.setBod("1988-02-18");
        nikInfo.setAddress("Jl.**********bar daun");
        return  NIKCheckVo.createVo(nikInfo);
    }


}
