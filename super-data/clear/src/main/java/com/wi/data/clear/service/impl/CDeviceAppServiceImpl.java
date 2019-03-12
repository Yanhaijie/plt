package com.wi.data.clear.service.impl;

import com.wi.data.clear.dao.CDeviceAppMapper;
import com.wi.data.clear.entity.CDeviceApp;
import com.wi.data.clear.service.CDeviceAppService;
import com.wi.data.clear.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
@Service
@Transactional
public class CDeviceAppServiceImpl extends AbstractService<CDeviceApp> implements CDeviceAppService {
    @Resource
    private CDeviceAppMapper cDeviceAppMapper;

}
