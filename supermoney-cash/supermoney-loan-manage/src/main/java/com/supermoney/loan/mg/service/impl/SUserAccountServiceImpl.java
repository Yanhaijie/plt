package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SUserAccountMapper;
import com.supermoney.loan.mg.dao.SUserMapper;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.entity.SUserAccount;
import com.supermoney.loan.mg.service.SUserAccountService;
import com.supermoney.loan.mg.service.SUserService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/13.
 */
@Service
@Transactional
public class SUserAccountServiceImpl extends AbstractService<SUserAccount> implements SUserAccountService {
    @Resource
    private SUserAccountMapper sUserAccountMapper;

    @Override
    public List<Map<String, Object>> getAccountList(Map<String, Object> param) {
        return sUserAccountMapper.getAccountList(param);
    }
}
