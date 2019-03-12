package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SUserCashMapper;
import com.supermoney.loan.api.entity.SUserAccount;
import com.supermoney.loan.api.entity.SUserBind;
import com.supermoney.loan.api.entity.SUserCash;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.AbstractService;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.rowset.CachedRowSet;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/01.
 */
@Service
@Transactional
public class SUserCashServiceImpl extends AbstractService<SUserCash> implements SUserCashService {
    private static final Logger logger = LoggerFactory.getLogger(SUserCashServiceImpl.class);
    @Resource
    private SUserCashMapper sUserCashMapper;
    @Resource
    private SUserAccountService sUserAccountService;

    @Resource
    private UserAccountBussService userAccountBussService;
    @Resource
    private SUserBindService sUserBindService;

    @Resource
    private SExchangeRateService sExchangeRateService;


    /**
     * 提现审核状态的数量
     * @param userId
     * @param status
     * @return
     */
    @Override
    public  Integer countByStatus(Integer userId,Integer status){
        Map<String,Object> param=new HashMap(Constants.App.MAP_MIN_SIZE);
        param.put("userId",userId);
        param.put("status",status);
        return  sUserCashMapper.statusCashTotal(param);
    }

    /**
     * 处理提现审核通过的打款操作
     */
    @Override
    public void  transferToUser(){
        //审核通过的单
     //  sUserAccountService.test();
        List<SUserCash> cashList=getByStatus(1,null);
        for(SUserCash cash:cashList){
            logger.info("cash-ordedr:"+cash.getId());
            SUserBind userCard=sUserBindService.getUserUseCard(cash.getUserId());
            if(userCard==null){
                logger.info("not bind card - cashId"+ cash.getId());
                continue;
            }
            Result result = sUserAccountService.moneyAccountCash(cash, cash.getUserId(), userCard.getCardAccount(), cash.getCashMoney(), cash.getAreaCode());
            Result cashResult= result;
           logger.info("cashResult:"+cashResult.getMessage());
        }
    }

    /**
     * 状态获取
     * @param status
     * @return
     */
    public  List<SUserCash> getByStatus(Integer status,Integer userId){
        SUserCash param=new SUserCash();
        param.setCashStatus(status);
        if(userId!=null){
            param.setUserId(userId);
        }
        return  sUserCashMapper.select(param);
    }
    /**
     * 用户最近一条审核中提现
     * @param userId
     * @return
     */
    @Override
    public Result lastCash(Integer userId){
        SUserCash lastCash=sUserCashMapper.lastCash(userId);
        if(lastCash==null){
            return  ResultGenerator.genSuccessResult();
        }
        Map<String,Object> map=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        map.put("auditMoney",sExchangeRateService.usdToByCountry(Constants.Country.INDONESIA_CR,lastCash.getCashMoney(),true));
        return  ResultGenerator.genSuccessResult(map);
    }

    /**
     * 更新cash状态
     * @param cashId
     * @param cashStatus
     * @return
     */
    @Override
    public  boolean  updateCashStatus(Integer cashId,Integer cashStatus){
        SUserCash userCash=sUserCashMapper.selectByPrimaryKey(cashId);
        if(userCash==null){
            return false;
        }
        userCash.setCashStatus(cashStatus);
       return sUserCashMapper.updateByPrimaryKeySelective(userCash) >0 ;
    }


}
