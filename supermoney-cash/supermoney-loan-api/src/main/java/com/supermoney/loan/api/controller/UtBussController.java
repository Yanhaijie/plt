package com.supermoney.loan.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.RequestUntil;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by bear on 2018/1/27.
 */
@RestController
@RequestMapping("/ut")
@Api(value = "/ut", description = "后端业务对外开放")
public class UtBussController {

    private static final Logger logger = LoggerFactory.getLogger(UtBussController.class);

    @Resource
    private SBountyRecordService sBountyRecordService;

    @Resource
    private  SUserAccountService sUserAccountService;

    @Resource
    private SUserBindService sUserBindService;

    @Resource
    private SUserService sUserService;

    @Resource
    private SXenditVirtualAccountService sXenditVirtualAccountService;





    @ApiOperation("赏金任务回调")
    @RequestMapping(value = "/bounty-callback",method = {RequestMethod.GET,RequestMethod.POST})
    public Result bountyCallBack(HttpServletRequest request, HttpServletResponse respone) {
        logger.info( "666callback-query;"+ request.getQueryString());
        Map<String, Object> param = RequestUntil.getParams();
        //logger.info("666callback-param;"+param.toString());
        return  sBountyRecordService.taskCallBack(param);
    }
    @ApiOperation("测试打款回调")
    @RequestMapping(value = "/test-disbursement-callback",method = {RequestMethod.GET,RequestMethod.POST})
    public Result xenditAcceptTestCallBack(@RequestBody JSONObject obj,HttpServletRequest request, HttpServletResponse respone) {
        logger.info("TEST-BACK-JSON：" + obj.toJSONString());
        String callBackToken=request.getHeader("X-CALLBACK-TOKEN");
        logger.info("TEST-BACK-TOKEN：" + callBackToken);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("打款接口")
    @RequestMapping(value = "/disbursement-callback",method = {RequestMethod.GET,RequestMethod.POST})
    public Result xenditAcceptCallBack(@RequestBody JSONObject obj,HttpServletRequest request, HttpServletResponse respone) {
        logger.info("支付接口回调" + obj.toString());
        String callBackToken=request.getHeader("X-CALLBACK-TOKEN");
        Result backResul=sUserAccountService.moneyAccountCashCallBack(obj,callBackToken);
        logger.info("CALLBACK-result:"+backResul.getMessage());
        return backResul;
    }

    @ApiOperation("银行卡绑定验证回调")
    @PostMapping("/bank-account-callback")
    public Result xenditBankAccountCallBack(@RequestBody JSONObject obj, HttpServletRequest request,HttpServletResponse respone) {
        logger.info("银行卡绑定验证回调" + obj.toString());
        String callBackToken=request.getHeader("X-CALLBACK-TOKEN");
        return  sUserBindService.bindAccountCallBack(obj,callBackToken);
    }

    @ApiOperation("收款回调")
    @RequestMapping(value = "/virtual-account-payment-callback",method = {RequestMethod.GET,RequestMethod.POST})
    public Result xenditVirtualAccountCallBack(@RequestBody JSONObject obj,HttpServletRequest request, HttpServletResponse respone) {
        logger.info("收款接口回调" + obj.toString());
        String callBackToken=request.getHeader("X-CALLBACK-TOKEN");
        Result backResul = null;
        try {
             backResul=sXenditVirtualAccountService.virtualAccountPaymentCallBack(obj,callBackToken);
            logger.info("CALLBACK-result:"+backResul.getMessage());
        }catch (Exception e){
            respone.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return backResul;
    }

    @ApiOperation("测试收款回调")
    @RequestMapping(value = "/test-virtual-account-payment-callback",method = {RequestMethod.GET,RequestMethod.POST})
    public Result testXenditVirtualAccountCallBack(@RequestBody JSONObject obj,HttpServletRequest request, HttpServletResponse respone) {
        logger.info("支付接口回调" + obj.toString());
        logger.info("支付接口回调X-CALLBACK-TOKEN：" + request.getHeader("X-CALLBACK-TOKEN"));
        return null;
    }




}
