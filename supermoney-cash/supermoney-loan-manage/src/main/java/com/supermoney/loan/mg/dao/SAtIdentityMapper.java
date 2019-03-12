package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SAtIdentity;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SAtIdentityMapper extends Mapper<SAtIdentity> {
    public List<SAtIdentity> selectList(Map<String,Object> map);

    public List<SAtIdentity> selectUncheckIdentity();

    public List<SAtIdentity> selectUncheck();

    public List<SAtIdentity> selectHaveOrderIdentity();


    public SAtIdentity selectUserIdentity(Map<String,Object> map);
}