package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SRiskSource;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SRiskSourceMapper extends Mapper<SRiskSource> {

    public List<Map<String,Object>> getDrop(Map<String, Object> param);

}