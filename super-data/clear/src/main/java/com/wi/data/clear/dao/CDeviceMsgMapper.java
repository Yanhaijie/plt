package com.wi.data.clear.dao;

import com.wi.data.clear.entity.CDeviceLocation;
import com.wi.data.clear.entity.CDeviceMsg;
import com.wi.data.clear.utils.Mapper;

import java.util.List;

public interface CDeviceMsgMapper extends Mapper<CDeviceMsg> {

    public void saveOrUpdateDeviceMsg(List<CDeviceMsg> list);
}