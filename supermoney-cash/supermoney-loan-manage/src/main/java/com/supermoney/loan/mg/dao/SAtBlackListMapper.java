package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SAtBlackList;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SAtBlackListMapper extends Mapper<SAtBlackList> {

    public List<SAtBlackList> selectBlackListByParam(Map<String, Object> param);

    public List<SAtBlackList> selectHitBlackListByParam(Map<String, Object> param);
}