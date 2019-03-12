package com.supermoney.open.platform.dao;

import com.supermoney.open.platform.entity.SCallRecord;
import com.supermoney.open.platform.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SCallRecordMapper extends Mapper<SCallRecord> {

    public List<Map<String, Object>> selectCallRecordByParam(Map<String, Object> param);
}