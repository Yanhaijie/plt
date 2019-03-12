package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SActivityGift;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SActivityGiftMapper extends Mapper<SActivityGift> {
    public List<SActivityGift> selectCommonGiftByMap(Map<String, Object> param);

    public List<Map<String, Object>> selectGiftListByMap(Map<String, Object> param);

    public void subRemainCount(Integer id);

    public void addRemainCount(Integer id);

}