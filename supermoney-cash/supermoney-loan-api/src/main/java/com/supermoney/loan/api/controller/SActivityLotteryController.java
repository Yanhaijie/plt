package com.supermoney.loan.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.SUserAccount;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Donkey_MI_book on 2018/4/22.
 */
@RestController
@RequestMapping("/s/activity/lottery")
@Api(value = "/s/activity/lottery",description = "活动抽奖")
public class SActivityLotteryController {
    private static final Logger logger = LoggerFactory.getLogger(SActivityLotteryController.class);

    @Resource
    private SActivityLotteryRecordService sActivityLotteryRecordService;

    @Resource
    private UserAccountBussService userAccountBussService;

    @Resource
    private LotteryBussService lotteryBussService;

    @Resource
    private SUserService sUserService;

    @Resource
    private SExchangeRateService sExchangeRateService;

    @Resource
    private SBussLabelService bussLabelService;

    @ApiOperation("抽奖")
    @PostMapping("/drawLottery")
    public Result draw(String appSecret) {
        Integer userId = UserUtils.getCurrentUserId();
        Result result;
        Boolean isNotExpire = lotteryBussService.lotteryNotExpire();
        if (isNotExpire){
            result = lotteryBussService.drawLottery(userId);
        }
        else {
            result = ResultGenerator.genFailResult("Kegiatan promosi telah berakhir");
        }
        return result;
    }

    @ApiOperation("奖品记录")
    @PostMapping("/prizeRecord")
    public Result prizeRecord(String appSecret,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Integer userId = UserUtils.getCurrentUserId();

        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("activityName",Constants.Activity.LOTTERY_51);
        param.put("pageNum",(page - 1) * size);
        param.put("pageSize",size);
        List<Map<String, Object>> resultMap = sActivityLotteryRecordService.selectPrizeRecordByUserId(param);

        return ResultGenerator.genSuccessResult(resultMap);
    }

    @ApiOperation("抽奖记录")
    @PostMapping("/lotteryRecord")
    public Result lotteryRecord(String appSecret,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Integer userId = UserUtils.getCurrentUserId();
        Result result ;

        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("activityName",Constants.Activity.LOTTERY_51);
        param.put("pageNum",(page - 1) * size);
        param.put("pageSize",size);
        List<Map<String, Object>> resultMap = sActivityLotteryRecordService.selectLotteryRecordByUserId(param);
        result = ResultGenerator.genSuccessResult(resultMap);

        return result;
    }

    @ApiOperation("用户抽奖相关信息")
    @PostMapping("/userLotteryInfo")
    public Result lotteryCount(String appSecret) {

        Integer userId = UserUtils.getCurrentUserId();
        SUserAccount userAccount = userAccountBussService.getUserAccount(userId, Constants.ACCOUNT_TYPE_MONEY);
        Map<String ,Object> resultMap = new HashMap<>();


        Boolean isNotExpire = lotteryBussService.lotteryNotExpire();
        if (isNotExpire){
            //发放抽奖次数
            Result tempResult = lotteryBussService.addLotteryCount(userId,0);
            if (tempResult.getCode() != ResultCode.SUCCESS.code){
                logger.error("------领取抽奖次数失败------ ：" + userId);
                ResultGenerator.genFailResult("Jaringan internet tidak memadahi, Coba sekali lagi");
            }
        }

        SUser user  = sUserService.findById(userId);
        if (user.getLotteryCount() == null){
            user.setLotteryCount(0);
        }
        resultMap.put("lotteryCount",user.getLotteryCount());
        resultMap.put("balance",sExchangeRateService.usdToByCountry(Constants.Country.INDONESIA_CR,userAccount.getAvailableAmount(),false));
        resultMap.put("userName",user.getUserName());
        resultMap.put("isNotExpire",isNotExpire);

        //判断是否金额足够
        Result bussLabelResult = bussLabelService.getByNameNotSplit(Constants.Activity.LOTTERY_51);
        JSONObject temJsonObject = JSON.parseObject(bussLabelResult.getData().toString());
        int tempMoney = Integer.parseInt(temJsonObject.get("exchangeAmount").toString());
        BigDecimal bigDecimal = new BigDecimal(tempMoney);
        BigDecimal tempCount = sExchangeRateService.toUsdByCountry(Constants.Country.INDONESIA_CR,bigDecimal);

        if (userAccount.getAvailableAmount().compareTo(tempCount) >= 0){
            resultMap.put("canExchange",1);
        }
        else {
            resultMap.put("canExchange",0);
        }

        return ResultGenerator.genSuccessResult(resultMap);
    }

    @ApiOperation("分享增加抽奖次数")
    @PostMapping("/addShareLotteryCount")
    public Result addShareLotteryCount(String appSecret) {
        Integer userId = UserUtils.getCurrentUserId();

        if (userId == null){
            return ResultGenerator.genSuccessResult("fail");
        }
        Boolean isNotExpire = lotteryBussService.lotteryNotExpire();
        if(isNotExpire == false){
            return ResultGenerator.genSuccessResult();
        }

        //发放抽奖次数
        Result tempResult = lotteryBussService.addLotteryCount(userId,1);
        if (tempResult.getCode() != ResultCode.SUCCESS.code){
            logger.error("------领取抽奖次数失败------ ：" + userId);
        }

        SUser user  = sUserService.findById(userId);
        if (user.getLotteryCount() == null){
            user.setLotteryCount(0);
        }
        return ResultGenerator.genSuccessResult(user.getLotteryCount());
    }

    @ApiOperation("兑换抽检次数")
    @PostMapping("/exchangeLotteryCount")
    public Result exchangeLotteryCount(String appSecret) {
        Integer userId = UserUtils.getCurrentUserId();

        Result result;
        Boolean isNotExpire = lotteryBussService.lotteryNotExpire();
        if(isNotExpire){
            result = lotteryBussService.exchangeLotteryCount(userId);
        }
        else {
            result =  ResultGenerator.genFailResult("Kegiatan promosi telah berakhir");
        }

        return result;
    }

//    @ApiOperation("礼物列表")
    @PostMapping("/giftList")
    public Result giftList(String appSecret) {
        return ResultGenerator.genSuccessResult(lotteryBussService.giftList());
    }

    @ApiOperation("滚动中奖列表")
    @PostMapping("/scrollLotteryList")
    public Result giftScroollLotteryRecordList(String appSecret) {
        Map<String, Object> param = new HashMap<>();
        param.put("activityName",Constants.Activity.LOTTERY_51);
        List<Map<String, Object>> resultList = sActivityLotteryRecordService.selectScroolLotteryRecord(param);

        String[] centerChar = new String[]{"*","**","***","****","*****","******","*******","********","*********"};

        for (Map<String, Object> tempMap:resultList) {
            String mobile = (String) tempMap.get("mobile");

            int centerLength = mobile.length() - 2 - 3;
            String centerString = centerChar[centerLength - 1];
            mobile = mobile.substring(0,2) + centerString + mobile.substring(mobile.length() - 3 ,mobile.length());

            tempMap.put("mobile",mobile);
        }
        return ResultGenerator.genSuccessResult(resultList);
    }
}
