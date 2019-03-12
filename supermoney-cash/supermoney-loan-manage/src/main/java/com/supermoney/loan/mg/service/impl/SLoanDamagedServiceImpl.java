package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SLoanDamagedMapper;
import com.supermoney.loan.mg.entity.SLoanDamaged;
import com.supermoney.loan.mg.service.SLoanDamagedService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by bear on 2018/07/25.
 */
@Service
@Transactional
public class SLoanDamagedServiceImpl extends AbstractService<SLoanDamaged> implements SLoanDamagedService {
    @Resource
    private SLoanDamagedMapper sLoanDamagedMapper;

}
