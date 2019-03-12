package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SRoleRight;
import com.supers.p2p.assets.utils.Mapper;

import java.util.Map;

public interface SRoleRightMapper extends Mapper<SRoleRight> {
    public Map<String,Object> executeSql(Map<String,Object> param);
}