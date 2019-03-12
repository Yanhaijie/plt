package com.supermoney.loan.api.service;

import com.supermoney.loan.api.entity.SActivityGift;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.utils.Result;

import java.util.List;
import java.util.Map;

public interface LotteryBussService {

    public Result drawLottery(Integer userId);

    public Result addLotteryCount(Integer userId,Integer issueType);

    public Result exchangeLotteryCount(Integer userId);

    public Boolean lotteryNotExpire();

    public List<Map<String, Object>> giftList();
}
