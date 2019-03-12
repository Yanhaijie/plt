package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SBountyLoan;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBountyLoanMapper extends Mapper<SBountyLoan> {
    public List<SBountyLoan> selectList(Map<String,Object> map);
}