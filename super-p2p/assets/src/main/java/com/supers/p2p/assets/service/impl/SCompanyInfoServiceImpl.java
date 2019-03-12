package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SCompanyInfoMapper;
import com.supers.p2p.assets.entity.SCompanyInfo;
import com.supers.p2p.assets.service.SCompanyInfoService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/04.
 */
@Service
@Transactional
public class SCompanyInfoServiceImpl extends AbstractService<SCompanyInfo> implements SCompanyInfoService {
    @Resource
    private SCompanyInfoMapper sCompanyInfoMapper;

}
