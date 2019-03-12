package com.supermoney.open.platform.dao;

import com.supermoney.open.platform.entity.SOcr;
import com.supermoney.open.platform.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SOcrMapper extends Mapper<SOcr> {
    public List<SOcr> selectOcrListByParam(Map<String, Object> param);
}