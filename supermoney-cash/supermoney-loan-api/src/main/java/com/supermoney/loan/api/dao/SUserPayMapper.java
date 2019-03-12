package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SUserPay;
import com.supermoney.loan.api.utils.Mapper;

import java.math.BigDecimal;
import java.util.List;

public interface SUserPayMapper extends Mapper<SUserPay> {
    public BigDecimal getPaySum(SUserPay sUserPay);
    public List<SUserPay> getList(SUserPay sUserPay);
}