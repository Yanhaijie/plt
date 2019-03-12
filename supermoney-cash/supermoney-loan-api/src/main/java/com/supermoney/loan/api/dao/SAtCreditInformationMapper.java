package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SAtCreditInformation;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;

public interface SAtCreditInformationMapper extends Mapper<SAtCreditInformation> {
    public List<SAtCreditInformation> selectUnCheckCreditList();
}