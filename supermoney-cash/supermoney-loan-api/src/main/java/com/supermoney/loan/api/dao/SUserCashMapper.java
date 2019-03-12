package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SUserCash;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserCashMapper extends Mapper<SUserCash> {
    /**
     * 提现统计
     * @param map
     * @return
     */
    public  Integer statusCashTotal(Map<String,Object> map);

    /**
     * 最后一条提现
     * @param userId
     * @return
     */
    public SUserCash lastCash (Integer userId);
}