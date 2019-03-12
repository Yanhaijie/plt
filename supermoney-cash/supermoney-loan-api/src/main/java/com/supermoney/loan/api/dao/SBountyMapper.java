package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBountyMapper extends Mapper<SBounty> {

    public List<SBounty> selectList(Map<String,Object> map);
}