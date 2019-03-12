package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SVoucherUserRecord;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SVoucherUserRecordMapper extends Mapper<SVoucherUserRecord> {
    public List<SVoucherUserRecord> selectList(Map<String,Object> map);
    public Map<String,Object> executeSql(Map<String,Object> param);
}