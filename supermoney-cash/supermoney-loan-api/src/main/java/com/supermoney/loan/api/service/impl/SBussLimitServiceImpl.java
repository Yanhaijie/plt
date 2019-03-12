package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SBussLimitMapper;
import com.supermoney.loan.api.entity.SBussLimit;
import com.supermoney.loan.api.service.SBussLimitService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by @author on 2018/08/08.
 */
@Service
@Transactional
public class SBussLimitServiceImpl extends AbstractService<SBussLimit> implements SBussLimitService {
    @Resource
    private SBussLimitMapper sBussLimitMapper;

}
