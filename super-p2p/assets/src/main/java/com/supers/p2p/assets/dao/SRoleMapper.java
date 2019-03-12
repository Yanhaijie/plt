package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SRole;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SRoleMapper extends Mapper<SRole> {
    /**
     *
     * @param map
     * @return
     */
    public List<SRole> selectList(Map<String,Object> map);
}