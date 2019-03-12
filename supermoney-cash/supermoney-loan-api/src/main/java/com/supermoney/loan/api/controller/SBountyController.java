package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import com.supermoney.loan.api.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xionghuifeng on 2018/01/04.
 */
@RestController
@RequestMapping("/s/bounty")
@Api(value = "/s/bounty", description = "赏金相关接口")
public class SBountyController  {
    private static final Logger logger = LoggerFactory.getLogger(SBountyController.class);

    @Resource
    private SBountyService sBountyService;

    @Resource
    private SBountyRecordService sBountyRecordService;

    @Resource
    private SUserAccountService accountService;

    @Resource
    private SAccountBalanceService balanceService;

    @Resource
    private SXenditPayService xenditPayService;

    @Resource
    private SUserBindService userCardService;

    @Resource
    private  SUserCashService sUserCashService;

    @ApiOperation("赏金任务详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBounty sBounty = sBountyService.findById(id);
        return ResultGenerator.genSuccessResult(sBounty);
    }

    @ApiOperation("赏金任务列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer size,String appSecret) {
        return sBountyService.getBountyList(page, size, UserUtils.getCurrentUserId());
    }
    @ApiOperation("赏金任务用户完成记录")
    @PostMapping("/record-list")
    public Result recordList( @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,String appSecret) {
        // (defaultValue = "1") (defaultValue = "15")
        logger.info("size:{},page:{}",page,size);
//        if(page==null || page.equals(0))page=1;
//        if(size==null || size.equals(0))size=15;

        Map<String, Object> param = new HashMap<>();
        param.put("userId",UserUtils.getCurrentUserId());
        return sBountyRecordService.getUserRecordByPage(page, size,param);
    }

    @ApiOperation("用户赏金任务统计信息")
    @PostMapping("/total")
    public Result bountyTotal() {
        return sBountyRecordService.userBountyTotal(UserUtils.getCurrentUserId());
    }


//    @ApiOperation("赏金提现")
//    @PostMapping("/cash")
//    public Result cash(String appSecret,
//                       @ApiParam(name = "cardNumber", value = "需要提现到的银行卡号", required = true)  @RequestParam String cardNumber,
//                       @ApiParam(name = "amount", value = "提现金额", required = true) @RequestParam(defaultValue = "0") BigDecimal amount) {
//        return  accountService.moneyAccountCash(UserUtils.getCurrentUserId(),cardNumber,amount, Constants.Country.INDONESIA_CODE);
//    }

    @ApiOperation("赏金全部提现")
    @PostMapping("/cash-all")
    public Result cashAll(String appSecret) {
        return  accountService.moneyAllCash(UserUtils.getCurrentUserId());
    }

    @ApiOperation("用户最近一条审核中提现")
    @PostMapping("/cash-last")
    public  Result lastCash(String appSecret){
      return  sUserCashService.lastCash(UserUtils.getCurrentUserId());
    }


}
