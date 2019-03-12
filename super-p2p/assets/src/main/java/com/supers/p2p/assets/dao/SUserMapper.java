package com.supers.p2p.assets.dao;

import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.entity.vo.SUserVo;
import com.supers.p2p.assets.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserMapper extends Mapper<SUser> {

    public List<SUserVo> selectList(Map<String,Object> map);
}