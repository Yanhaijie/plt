package com.wi.data.clear.dao;

import com.wi.data.clear.entity.CDevice;
import com.wi.data.clear.entity.CDeviceApp;
import com.wi.data.clear.utils.Mapper;

import java.util.List;

public interface CDeviceAppMapper extends Mapper<CDeviceApp> {
    public void saveOrUpdateDeviceApp(List<CDeviceApp> list);
}