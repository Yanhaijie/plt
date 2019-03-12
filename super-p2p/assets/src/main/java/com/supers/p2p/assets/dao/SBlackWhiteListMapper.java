package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SBlackWhiteList;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.vo.AssetFoundVo;
import com.supers.p2p.assets.utils.Mapper;
import com.supers.p2p.assets.utils.Result;

import java.util.List;
import java.util.Map;

public interface SBlackWhiteListMapper extends Mapper<SBlackWhiteList> {

    public List<SBlackWhiteList> selectListByObj(SBlackWhiteList sBlackWhiteList);

    public int deleteByObj(SBlackWhiteList sBlackWhiteList);

    public List<Map<String,Object>> getMyAssetCompanyList(Map<String,Object> map);

    public List<Map<String,Object>> selectAssetFoundRelation(Map<String,Object> map);

}