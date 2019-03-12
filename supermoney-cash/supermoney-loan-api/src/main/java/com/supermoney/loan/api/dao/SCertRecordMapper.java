package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SCertRecord;
import com.supermoney.loan.api.utils.Mapper;

import java.util.Map;

public interface SCertRecordMapper extends Mapper<SCertRecord> {

    public Map<String, Long> selectStatusByUserId(Map<String, Object> param);

    public Map<String, Integer> selectAllStatusByUserId(int userId);
}