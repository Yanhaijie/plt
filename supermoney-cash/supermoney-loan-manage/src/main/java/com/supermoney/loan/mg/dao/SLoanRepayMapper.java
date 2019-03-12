package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SLoanGrant;
import com.supermoney.loan.mg.entity.SLoanRepay;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SLoanRepayMapper extends Mapper<SLoanRepay> {
    /**
     *
     * @param map
     * @return
     */
    public List<SLoanRepay> selectList(Map<String,Object> map);

    /**
     * 用户还款记录
     * @param map
     * @return
     */
    public List<Map<String,Object>> getRecordList(Map<String,Object> map);
}