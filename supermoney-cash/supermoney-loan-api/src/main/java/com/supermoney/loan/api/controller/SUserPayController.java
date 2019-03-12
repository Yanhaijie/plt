package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.entity.SUserPay;
import com.supermoney.loan.api.service.SExchangeRateService;
import com.supermoney.loan.api.service.SUserPayService;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import com.supermoney.loan.api.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
* Created by wenyuhao on 2018/04/25.
*/
@RestController
@RequestMapping("/s/user/pay")
@Api(value = "/s/user/pay",description = "充值接口")
public class SUserPayController {
    @Resource
    private SUserPayService sUserPayService;
    @Resource
    private SExchangeRateService sExchangeRateService;


    @PostMapping("/getAwardForNewUser")
    @ApiOperation("新用户领取奖励")
    public Result getAwardForNewUser(String appSecret) {
        SUserPay sUserPay  =new SUserPay();
        Integer userId = UserUtils.getCurrentUserId();
        sUserPay.setUserId(userId);
        return sUserPayService.getAwardForNewUser(sUserPay);
    }

    @PostMapping("/getSumAward")
    @ApiOperation("获取总金额")
    public Result getSumAward(String appSecret,Integer payStatus,Integer getStatus) {
        SUserPay sUserPay  =new SUserPay();
        sUserPay.setUserId(UserUtils.getCurrentUserId());
        sUserPay.setPayStatus(payStatus);
        sUserPay.setGetStatus(getStatus);
        BigDecimal sum = sUserPayService.getPaySum(sUserPay);
        return ResultGenerator.genSuccessResult( sExchangeRateService.usdToByCountry(Constants.Country.INDONESIA_CR,sum,false));
    }


}
