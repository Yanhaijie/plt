package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SUserCash;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserCashMapper extends Mapper<SUserCash> {

    public List<Map<String,Object>> getCashCheckListByStatus(Map<String, Object> param);

    public void doCashCheck(Map<String, Object> param);



}