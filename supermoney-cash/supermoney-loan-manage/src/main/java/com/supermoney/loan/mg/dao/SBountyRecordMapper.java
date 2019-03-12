package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SBountyRecord;
import com.supermoney.loan.mg.entity.vo.SBountyRecordVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBountyRecordMapper extends Mapper<SBountyRecord> {

    public List<SBountyRecordVo> selectList(Map<String,Object> map);

    public Map<String,Object> executeSql(Map<String,Object> param);
}