package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SBlackList;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBlackListMapper extends Mapper<SBlackList> {

    public void saveOrUpdateBlackList(List<SBlackList> list);
    public List<SBlackList> selectList(Map<String,Object> param);
}