package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SMessageUser;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SMessageUserMapper extends Mapper<SMessageUser> {

    public List<SMessageUser> selectList(Map<String,Object> map);

    public int getCount(SMessageUser sMessageUser);



}