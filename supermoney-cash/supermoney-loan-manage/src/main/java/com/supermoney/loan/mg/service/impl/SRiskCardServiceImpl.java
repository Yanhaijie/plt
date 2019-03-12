package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SRiskCardMapper;
import com.supermoney.loan.mg.entity.SRiskCard;
import com.supermoney.loan.mg.service.SRiskCardService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by bear on 2018/07/30.
 */
@Service
@Transactional
public class SRiskCardServiceImpl extends AbstractService<SRiskCard> implements SRiskCardService {
    @Resource
    private SRiskCardMapper sRiskCardMapper;

}
