package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SAccountBalanceMapper;
import com.supermoney.loan.api.entity.SAccountBalance;
import com.supermoney.loan.api.service.SAccountBalanceService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/18.
 */
@Service
@Transactional
public class SAccountBalanceServiceImpl extends AbstractService<SAccountBalance> implements SAccountBalanceService {

    @Resource
    private SAccountBalanceMapper accountBalanceMapper;

    @Override
    public SAccountBalance getAccountBalance(Map<String, Object> maps) {
        return accountBalanceMapper.getbalanceByOrderSn(maps);
    }
}
