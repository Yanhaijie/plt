package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SRight;
import com.supers.p2p.assets.entity.SRole;
import com.supers.p2p.assets.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SRightMapper extends Mapper<SRight> {
    /**
     *
     * @param map
     * @return
     */
    public List<SRight> selectList(Map<String,Object> map);

    /**
     *
     * @param map
     * @return
     */
    public List<SRight> userRight(Map<String,Object> map);
}