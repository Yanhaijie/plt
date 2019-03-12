package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SVoucherUserRecord;
import com.supermoney.loan.api.entity.vo.SVoucherVo;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SVoucherUserRecordMapper extends Mapper<SVoucherUserRecord> {
    public List<SVoucherVo> selectUserList(Map<String,Object> map);

    public Map<String,Object> executeSql(Map<String,Object> param);
}