package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SPersonalInfoMapper;
import com.supers.p2p.assets.entity.SPersonalInfo;
import com.supers.p2p.assets.service.SPersonalInfoService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/04.
 */
@Service
@Transactional
public class SPersonalInfoServiceImpl extends AbstractService<SPersonalInfo> implements SPersonalInfoService {
    @Resource
    private SPersonalInfoMapper sPersonalInfoMapper;

}
