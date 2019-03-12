package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SUserCashMapper;
import com.supermoney.loan.mg.entity.SUserCash;
import com.supermoney.loan.mg.service.SUserCashService;
import com.supermoney.loan.mg.service.UserAccountBussService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/02.
 */
@Service
@Transactional
public class SUserCashServiceImpl extends AbstractService<SUserCash> implements SUserCashService {
    @Resource
    private SUserCashMapper sUserCashMapper;

    @Resource
    private UserAccountBussService userAccountBussService;

    @Override
    public List<Map<String, Object>> getCashCheckListByStatus(Map<String, Object> param) {
        return sUserCashMapper.getCashCheckListByStatus(param);
    }

    @Override
    @Transactional
    public void doCashCheck(Map<String, Object> param, List<SUserCash> list) {
        if (param.get("status").equals("2")){
            for (SUserCash userCash:list){
                userAccountBussService.freezBackAccountMoney(Constants.BUSS_TYPE_BOUNTY,Constants.BUSS_STEP_BOUNTY_CASH_FREEZEBACK,userCash.getUserId(),userCash.getCashMoney());
            }
        }
        sUserCashMapper.doCashCheck(param);
    }
}
