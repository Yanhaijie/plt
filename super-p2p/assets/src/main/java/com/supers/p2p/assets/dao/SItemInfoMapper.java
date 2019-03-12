package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SItemInfo;
import com.supers.p2p.assets.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SItemInfoMapper extends Mapper<SItemInfo> {

    public List<Map<String, Object>> selectAssetItemPoolList(Map<String ,Object> param);
    public List<Map<String, Object>> selectAssetItemCarList(Map<String ,Object> param);
    public List<Map<String, Object>> selectList(Map<String,Object> map);
    public List<Map<String, Object>> selectDetailList(Map<String,Object> map);
    public List<SItemInfo> selectLockItem(Map<String,Object> map);
    public void unLockItem();
    public void exportCar(Map<String ,Object> param);


}