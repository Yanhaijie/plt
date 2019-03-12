package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SAtIdentity;
import com.supermoney.loan.api.utils.Mapper;

import java.util.Map;

public interface SAtIdentityMapper extends Mapper<SAtIdentity> {

    public  Integer hasIdentityTotal(Map<String,Object> map);

    public  Integer statusIdentityTotal(Map<String,Object> map);

    public SAtIdentity selectAtIdentityByUserId(Map<String, Object> param);

}