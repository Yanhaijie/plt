package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SBounty;
import com.supermoney.loan.mg.entity.vo.SBountyRecordVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBountyMapper extends Mapper<SBounty> {
    public List<SBounty> selectList(Map<String,Object> map);
}