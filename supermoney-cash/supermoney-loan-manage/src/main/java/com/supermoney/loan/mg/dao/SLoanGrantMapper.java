package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SLoanGrant;
import com.supermoney.loan.mg.entity.vo.SLoanOrderVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SLoanGrantMapper extends Mapper<SLoanGrant> {
    public List<SLoanGrant> selectList(Map<String,Object> map);
}