package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SBanner;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SBannerMapper extends Mapper<SBanner> {
    public List<Map<String ,Object>> selectBannerByParam(Map<String, Object> param);
}