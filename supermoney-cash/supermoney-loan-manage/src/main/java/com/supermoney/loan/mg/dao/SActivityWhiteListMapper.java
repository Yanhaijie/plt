package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SActivityWhiteList;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SActivityWhiteListMapper extends Mapper<SActivityWhiteList> {
    public List<SActivityWhiteList> selectWhiteListByMap(Map<String, Object> param);
}