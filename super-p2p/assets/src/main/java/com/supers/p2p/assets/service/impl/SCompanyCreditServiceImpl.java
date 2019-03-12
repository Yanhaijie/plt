package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SCompanyCreditMapper;
import com.supers.p2p.assets.entity.SCompanyCredit;
import com.supers.p2p.assets.service.SCompanyCreditService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/04.
 */
@Service
@Transactional
public class SCompanyCreditServiceImpl extends AbstractService<SCompanyCredit> implements SCompanyCreditService {
    @Resource
    private SCompanyCreditMapper sCompanyCreditMapper;

}
