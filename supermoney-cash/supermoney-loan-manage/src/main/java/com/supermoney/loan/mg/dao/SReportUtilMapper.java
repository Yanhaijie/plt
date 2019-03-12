package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SReportUtil;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SReportUtilMapper extends Mapper<SReportUtil> {

    public List<Map<String,Object>> executeSelect(Map<String,Object> map);

}