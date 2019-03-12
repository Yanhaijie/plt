package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SActivityIssueRecord;
import com.supermoney.loan.api.utils.Mapper;

import java.util.Map;

public interface SActivityIssueRecordMapper extends Mapper<SActivityIssueRecord> {

    public Long getTodayIssueRecord(Map<String, Object> param);
}