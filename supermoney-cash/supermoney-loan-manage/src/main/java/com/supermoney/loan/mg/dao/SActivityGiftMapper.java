package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SActivityGift;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SActivityGiftMapper extends Mapper<SActivityGift> {

    public List<Map<String, Object>> selectGiftByMap(Map<String,Object> param);

    public List<Map<String, Object>> selectSearchGiftByMap(Map<String,Object> param);

    public List<Map<String, Object>> selectBackGiftByMap(Map<String,Object> param);

}