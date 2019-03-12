package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SActivityLotteryRecord;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SActivityLotteryRecordMapper extends Mapper<SActivityLotteryRecord> {

    List<Map<String, Object>> selectLotteryRecordByUserId(Map<String ,Object> param);

    public List<Map<String, Object>> selectPrizeRecordByUserId(Map<String, Object> param);

    public Long getTodayLotteryRecord(Map<String, Object> param);

    public Long selectLotteryRecordCountByUserId(Map<String, Object> param);

    public List<Map<String, Object>> selectScroolLotteryRecord(Map<String, Object> param);
}