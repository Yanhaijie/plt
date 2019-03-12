package com.wi.data.clear.dao;

import com.wi.data.clear.entity.CDevice;
import com.wi.data.clear.utils.Mapper;

import java.util.List;

public interface CDeviceMapper extends Mapper<CDevice> {

    public void saveOrUpdateDevice(List<CDevice> list);
}