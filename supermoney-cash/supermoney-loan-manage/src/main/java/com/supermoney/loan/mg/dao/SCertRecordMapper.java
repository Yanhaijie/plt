package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SCertRecord;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.Map;

public interface SCertRecordMapper extends Mapper<SCertRecord> {

    public Map<String, Long> selectStatusByUserId(Map<String, Object> param);

    public Map<String, Integer> selectAllStatusByUserId(int userId);
}