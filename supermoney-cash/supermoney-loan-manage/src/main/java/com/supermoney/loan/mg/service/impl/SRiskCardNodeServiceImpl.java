package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SRiskCardNodeMapper;
import com.supermoney.loan.mg.entity.SRiskCardNode;
import com.supermoney.loan.mg.service.SRiskCardNodeService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by bear on 2018/07/30.
 */
@Service
@Transactional
public class SRiskCardNodeServiceImpl extends AbstractService<SRiskCardNode> implements SRiskCardNodeService {
    @Resource
    private SRiskCardNodeMapper sRiskCardNodeMapper;

}
