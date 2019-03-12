package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SRiskCardNodeConditionMapper;
import com.supermoney.loan.mg.entity.SRiskCardNodeCondition;
import com.supermoney.loan.mg.service.SRiskCardNodeConditionService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by bear on 2018/07/30.
 */
@Service
@Transactional
public class SRiskCardNodeConditionServiceImpl extends AbstractService<SRiskCardNodeCondition> implements SRiskCardNodeConditionService {
    @Resource
    private SRiskCardNodeConditionMapper sRiskCardNodeConditionMapper;

}
