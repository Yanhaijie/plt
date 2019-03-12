package com.supermoney.open.platform.services;

import com.alibaba.fastjson.JSON;
import com.supermoney.open.platform.entity.SBaiduCompare;
import com.supermoney.open.platform.entity.SBaiduLiving;
import com.supermoney.open.platform.entity.SCallRecord;
import com.supermoney.open.platform.entity.SInterface;
import com.supermoney.open.platform.entity.vo.respond.LivingRespondVo;
import com.supermoney.open.platform.service.*;
import com.supermoney.open.platform.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaiduAIService {

    //获取access_token url
    final static private String BAIDU_TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token";
    //活体认证url
    final static private String BAIDU_LIVING_URL = "https://aip.baidubce.com/rest/2.0/face/v3/faceverify";
    //人脸对比url
    final static private String BAIDU_MATCH_URL = "https://aip.baidubce.com/rest/2.0/face/v3/match";

    @Value("${baidu.ai.apiKey}")
    private String apiKey;
    @Value("${baidu.ai.secretKey}")
    private String secretKey;

    @Resource
    private SInterfaceService sInterfaceService;
    @Resource
    private SCallRecordService sCallRecordService;
    @Resource
    private SBaiduLivingService sBaiduLivingService;
    @Resource
    private SBaiduCompareService sBaiduCompareService;
    @Resource
    private FileServer fileServer;

    /**
     * 活体验证
     * @param imgBase
     * @return
     * @throws IOException
     */
    @Transactional
    public Result checkLivePeople(String imgBase, String merchantId) throws IOException {
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

        //校验活体
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes1 = decoder.decodeBuffer(URLDecoder.decode(imgBase,"utf-8").split(",")[1]);
        result = BaiduAIUtils.checkLivePeopleByByte(BAIDU_LIVING_URL, bytes1,getAccessToken());

        //查询接口
        SInterface sInterface = sInterfaceService.getInterface(4);

        //添加调用记录
        SCallRecord callRecord = new SCallRecord();
        callRecord.setInterfaceId(sInterface.getId());
        callRecord.setMerchantId(merchantId);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("living_img", imgUrl);
        callRecord.setParamJson(JSON.toJSONString(paramMap));

        if (result.getCode() == ResultCode.SUCCESS.code) {
            //活体记录
            Map<String, Object> tempMap = (Map<String, Object>) result.getData();
            Map<String, Object> thresholdsMap = (Map<String, Object>) tempMap.get("thresholds");
            Map<String, Object> faceMap = ((List<Map<String, Object>>) tempMap.get("face_list")).get(0);
            Map<String, Object> angleMap = (Map<String, Object>) faceMap.get("angle");
            Map<String, Object> locationMap = (Map<String, Object>) faceMap.get("location");
            SBaiduLiving sBaiduLiving = new SBaiduLiving();
            sBaiduLiving.setSource(sInterface.getSource());
            sBaiduLiving.setFrr1e2(new BigDecimal(thresholdsMap.get("frr_1e-2").toString()));
            sBaiduLiving.setFrr1e3(new BigDecimal(thresholdsMap.get("frr_1e-3").toString()));
            sBaiduLiving.setFrr1e4(new BigDecimal(thresholdsMap.get("frr_1e-4").toString()));
            sBaiduLiving.setFaceLiveness(new BigDecimal(tempMap.get("face_liveness").toString()));
            sBaiduLiving.setFaceRoll(angleMap.get("roll").toString());
            sBaiduLiving.setFacePitch(angleMap.get("pitch").toString());
            sBaiduLiving.setFaceYaw(angleMap.get("yaw").toString());
            sBaiduLiving.setFaceToken(faceMap.get("face_token").toString());
            sBaiduLiving.setFaceTop(locationMap.get("top").toString());
            sBaiduLiving.setFaceLeft(locationMap.get("left").toString());
            sBaiduLiving.setFaceWidth(locationMap.get("width").toString());
            sBaiduLiving.setFaceHeight(locationMap.get("height").toString());
            sBaiduLiving.setRotation(locationMap.get("rotation").toString());
            sBaiduLiving.setFaceProbability(faceMap.get("face_probability").toString());
            sBaiduLivingService.save(sBaiduLiving);

            callRecord.setRequestStatus(0);
            callRecord.setResultId(sBaiduLiving.getId());

            result.setData(new LivingRespondVo((Map<String, Object>) result.getData()));
        }
        else {
            callRecord.setRequestStatus(1);
        }

        callRecord.setRespondJson(JSON.toJSONString(result));
        sCallRecordService.save(callRecord);

        return result;
    }


    /**
     * 人脸对比
     * @param face1
     * @param face2
     * @return
     * @throws IOException
     */
    @Transactional
    public Result baiduCompare(String face1, String face2, String merchantId) throws IOException {
        //参数校验
        if (StringUtils.isBlank(face1) || StringUtils.isBlank(face2)){
            return ResultGenerator.genResult(ResultCode.FILE_ERROR_EMPTY.code, ResultCode.FILE_ERROR_EMPTY.msg);
        }

        //保存图片
        Result result = fileServer.imgUpdate(face1);
        if (result.getCode() !=  ResultCode.SUCCESS.code){
            return ResultGenerator.genResult(ResultCode.FILE_ERROR_UPLOAD.code, ResultCode.FILE_ERROR_UPLOAD.msg);
        }
        String faceUrl1 = ((Map<String,String>)result.getData()).get("fileName");

        result = fileServer.imgUpdate(face2);
        if (result.getCode() !=  ResultCode.SUCCESS.code){
            return ResultGenerator.genResult(ResultCode.FILE_ERROR_UPLOAD.code, ResultCode.FILE_ERROR_UPLOAD.msg);
        }
        String faceUrl12 = ((Map<String,String>)result.getData()).get("fileName");

        //人脸对比
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes1 = decoder.decodeBuffer(URLDecoder.decode(face1,"utf-8").split(",")[1]);
        byte[] bytes2 = decoder.decodeBuffer(URLDecoder.decode(face2,"utf-8").split(",")[1]);
        result = BaiduAIUtils.baiduCompareByByte(BAIDU_MATCH_URL,bytes1 ,bytes2 ,getAccessToken());

        //查询接口
        SInterface sInterface = sInterfaceService.getInterface(3);

        //添加调用记录
        SCallRecord callRecord = new SCallRecord();
        callRecord.setInterfaceId(sInterface.getId());
        callRecord.setMerchantId(merchantId);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("face1_img", faceUrl1);
        paramMap.put("face2_img", faceUrl12);
        callRecord.setParamJson(JSON.toJSONString(paramMap));

        if (result.getCode() == ResultCode.SUCCESS.code) {
            //对比记录
            Map<String, Object> tempMap = (Map<String, Object>) result.getData();
            SBaiduCompare sBaiduCompare = new SBaiduCompare();
            sBaiduCompare.setSource(sInterface.getSource());
            sBaiduCompare.setCompareScore(tempMap.get("score").toString());
            sBaiduCompare.setFaceToken1(tempMap.get("faceToken1").toString());
            sBaiduCompare.setFaceToken2(tempMap.get("faceToken2").toString());
            sBaiduCompareService.save(sBaiduCompare);

            callRecord.setRequestStatus(0);
            callRecord.setResultId(sBaiduCompare.getId());
        }
        else {
            callRecord.setRequestStatus(1);
        }

        callRecord.setRespondJson(JSON.toJSONString(result));
        sCallRecordService.save(callRecord);

        return result;
    }

    private String getAccessToken(){
        return BaiduAIUtils.getAccessToken(BAIDU_TOKEN_URL, apiKey, secretKey);
    }










































}
