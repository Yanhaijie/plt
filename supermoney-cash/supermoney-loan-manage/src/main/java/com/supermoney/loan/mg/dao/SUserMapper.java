package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.entity.vo.DistributionVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserMapper extends Mapper<SUser> {

    public List<SUser> selectList(Map<String,Object> map);

    public void updateCreditInfo(Map<String, Object> param);


    public List<Map<String,Object>> reportNomalCount(Map<String, Object> param);

    public List<Map<String,Object>> reportOrderCount(Map<String, Object> param);

    public List<Map<String,Object>> reportOrderStatus(Map<String, Object> param);

    List<DistributionVo> getDistributions(Map<String, Object> param);

    Integer getDistributionCount(Map<String, Object> param);
}