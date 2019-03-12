package com.supermoney.open.platform.dao;

import com.supermoney.open.platform.entity.SBlackList;
import com.supermoney.open.platform.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBlackListMapper extends Mapper<SBlackList> {
    public List<SBlackList> selectBlackListByParam(Map<String, Object> param);
}