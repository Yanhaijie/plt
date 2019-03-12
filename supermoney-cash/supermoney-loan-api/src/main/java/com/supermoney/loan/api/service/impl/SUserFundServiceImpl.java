package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SUserFundMapper;
import com.supermoney.loan.api.entity.SUserFund;
import com.supermoney.loan.api.entity.vo.SUserFundVo;
import com.supermoney.loan.api.service.SUserFundService;
import com.supermoney.loan.api.utils.AbstractService;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/13.
 */
@Service
@Transactional
public class SUserFundServiceImpl extends AbstractService<SUserFund> implements SUserFundService {


    @Resource
    private SUserFundMapper userFundMapper;

    @Override
    public Result addUserFund(SUserFund entity) {
        if (entity.getUserId() == null) {
            throw new RuntimeException("not get user key");
        }
        Integer result = userFundMapper.addUserFund(entity);
        return result > 0 ? ResultGenerator.genSuccessResult() : ResultGenerator.genFailResult("service error");
    }

    @Override
    public Result editUserFundReduce(Map<String, Object> maps) {
        Integer result = userFundMapper.editUserFundReduce(maps);
        if (result > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("service error");
        }
    }

    @Override
    public Result editUserFundPlus(Map<String, Object> maps) {
        Integer result = userFundMapper.editUserFundPlus(maps);
        if (result > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("service error");
        }
    }


    @Override
    public Result getUserAccountEntity(Integer userId) {

        if (userId != null) {
            Map<String, Object> map = new HashMap();
            map.put("userId", userId);

            SUserFundVo voEntity = userFundMapper.getUserFundEntity(map).get(0);
            if (voEntity != null) {
                return ResultGenerator.genSuccessResult(voEntity);
            } else {
                SUserFund  entity = new SUserFund();
                entity.setUserId(userId);
                entity.setAvailableAmount(BigDecimal.ZERO);
                entity.setFreezeAmount(BigDecimal.ZERO);
                entity.setWaitAmount(BigDecimal.ZERO);
                entity.setRepaymentedAmount(BigDecimal.ZERO);
                entity.setRewardAmount(BigDecimal.ZERO);
                entity.setWorkAmount(BigDecimal.ZERO);
                entity.setRechargeAmount(BigDecimal.ZERO);
                entity.setCashAmount(BigDecimal.ZERO);
                entity.setCreateTime(new Date());
                entity.setUpdateTime(new Date());
                entity.setOpt(userId.toString());
                userFundMapper.addUserFund(entity);

                return ResultGenerator.genSuccessResult(userFundMapper.getUserFundEntity(map).get(0));
            }
        }
        return ResultGenerator.genFailResult("service userid not parment ");
    }

}
