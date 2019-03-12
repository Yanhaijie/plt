package com.wi.data.clear.service.impl;

import com.wi.data.clear.dao.CDeviceLocationMapper;
import com.wi.data.clear.entity.CDeviceLocation;
import com.wi.data.clear.service.CDeviceLocationService;
import com.wi.data.clear.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
@Service
@Transactional
public class CDeviceLocationServiceImpl extends AbstractService<CDeviceLocation> implements CDeviceLocationService {
    @Resource
    private CDeviceLocationMapper cDeviceLocationMapper;

}
