package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SPersionalCreditMapper;
import com.supers.p2p.assets.entity.SPersionalCredit;
import com.supers.p2p.assets.service.SPersionalCreditService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/04.
 */
@Service
@Transactional
public class SPersionalCreditServiceImpl extends AbstractService<SPersionalCredit> implements SPersionalCreditService {
    @Resource
    private SPersionalCreditMapper sPersionalCreditMapper;

}
