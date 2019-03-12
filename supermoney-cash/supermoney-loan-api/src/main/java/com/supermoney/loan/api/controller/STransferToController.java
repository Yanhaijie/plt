package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.service.STransfertoTopupHisService;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import com.supermoney.loan.api.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.supermoney.loan.api.utils.NomalUntil.getPageMap;

/**
 * Created by xionghuifeng on 2018/01/04.
 */
@RestController
@RequestMapping("/s/transferto")
@Api(value = "/s/transferto", description = "充话费相关接口")
public class STransferToController {
    private static final Logger logger = LoggerFactory.getLogger(STransferToController.class);
    @Resource
    private STransfertoTopupHisService sTransfertoTopupHisService;

    @ApiOperation("充话费")
    @PostMapping("/topUp")
    public Result topUp(@ApiParam(name = "phoneNumber", value = "电话号码（不带+63前缀）", required = true) @RequestParam String phoneNumber, @ApiParam(name = "product", value = "充值金额", required = true) @RequestParam Integer product,String appSecret) {
        if(StringUtils.isEmpty(phoneNumber)){
            return ResultGenerator.genFailResult("phoneNumber is null");
        }
        if(product == null){
            return ResultGenerator.genFailResult("product is null");
        }
        //目前只允许印尼号码充值
        if(!phoneNumber.startsWith("+63")){
            if(phoneNumber.indexOf("0") == 0){
                phoneNumber = phoneNumber.substring(1,phoneNumber.length());
            }
            phoneNumber = "+63"+phoneNumber.trim();
        }
        return  sTransfertoTopupHisService.topup(UserUtils.getCurrentUserId(),1,phoneNumber,  product,  "+639668951527", "yes",  "", "yes", "");
    }
/*
    @ApiOperation("充话费服务")
    @PostMapping("/topUpService")
    public Result topUpService(String appSecret, @ApiParam(name = "phoneNumber", value = "电话号码（不带+62前缀）") @RequestParam String phoneNumber) {
        if(StringUtils.isEmpty(phoneNumber)){
            return ResultGenerator.genFailResult("phoneNumber is null");
        }
        //目前只允许印尼号码充值
        if(!phoneNumber.startsWith("+62")){
            if(phoneNumber.indexOf("0") == 0){
                phoneNumber = phoneNumber.substring(1,phoneNumber.length());
            }
            phoneNumber = "+62"+phoneNumber.trim();
        }
       // Map<Integer,BigDecimal> topUpFeeMap = sTransfertoTopupHisService.topUpService(UserUtils.getCurrentUserId(),phoneNumber);
        return ResultGenerator.genSuccessResult();
    }*/

    @ApiOperation("充话费服务")
    @PostMapping("/topUpServiceByType")
    public Result topUpServiceByType(String appSecret,@RequestParam(required = true) Integer phoneNumberType) {
        List<Map<String,Object>> list = sTransfertoTopupHisService.topUpService(UserUtils.getCurrentUserId(),phoneNumberType);
//        List<Map<String,Object>> list = sTransfertoTopupHisService.topUpService(114841,phoneNumberType);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("充话费账单")
    @PostMapping("/topUpCheck")
    public Result topUpCheck(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,String appSecret) {
        Map<String,Object> paramMap= getPageMap(page,size);
        paramMap.put("userId",UserUtils.getCurrentUserId());
//        paramMap.put("userId",114841);
        List<Map<String,Object>> resultList = sTransfertoTopupHisService.topUpCheck(paramMap);
        return ResultGenerator.genSuccessResult(resultList);
    }

    @ApiOperation("获取话费充值清单")
    @PostMapping("/msisdn_info")
    public Result msisdn_info(String appSecret){
        return sTransfertoTopupHisService.msisdn_info(UserUtils.getCurrentUserId());
    }
}
