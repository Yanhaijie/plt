package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SBanner;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBannerMapper extends Mapper<SBanner> {
    public List<Map<String, Object>> selectBannerByMap(Map<String, Object> param);

    void updateBanner(SBanner banner);
}