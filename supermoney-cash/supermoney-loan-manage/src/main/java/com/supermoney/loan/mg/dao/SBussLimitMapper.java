package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SBussLimit;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBussLimitMapper extends Mapper<SBussLimit> {

    public List<Map<String,Object>> selectFeeLimit(Integer bountyId);
}