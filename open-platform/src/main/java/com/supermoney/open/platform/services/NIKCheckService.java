package com.supermoney.open.platform.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.open.platform.entity.SBaiduCompare;
import com.supermoney.open.platform.entity.SCallRecord;
import com.supermoney.open.platform.entity.SInterface;
import com.supermoney.open.platform.entity.SNikInfo;
import com.supermoney.open.platform.entity.vo.respond.NikInfoRespondVo;
import com.supermoney.open.platform.service.SCallRecordService;
import com.supermoney.open.platform.service.SInterfaceService;
import com.supermoney.open.platform.service.SNikInfoService;
import com.supermoney.open.platform.utils.NIKCheckSUtils;
import com.supermoney.open.platform.utils.Result;
import com.supermoney.open.platform.utils.ResultCode;
import com.supermoney.open.platform.utils.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class NIKCheckService {

    @Resource
    private SInterfaceService sInterfaceService;
    @Resource
    private SCallRecordService sCallRecordService;
    @Resource
    private SNikInfoService sNikInfoService;

    /**
     * 查询身份信息
     *{
     *  "nik": "3672080209920003",
     *  "name": "Ra*******tiandi",
     *  "address": "Link*************ng brangbang",
     *  "bod": "1992-09-02"
     *}
     *
     * @param nik
     * @return
     */
    public Result checkNIK(String nik , String merchantId){

        Result result;

        if (StringUtils.isBlank(nik)){
            return ResultGenerator.genResult(ResultCode.PARAM_EMPTY_NIK.code, ResultCode.PARAM_EMPTY_NIK.msg);
        }

        //查询接口
        SInterface sInterface = sInterfaceService.getInterface(5);

        //添加调用记录
        SCallRecord callRecord = new SCallRecord();
        callRecord.setInterfaceId(sInterface.getId());
        callRecord.setMerchantId(merchantId);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("nik", nik);
        callRecord.setParamJson(JSON.toJSONString(paramMap));

        //本地查询
        SNikInfo nikInfo = sNikInfoService.selectNikInfoByNik(nik);
        if (nikInfo != null){
            return ResultGenerator.genSuccessResult(new NikInfoRespondVo(nikInfo));
        }
        //三方查询
        else {
            result = NIKCheckSUtils.checkNIK(nik);

            //添加个人信息记录
            if (result.getCode() == ResultCode.SUCCESS.code) {

                Map<String, Object> tempMap = (Map<String, Object>) result.getData();
                SNikInfo sNikInfo = new SNikInfo();
                sNikInfo.setName(tempMap.get("name") == null ? null : tempMap.get("name").toString());
                sNikInfo.setNik(nik);
                sNikInfo.setBod(tempMap.get("bod") == null ? null : tempMap.get("bod").toString());
                sNikInfo.setAddress(tempMap.get("address") == null ? null : tempMap.get("address").toString());
                sNikInfo.setCreatedAt(tempMap.get("created_at") == null ? null : tempMap.get("created_at").toString());
                sNikInfo.setUpdatedAt(tempMap.get("updated_at") == null ? null : tempMap.get("updated_at").toString());
                sNikInfoService.save(sNikInfo);

                result.setData(new NikInfoRespondVo(sNikInfo));

                callRecord.setRequestStatus(0);
                callRecord.setResultId(sNikInfo.getId());
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
