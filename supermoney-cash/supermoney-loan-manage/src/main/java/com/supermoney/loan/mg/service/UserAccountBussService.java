package com.supermoney.loan.mg.service;

import com.supermoney.loan.mg.entity.SUserAccount;

import java.math.BigDecimal;

/**
 * Created by bear on 2018/1/14.
 */
public interface UserAccountBussService {
    /**
     * 获取用户账户
     * @param userId
     * @param accountType
     * @return
     */
    public SUserAccount getUserAccount(Integer userId, Integer accountType);

    /**
     * 注册时第一次初始化 资金账户 和 资金信息
     * @param userId
     */
    public  void  firstInitAcocountAndFund(Integer userId);
    /**
     * 冻结资金账户的钱
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    public void freezeAccountMoney(Integer bussType, Integer optType, Integer userId, BigDecimal moeny);
    /**
     * 冻结划出
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    public void   freezeOutAccountMoney(Integer bussType,Integer optType,Integer userId,BigDecimal moeny);
    /**
     * 冻结打回
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    public void   freezBackAccountMoney(Integer bussType,Integer optType,Integer userId,BigDecimal moeny);
    /**
     * 资金账户入账
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    public void   inMoenyAccount(Integer bussType,Integer optType,Integer userId,BigDecimal moeny);
    /**
     * 资金账户出账
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    public void   outMoenyAccount(Integer bussType,Integer optType,Integer userId,BigDecimal moeny);
    /**
     * 借款账户出账
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     */
    public void  outMoneyLoanAccount (Integer bussType,Integer optType,Integer userId,BigDecimal moeny);
    /**
     * 借款账户入账
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     */
    public void  inMoneyLoanAccount (Integer bussType,Integer optType,Integer userId,BigDecimal moeny);
}
