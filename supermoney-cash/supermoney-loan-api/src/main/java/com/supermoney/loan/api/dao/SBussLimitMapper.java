package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SBussLimit;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBussLimitMapper extends Mapper<SBussLimit> {

    public List<Map<String,Object>> selectFeeLimit(Integer bountyId);
}