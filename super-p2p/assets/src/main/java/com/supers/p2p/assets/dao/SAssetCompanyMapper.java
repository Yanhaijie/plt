package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SAssetCompany;
import com.supers.p2p.assets.entity.vo.SAssetCompanyVo;
import com.supers.p2p.assets.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SAssetCompanyMapper extends Mapper<SAssetCompany> {

    public List<Map<String,Object>> getAssetCompanyPoolList(Map<String,Object> map);

    public List<SAssetCompanyVo> findByParam(Map<String,Object> map);
}