package com.wi.data.clear.service.impl;

import com.wi.data.clear.dao.CDeviceMsgMapper;
import com.wi.data.clear.entity.CDeviceMsg;
import com.wi.data.clear.service.CDeviceMsgService;
import com.wi.data.clear.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
@Service
@Transactional
public class CDeviceMsgServiceImpl extends AbstractService<CDeviceMsg> implements CDeviceMsgService {
    @Resource
    private CDeviceMsgMapper cDeviceMsgMapper;

}
