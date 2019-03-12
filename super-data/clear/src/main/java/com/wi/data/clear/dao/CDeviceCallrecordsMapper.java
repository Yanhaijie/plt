package com.wi.data.clear.dao;

import com.wi.data.clear.entity.CDevice;
import com.wi.data.clear.entity.CDeviceCallrecords;
import com.wi.data.clear.utils.Mapper;

import java.util.List;

public interface CDeviceCallrecordsMapper extends Mapper<CDeviceCallrecords> {

    public void saveOrUpdateCallrecords(List<CDeviceCallrecords> list);
}