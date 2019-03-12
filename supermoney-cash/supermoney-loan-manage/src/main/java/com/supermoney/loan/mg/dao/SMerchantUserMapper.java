package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SMerchantUser;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SMerchantUserMapper extends Mapper<SMerchantUser> {

    public List<SMerchantUser> selectByParam(Map<String, Object> param);

    public List<Map<String, Object>> selectDrop();

}