package com.supermoney.loan.service.impl;

import com.supermoney.loan.dao.SUserAccountMapper;
import com.supermoney.loan.service.SUserAccountService;
import com.supermoney.loan.vo.WaitkeOutBalanceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by xionghuifeng on 2018/1/13.
 */
@Service
@Transactional
public class SUserAccountServiceImpl implements SUserAccountService {

    private static final Logger logger = LoggerFactory.getLogger(SUserAccountServiceImpl.class);

    @Autowired
    SUserAccountMapper sUserAccountMapper;

    @Override
    public List<WaitkeOutBalanceVo> getUserAccount() {
        return sUserAccountMapper.getUserAccount();
    }

    @Override
    public int getCount() {
        return sUserAccountMapper.getCount();
    }
}
