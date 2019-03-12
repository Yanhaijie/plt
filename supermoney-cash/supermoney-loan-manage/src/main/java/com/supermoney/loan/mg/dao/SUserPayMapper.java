package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SUserPay;
import com.supermoney.loan.mg.entity.vo.SUserPayVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserPayMapper extends Mapper<SUserPay> {

    public List<SUserPayVo> selectList(Map<String,Object> map);

    public void doPayCheck(Map<String, Object> param);
}