package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SMessageType;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SMessageTypeMapper extends Mapper<SMessageType> {

    public List<SMessageType> selectList(Map<String,Object> map);
}