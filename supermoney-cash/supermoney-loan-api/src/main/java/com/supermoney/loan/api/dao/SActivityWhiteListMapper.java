package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SActivityWhiteList;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SActivityWhiteListMapper extends Mapper<SActivityWhiteList> {

    public List<SActivityWhiteList> selectWhiteListByParam(Map<String, Object> param);
}