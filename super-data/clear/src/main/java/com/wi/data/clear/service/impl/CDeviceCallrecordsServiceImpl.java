package com.wi.data.clear.service.impl;

import com.wi.data.clear.dao.CDeviceCallrecordsMapper;
import com.wi.data.clear.entity.CDeviceCallrecords;
import com.wi.data.clear.service.CDeviceCallrecordsService;
import com.wi.data.clear.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
@Service
@Transactional
public class CDeviceCallrecordsServiceImpl extends AbstractService<CDeviceCallrecords> implements CDeviceCallrecordsService {
    @Resource
    private CDeviceCallrecordsMapper cDeviceCallrecordsMapper;

}
