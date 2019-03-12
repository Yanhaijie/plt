package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SFoundCompany;
import com.supers.p2p.assets.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SFoundCompanyMapper extends Mapper<SFoundCompany> {
    public Map<String, Object> selectFoundCompanyDetail(Map<String,Object> param);

    public void saveFoundCompany(SFoundCompany sFoundCompany);

    public List<Map<String, Object>> selectFoundCompanyList(Map<String, Object> param);

    public List<Map<String, Object>> selectCoopFoundCompanyList(Map<String, Object> param);
}