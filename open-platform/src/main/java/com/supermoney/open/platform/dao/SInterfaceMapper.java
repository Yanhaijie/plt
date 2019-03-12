package com.supermoney.open.platform.dao;

import com.supermoney.open.platform.entity.SInterface;
import com.supermoney.open.platform.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SInterfaceMapper extends Mapper<SInterface> {
    public SInterface selectInterfaceByParam(Map<String, Object> param);

    public List<SInterface> selectInterfaceListByParam(Map<String, Object> param);
}