package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SBountyRecord;
import com.supermoney.loan.api.entity.vo.SBountyUserRecordVo;
import com.supermoney.loan.api.utils.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SBountyRecordMapper extends Mapper<SBountyRecord> {

    public List<SBountyRecord> selectList(Map<String,Object> map);

    public List<SBountyUserRecordVo> selectUserList(Map<String,Object> map);

    /**
     * 统计用户已结算的记录
     * @param map
     * @return
     */
    public BigDecimal userRecordTotal(Map<String,Object> map);
}