package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SVoucher;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SVoucherMapper extends Mapper<SVoucher> {

    List<SVoucher> selectList(Map<String, Object> map);
}