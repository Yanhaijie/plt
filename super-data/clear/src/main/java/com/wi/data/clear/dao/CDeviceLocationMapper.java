package com.wi.data.clear.dao;

import com.wi.data.clear.entity.CDeviceCallrecords;
import com.wi.data.clear.entity.CDeviceLocation;
import com.wi.data.clear.utils.Mapper;

import java.util.List;

public interface CDeviceLocationMapper extends Mapper<CDeviceLocation> {

    public void saveOrUpdateDeviceLocation(List<CDeviceLocation> list);
}