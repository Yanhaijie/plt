package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SAtCreditInformation;
import com.supermoney.loan.mg.entity.SAtIdentity;
import com.supermoney.loan.mg.entity.vo.SAtCreditInformationVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SAtCreditInformationMapper extends Mapper<SAtCreditInformation> {

    public List<SAtCreditInformationVo> selectList(Map<String,Object> map);

    public List<SAtCreditInformation> selectUnCheckCreditList();
}