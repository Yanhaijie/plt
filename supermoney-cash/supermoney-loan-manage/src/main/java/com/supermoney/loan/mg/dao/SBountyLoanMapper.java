package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SBountyLoan;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBountyLoanMapper extends Mapper<SBountyLoan> {
    public List<SBountyLoan> selectList(Map<String,Object> map);
}