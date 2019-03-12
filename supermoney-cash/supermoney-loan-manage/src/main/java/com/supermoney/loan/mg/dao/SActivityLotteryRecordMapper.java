package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SActivityLotteryRecord;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SActivityLotteryRecordMapper extends Mapper<SActivityLotteryRecord> {
    public List<SActivityLotteryRecord> selectLotteryRecordListByMap(Map<String, Object> param);

    public List<Map<String, Object>> selectUserLotteryCount(Map<String, Object> param);
}