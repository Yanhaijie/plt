package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SBounty;
import com.supermoney.loan.mg.entity.SExchangeRate;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SExchangeRateMapper extends Mapper<SExchangeRate> {
    public List<SExchangeRate> selectList(Map<String,Object> map);
}