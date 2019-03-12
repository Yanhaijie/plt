package com.supermoney.loan.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.entity.*;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LotteryBussServiceImpl implements LotteryBussService{
    private static final Logger logger = LoggerFactory.getLogger(LotteryBussServiceImpl.class);

    @Resource
    private SActivityLotteryRecordService sActivityLotteryRecordService;

    @Resource
    private SActivityWhiteListService sActivityWhiteListService;

    @Resource
    private SActivityGiftService sActivityGiftService;

    @Resource
    private UserAccountBussService userAccountBussService;

    @Resource
    private SUserService sUserService;

    @Resource
    private SActivityIssueRecordService sActivityIssueRecordService;

    @Resource
    private SBussLabelService bussLabelService;

    @Resource
    private SExchangeRateService sExchangeRateService;

    @Override
    @Transactional
    public Result drawLottery(Integer userId) {
        Map<String, Object> resultMap = new HashMap<>();

        //查看是否有抽奖次数
        SUser user = sUserService.findById(userId);
        if (user.getLotteryCount() == null || user.getLotteryCount() <= 0){
            Result result = ResultGenerator.genFailResult("Hari ini sudah tidak ada kesempatan mengundi");

            //判断是否金额足够
            SUserAccount userAccount = userAccountBussService.getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
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
            result.setData(resultMap);

            result.setCode(11);
            return result;
        }

        //校验是否超过10次
        Map<String, Object> param = new HashMap<>();
        param.put("activityName",Constants.Activity.LOTTERY_51);
        param.put("userId",userId);
        Long count = sActivityLotteryRecordService.getTodayLotteryRecord(param);

        Result tempResult = bussLabelService.getByNameNotSplit(Constants.Activity.LOTTERY_51);
        JSONObject jsonObject = JSON.parseObject(tempResult.getData().toString());
        int maxCount = Integer.parseInt(jsonObject.get("maxCount").toString());

        if (count >= maxCount){
            return ResultGenerator.genFailResult("Maksimal pengundian sebanyak " + maxCount + " x/ hari");
        }

        //查看是否触发白名单里面的次数
        Long lotteryCount = sActivityLotteryRecordService.selectLotteryRecordCountByUserId(param);
        SActivityWhiteList winningWhite = null;
        List<SActivityWhiteList> tempWhiteLists = sActivityWhiteListService.selectWhiteListByParam(param);
        for (SActivityWhiteList temp :tempWhiteLists) {
            if (temp.getLotteryCount() <= lotteryCount){
                winningWhite = temp;

            }
        }

        //构建奖池 触发白名单用户则直接发奖
        SActivityGift winGift = null;

        SActivityGift noWinningGift = new SActivityGift();
        noWinningGift.setGiftName("未中奖");

        if (winningWhite != null){
            winGift = sActivityGiftService.findById(winningWhite.getGiftId());
        }
        else {
            List<SActivityGift> giftList = sActivityGiftService.selectCommonGiftByMap(param);

            BigDecimal noWinningBig = new BigDecimal(1);

            for (SActivityGift activityGift:giftList) {
                noWinningBig = noWinningBig.subtract(activityGift.getProbability()).setScale(8,BigDecimal.ROUND_HALF_UP);
            }

            if (noWinningBig.compareTo(new BigDecimal(0)) <= 0){
                logger.error("后台抽奖概率设置出错，中奖概率超过1");
            }
            else {
                //添加 未中奖 礼物
                noWinningGift.setProbability(noWinningBig);
                giftList.add(noWinningGift);

                //打乱礼物顺序
                if(giftList.size() > 1){
                    Collections.shuffle(giftList);
                }

                winGift = draw(giftList);
            }
        }

        if (winGift == null){
            winGift = noWinningGift;
        }

        //礼物数量减一
        if (!winGift.getGiftName().equals("未中奖")){
            if (winGift.getRemainCount() > 0){
                sActivityGiftService.subRemainCount(winGift.getId());

                SActivityGift temp = sActivityGiftService.findById(winGift.getId());
                if (temp.getRemainCount() < 0){
                    winGift.setRemainCount(winGift.getRemainCount() + 1);
                    sActivityGiftService.addRemainCount(winGift.getId());
                    winGift = noWinningGift;
                }
            }
            else {
                winGift = noWinningGift;
            }
        }

        //抽奖次数减一
        user.setLotteryCount(user.getLotteryCount() - 1);
        sUserService.update(user);

        //插入中奖记录
        SActivityLotteryRecord lotteryRecord = new SActivityLotteryRecord();
        lotteryRecord.setActivityName(Constants.Activity.LOTTERY_51);
        lotteryRecord.setUserId(userId);
        if (winGift.getGiftName().equals("未中奖")){
            lotteryRecord.setIsWinning((byte)0);

            resultMap.put("isWinning",0);
            resultMap.put("giftName",null);
            resultMap.put("giftCount",null);
            resultMap.put("giftId",null);
        }
        else {
            lotteryRecord.setGiftId(winGift.getId());
            lotteryRecord.setGiftCount(winGift.getGiftCount());
            lotteryRecord.setGiftName(winGift.getGiftName());
            lotteryRecord.setIsWinning((byte)1);

            resultMap.put("isWinning",1);
            resultMap.put("giftName",winGift.getGiftName());
            resultMap.put("giftCount",winGift.getGiftCount());
            resultMap.put("giftId",winGift.getId());
        }
        sActivityLotteryRecordService.save(lotteryRecord);

        //若命中白名单 ，修改白名单记录
        if (winningWhite != null && !winGift.getGiftName().equals("未中奖")){
            winningWhite.setIsSent((byte) 1);
            winningWhite.setLotteryRecordId(lotteryRecord.getId());
            sActivityWhiteListService.update(winningWhite);
        }

        //礼物是金币直接充值余额中
        if (!winGift.getGiftName().equals("未中奖") && winGift.getGiftType() == 0){
            BigDecimal giftCount = new BigDecimal(winGift.getGiftCount());
            BigDecimal usdCount = sExchangeRateService.toUsdByCountry(Constants.Country.INDONESIA_CR,giftCount);
            userAccountBussService.inMoenyAccount(Constants.BUSS_TYPE_LOTTERY,Constants.BUSS_TYPE_LOTTERY_WINNING_COUNT,userId,usdCount);
        }

        return ResultGenerator.genSuccessResult(resultMap);
    }

    public static void main(String[] args){

        SActivityGift gift1 = new SActivityGift();
        gift1.setProbability(new BigDecimal(0.1));
        gift1.setGiftName("金币10个");
        gift1.setGiftCount(0);

        SActivityGift gift2 = new SActivityGift();
        gift2.setProbability(new BigDecimal(0.05));
        gift2.setGiftName("话费");
        gift2.setGiftCount(0);

        SActivityGift gift3 = new SActivityGift();
        gift3.setProbability(new BigDecimal(0.0001));
        gift3.setGiftName("iphone");
        gift3.setGiftCount(0);

        SActivityGift noWinningGift = new SActivityGift();
        noWinningGift.setGiftName("未中奖");
        noWinningGift.setProbability(new BigDecimal(1).subtract(new BigDecimal(0.1)).subtract(new BigDecimal(0.05)).subtract(new BigDecimal(0.0001)));
        noWinningGift.setGiftCount(0);

        List<SActivityGift> list = new ArrayList<>();
        list.add(gift1);
        list.add(gift2);
        list.add(gift3);
        list.add(noWinningGift);

        for(int i=0;i<10;i++){
            SActivityGift tempgift = draw(list);
            tempgift.setGiftCount(tempgift.getGiftCount()+1);
        }

        list.forEach(temp-> logger.info(temp.getGiftName() + "  :  " + temp.getGiftCount()));
    }

    //抽奖
    public static SActivityGift draw(List<SActivityGift> giftList){
        if (giftList != null && giftList.size() > 0) {

            List<BigDecimal> sortRateList = new ArrayList<>();
            BigDecimal winningProbability = new BigDecimal(0);
            BigDecimal totalProbability = new BigDecimal(1);
            BigDecimal tempProbability = new BigDecimal(0);

            for (SActivityGift gift : giftList) {
                winningProbability = winningProbability.add(gift.getProbability()).setScale(8,BigDecimal.ROUND_HALF_UP);
                tempProbability = tempProbability.add(gift.getProbability()).setScale(8,BigDecimal.ROUND_HALF_UP);
                sortRateList.add(tempProbability.divide(totalProbability).setScale(8,BigDecimal.ROUND_HALF_UP));
            }

            BigDecimal random = new BigDecimal(Math.random()).setScale(8,BigDecimal.ROUND_HALF_UP);
            logger.info("" + random);
            int resultInt = 0;
            for (int i = 0; i < sortRateList.size(); i++){
                BigDecimal sortBig  = sortRateList.get(i);
                if (sortBig.compareTo(random) >= 0){
                    resultInt = i;
                    break;
                }
            }

            return giftList.get(resultInt);
        }

        return null;
    }

    @Override
    @Transactional
    public Result addLotteryCount(Integer userId,Integer issueType) {
        SUser user = sUserService.findById(userId);
        if (user.getLotteryCount() == null){
            user.setLotteryCount(0);
        }
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("activityName",Constants.Activity.LOTTERY_51);
            param.put("userId",userId);
            param.put("issueType",issueType);
            Long count = sActivityIssueRecordService.getTodayIssueRecord(param);

            if (count != null && count == 0){
                user.setLotteryCount(user.getLotteryCount() + 1);
                sUserService.update(user);

                SActivityIssueRecord issueRecord = new SActivityIssueRecord();
                issueRecord.setActivityName(Constants.Activity.LOTTERY_51);
                issueRecord.setUserId(userId);
                issueRecord.setIssueType((byte)issueType.intValue());
                sActivityIssueRecordService.save(issueRecord);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("");
        }

        return ResultGenerator.genSuccessResult();
    }


    @Override
    @Transactional
    public Result exchangeLotteryCount(Integer userId) {
        Result result = ResultGenerator.genSuccessResult();
        SUserAccount userAccount = userAccountBussService.getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        SUser user = sUserService.findById(userId);
        if (user.getLotteryCount() == null){
            user.setLotteryCount(0);
        }

        Result tempResult = bussLabelService.getByNameNotSplit(Constants.Activity.LOTTERY_51);
        JSONObject jsonObject = JSON.parseObject(tempResult.getData().toString());
        int tempMoney = Integer.parseInt(jsonObject.get("exchangeAmount").toString());
        BigDecimal bigDecimal = new BigDecimal(tempMoney);
        BigDecimal tempCount = sExchangeRateService.toUsdByCountry(Constants.Country.INDONESIA_CR,bigDecimal);

        if (userAccount.getAvailableAmount().compareTo(tempCount) >= 0){
            userAccountBussService.outMoenyAccount(Constants.BUSS_TYPE_LOTTERY,Constants.BUSS_TYPE_LOTTERY_EXCHANGE_COUNT,userId,tempCount);
            user.setLotteryCount(user.getLotteryCount() + 1);
            sUserService.update(user);

            SUserAccount trmpAccount = userAccountBussService.getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("lotteryCount",user.getLotteryCount());
            resultMap.put("balance",sExchangeRateService.usdToByCountry(Constants.Country.INDONESIA_CR,trmpAccount.getAvailableAmount(),false));
            result.setData(resultMap);

            SActivityIssueRecord issueRecord = new SActivityIssueRecord();
            issueRecord.setActivityName(Constants.Activity.LOTTERY_51);
            issueRecord.setUserId(userId);
            issueRecord.setIssueType((byte)2);
            sActivityIssueRecordService.save(issueRecord);
        }
        else {
            result = ResultGenerator.genFailResult("Saldo tidak mencukupi");
        }

        return result;
    }

    @Override
    public Boolean lotteryNotExpire() {
        Boolean isNotExpire = false;
        try {
            Result result = bussLabelService.getByNameNotSplit(Constants.Activity.LOTTERY_51);
            JSONObject jsonObject = JSON.parseObject(result.getData().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeoutDate = dateFormat.parse(jsonObject.get("lotteryTimeout").toString());
            Date nowDate = new Date();

            if (timeoutDate.getTime() > nowDate.getTime()){
                isNotExpire = true;
            }
        }
        catch (Exception w){
            w.printStackTrace();
        }

        return isNotExpire;
    }

    @Override
    public List<Map<String, Object>> giftList() {
        Map<String, Object> param = new HashMap<>();
        param.put("activityName",Constants.Activity.LOTTERY_51);
        return sActivityGiftService.selectGiftListByMap(param);
    }


}
