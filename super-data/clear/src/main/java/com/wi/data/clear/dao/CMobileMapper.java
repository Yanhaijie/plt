package com.wi.data.clear.dao;

import com.wi.data.clear.entity.CDeviceMsg;
import com.wi.data.clear.entity.CMobile;
import com.wi.data.clear.utils.Mapper;

import java.util.List;

public interface CMobileMapper extends Mapper<CMobile> {

    public void saveOrUpdateMobile(List<CMobile> list);
}