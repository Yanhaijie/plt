package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SCompanyMapper extends Mapper<SCompany> {

    public List<SCompany> selectList(Map<String,Object> map);

    public List<Map<String,Object>> selectCompanyExeclModel();

    public List<Map<String,Object>> selectAssetCompanyExeclModel();

    public List<Map<String,Object>> selectFoundCompanyExeclModel();
}