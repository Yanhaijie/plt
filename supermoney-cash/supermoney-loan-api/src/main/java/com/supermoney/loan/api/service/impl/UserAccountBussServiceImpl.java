package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SUserAccountBookMapper;
import com.supermoney.loan.api.dao.SUserAccountMapper;
import com.supermoney.loan.api.dao.SUserFundMapper;
import com.supermoney.loan.api.dao.SUserMapper;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.SUserAccount;
import com.supermoney.loan.api.entity.SUserAccountBook;
import com.supermoney.loan.api.entity.SUserFund;
import com.supermoney.loan.api.service.SXenditPayService;
import com.supermoney.loan.api.service.UserAccountBussService;
import com.supermoney.loan.api.utils.BussException;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 用户账户资金业务处理
 * Created by bear on 2018/1/14.
 */
@Service
@Transactional
public class UserAccountBussServiceImpl implements UserAccountBussService {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountBussServiceImpl.class);
    @Autowired
    private SUserMapper sUserMapper;

    @Autowired
    private SUserAccountMapper sUserAccountMapper;

    @Autowired
    private SUserFundMapper sUserFundMapper;

    @Autowired
    private SUserAccountBookMapper sUserAccountBookMapper;

    /**
     * 注册时第一次初始化 资金账户 和 资金信息
     * @param userId
     */
    @Override
    public  void  firstInitAcocountAndFund(Integer userId){
        InitAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        InitFund(userId);
    }
    /**
     * 初始化用户资金账户
     * @param userId
     * @return
     */
    public  SUserAccount  InitAccount(Integer userId,Integer accountType){
        SUserAccount userAccount=new SUserAccount();
        userAccount.setUserId(userId);
        String aid=UUID.randomUUID().toString().replace("-","");
        userAccount.setAccountId(aid);//生成账户编号
        userAccount.setAccountType(accountType);
        userAccount.setAvailableAmount(BigDecimal.ZERO);
        userAccount.setFreezeAmount(BigDecimal.ZERO);
        userAccount.setAccountStatus(Constants.STATUS_USE);
        sUserAccountMapper.insert(userAccount);
        return  userAccount;
    }



    /**
     * 初始化用户资金信息
     * @param userId
     * @return
     */
    public  boolean  InitFund(Integer userId){
        SUserFund  userFund = new SUserFund();
        userFund.setUserId(userId);
        userFund.setAvailableAmount(BigDecimal.ZERO);
        userFund.setFreezeAmount(BigDecimal.ZERO);
        userFund.setWaitAmount(BigDecimal.ZERO);
        userFund.setRepaymentedAmount(BigDecimal.ZERO);
        userFund.setRewardAmount(BigDecimal.ZERO);
        userFund.setWorkAmount(BigDecimal.ZERO);
        userFund.setRechargeAmount(BigDecimal.ZERO);
        userFund.setCashAmount(BigDecimal.ZERO);
        return  sUserFundMapper.insert(userFund)>0;
    }

    /**
     * 初始化借款账户
     * @param userId
     * @return
     */
    public SUserAccount initLoanAccount(Integer userId){
        SUser user=sUserMapper.selectByPrimaryKey(userId);
        if(user==null){
            throw  new BussException("initLoanAccount faild for userId is null!");
        }
        SUserAccount userAccount=InitAccount(userId,Constants.ACCOUNT_TYPE_LOAN);
        return  userAccount;
    }

    /**
     * 获取用户账户
     * @param userId
     * @param accountType
     * @return
     */
    public  SUserAccount  getUserAccount(Integer userId,Integer accountType){
        SUserAccount userAccount=new SUserAccount();
        userAccount.setUserId(userId);
        userAccount.setAccountType(accountType);
        userAccount= sUserAccountMapper.selectOne(userAccount);
        return  userAccount;
    }

    /**
     * 获取资金信息
     * @param userId
     * @return
     */
    public  SUserFund  getUserFund(Integer userId){
        SUserFund userFund=new SUserFund();
        userFund.setUserId(userId);
        userFund=sUserFundMapper.selectOne(userFund);
        return  userFund;
    }
    /**
     * 冻结资金账户的钱
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    @Override
    public void   freezeAccountMoney(Integer bussType,Integer optType,Integer userId,BigDecimal moeny){
        SUserAccount userAccount=getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            throw  new BussException("userAccount is null");
        }
       SUserFund userFund=getUserFund(userId);
        if(userFund==null){
            throw  new BussException("userFund is null");
        }
        //可用余额不足
        if(moeny.compareTo(userAccount.getAvailableAmount())>0){
            throw  new BussException("available insufficient");
        }
        //更新资金账户
        userAccount.setAvailableAmount(userAccount.getAvailableAmount().subtract(moeny));//可用-
        userAccount.setFreezeAmount(userAccount.getFreezeAmount().add(moeny));//冻结+
        sUserAccountMapper.updateByPrimaryKey(userAccount);
        //更新资金信息表
        userFund.setAvailableAmount(userFund.getAvailableAmount().subtract(moeny));//可用-
        userFund.setFreezeAmount(userFund.getFreezeAmount().add(moeny));//冻结+
        sUserFundMapper.updateByPrimaryKey(userFund);
        //记录日志
        addAccountLog(userAccount.getAccountId(),userAccount.getUserId(),null,
                bussType,optType,moeny.multiply(new BigDecimal(-1)),userAccount.getAvailableAmount(),"");


    }

    /**
     * 冻结划出
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    @Override
    public void   freezeOutAccountMoney(Integer bussType,Integer optType,Integer userId,BigDecimal moeny){
        SUserAccount userAccount=getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            throw  new BussException("userAccount is null");
        }
        SUserFund userFund=getUserFund(userId);
        if(userFund==null){
            throw  new BussException("userFund is null");
        }
        //冻结余额不足
        if(moeny.compareTo(userAccount.getFreezeAmount())>0){
            throw  new BussException("freezeAmount insufficient");
        }

        userAccount.setFreezeAmount(userAccount.getFreezeAmount().subtract(moeny));//冻结-
        sUserAccountMapper.updateByPrimaryKey(userAccount);

        userFund.setFreezeAmount(userFund.getFreezeAmount().subtract(moeny));//冻结-
        sUserFundMapper.updateByPrimaryKey(userFund);

        //记录日志
        addAccountLog(userAccount.getAccountId(),userAccount.getUserId(),null,
                bussType,optType,moeny.multiply(new BigDecimal(-1)),userAccount.getFreezeAmount(),"freezeAmount out");

    }

    /**
     * 冻结打回
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    @Override
    public void   freezBackAccountMoney(Integer bussType,Integer optType,Integer userId,BigDecimal moeny){
        SUserAccount userAccount=getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            throw  new BussException("userAccount is null");
        }
        SUserFund userFund=getUserFund(userId);
        if(userFund==null){
            throw  new BussException("userFund is null");
        }
        //冻结余额不足
        if(moeny.compareTo(userAccount.getFreezeAmount())>0){
            throw  new BussException("freezeAmount insufficient");
        }

        //更新资金账户
        userAccount.setAvailableAmount(userAccount.getAvailableAmount().add(moeny));//可用+
        userAccount.setFreezeAmount(userAccount.getFreezeAmount().subtract(moeny));//冻结-
        sUserAccountMapper.updateByPrimaryKey(userAccount);
        //更新资金信息表
        userFund.setAvailableAmount(userFund.getAvailableAmount().add(moeny));//可用+
        userFund.setFreezeAmount(userFund.getFreezeAmount().subtract(moeny));//冻结-
        sUserFundMapper.updateByPrimaryKey(userFund);
        //记录日志
        addAccountLog(userAccount.getAccountId(),userAccount.getUserId(),null,
                bussType,optType,moeny,userAccount.getAvailableAmount(),"");

    }

    /**
     * 资金账户入账
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    @Override
    public void    inMoenyAccount(Integer bussType,Integer optType,Integer userId,BigDecimal moeny){
        SUserAccount userAccount=getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            throw  new BussException("userAccount is null");
        }
        SUserFund userFund=getUserFund(userId);
        if(userFund==null){
            throw  new BussException("userFund is null");
        }
        //更新资金账户
        userAccount.setAvailableAmount(userAccount.getAvailableAmount().add(moeny));//可用+
        sUserAccountMapper.updateByPrimaryKey(userAccount);
        //更新资金信息表
        userFund.setAvailableAmount(userFund.getAvailableAmount().add(moeny));//可用+
        sUserFundMapper.updateByPrimaryKey(userFund);
        //记录日志
        addAccountLog(userAccount.getAccountId(),userAccount.getUserId(),null,
                bussType,optType,moeny,userAccount.getAvailableAmount(),"");
    }

    /**
     * 资金账户出账
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     * @return
     */
    @Override
    public void   outMoenyAccount(Integer bussType,Integer optType,Integer userId,BigDecimal moeny){
        SUserAccount userAccount=getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            throw  new BussException("userAccount is null");
        }
        SUserFund userFund=getUserFund(userId);
        if(userFund==null){
            throw  new BussException("userFund is null");
        }
        //可用余额不足
        if(moeny.compareTo(userAccount.getAvailableAmount())>0){
            throw  new BussException("available insufficient");
        }
        //更新资金账户
        userAccount.setAvailableAmount(userAccount.getAvailableAmount().subtract(moeny));//可用-
        sUserAccountMapper.updateByPrimaryKey(userAccount);
        //更新资金信息表
        userFund.setAvailableAmount(userFund.getAvailableAmount().subtract(moeny));//可用-
        sUserFundMapper.updateByPrimaryKey(userFund);
        //记录日志
        addAccountLog(userAccount.getAccountId(),userAccount.getUserId(),null,
                bussType,optType,moeny.multiply(new BigDecimal(-1)),userAccount.getAvailableAmount(),"");
    }

    /**
     * 借款账户入账
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     */
    @Override
    public void  inMoneyLoanAccount (Integer bussType,Integer optType,Integer userId,BigDecimal moeny){
        SUserAccount userAccount=getUserAccount(userId,Constants.ACCOUNT_TYPE_LOAN);
        if(userAccount==null){
            userAccount=initLoanAccount(userId);
        }
        SUserFund userFund=getUserFund(userId);
        if(userFund==null){
            throw  new BussException("userFund is null");
        }
        userAccount.setAvailableAmount(userAccount.getAvailableAmount().add(moeny));//可用+
        sUserAccountMapper.updateByPrimaryKey(userAccount);

        userFund.setWaitAmount(userFund.getWaitAmount().add(moeny));//待还+
        sUserFundMapper.updateByPrimaryKey(userFund);

        //记录日志
        addAccountLog(userAccount.getAccountId(),userAccount.getUserId(),null,
                bussType,optType,moeny,userAccount.getAvailableAmount(),"");
    }

    /**
     * 借款账户出账
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     */
    @Override
    public void  outMoneyLoanAccount (Integer bussType,Integer optType,Integer userId,BigDecimal moeny){
        SUserAccount userAccount=getUserAccount(userId,Constants.ACCOUNT_TYPE_LOAN);
        if(userAccount==null){
            userAccount=initLoanAccount(userId);
        }
        SUserFund userFund=getUserFund(userId);
        if(userFund==null){
            throw  new BussException("userFund is null");
        }
        //汇率转换小额差值处理
        BigDecimal  differAmount=moeny.subtract(userAccount.getAvailableAmount());
        BigDecimal  smallAmount=new BigDecimal(0.001);
        boolean isCeArea=differAmount.compareTo(BigDecimal.ZERO)>0&& differAmount.compareTo(smallAmount)<0;
        if(isCeArea){
            logger.info("outMoneyLoanAccount isCeArea change money:"+moeny+" to:"+userAccount.getAvailableAmount()+" differAmount:"+differAmount);
            moeny=userAccount.getAvailableAmount();
        }
        //可用余额不足
        if(moeny.compareTo(userAccount.getAvailableAmount())>0){
            throw  new BussException("loanAccount available insufficient- AvailableAmount:"+userAccount.getAvailableAmount().toString()+" money:"+moeny.toString());
        }
        //待还金额不足
        if(moeny.compareTo(userFund.getWaitAmount())>0){
            throw  new BussException("userFund waitAmount insufficient- WaitAmount:"+userFund.getWaitAmount().toString()+" money:"+moeny.toString());
        }

        userAccount.setAvailableAmount(userAccount.getAvailableAmount().subtract(moeny));//可用-
        sUserAccountMapper.updateByPrimaryKey(userAccount);

        userFund.setWaitAmount(userFund.getWaitAmount().subtract(moeny));//待还-
        userFund.setRepaymentedAmount(userFund.getRepaymentedAmount().add(moeny));//已还+
        sUserFundMapper.updateByPrimaryKey(userFund);

        //记录日志
        addAccountLog(userAccount.getAccountId(),userAccount.getUserId(),null,
                bussType,optType,moeny.multiply(new BigDecimal(-1)),userAccount.getAvailableAmount(),"loan repay money");
    }

    /**
     * 借款账户增加冻结金额
     * 注意: 借款账户的‘冻结金额’是可以提现到 现金账户的‘可用余额’中
     *       因为此冻结金额是还款时多还的
     * @param bussType
     * @param optType
     * @param userId
     * @param moeny
     */
    public  void  inFreezeLoanAccount(Integer bussType,Integer optType,Integer userId,BigDecimal moeny){
        SUserAccount userAccount=getUserAccount(userId,Constants.ACCOUNT_TYPE_LOAN);
        if(userAccount==null){
            userAccount=initLoanAccount(userId);
        }
        userAccount.setFreezeAmount(userAccount.getFreezeAmount().add(moeny));//冻结+
        sUserAccountMapper.updateByPrimaryKey(userAccount);

        //记录日志
        addAccountLog(userAccount.getAccountId(),userAccount.getUserId(),null,
                bussType,optType,moeny,userAccount.getFreezeAmount(),"用户多还金额");
    }
    /**
     * 借款账户划账到资金账户
     * @param userId
     * @param moeny
     */
    public void  loanAccountToMoneyAccount(Integer userId,BigDecimal moeny){
        //业务暂时错误-FUNDS应增加字段 借款账户出账
        // outMoneyLoanAccount(Constants.BUSS_TYPE_ACCOUNT_BILLING,Constants.BUSS_STEP_ACCOUNT_BILLING_LOAN_OUT_TOMONEY,userId,money);
        //inMoenyAccount(Constants.BUSS_TYPE_ACCOUNT_BILLING,Constants.BUSS_STEP_ACCOUNT_BILLING_MONEY_IN_FROMLOAN,userId,money);
    }

    /**
     * 增加账户日志
     * @param accountId
     * @param userId
     * @param bussType
     * @param optType
     * @param money
     * @param afterMoney
     * @param remark
     */
    public void  addAccountLog(String accountId,Integer userId,Integer bussId,Integer bussType,Integer optType,BigDecimal money,BigDecimal afterMoney,String remark){
        SUserAccountBook accountBook=new SUserAccountBook();
        accountBook.setUserId(userId);
        accountBook.setAccountId(accountId);
        accountBook.setBussType(bussType);
        accountBook.setOptType(optType);
        accountBook.setBussId(bussId);
        accountBook.setRemark(remark);
        accountBook.setAmount(money);
        accountBook.setAfterAmount(afterMoney);
        sUserAccountBookMapper.insert(accountBook);
    }

    //入账:用户ID、业务类型、操作类型、金额

    //出账:用户ID、业务类型、操作类型、金额

    //转账:转出用户ID、转入用户ID、金额、业务类型、操作类型

    //账户日志记录:

    //账户金钱日志统计:用户ID、业务类型、操作类型、


}
