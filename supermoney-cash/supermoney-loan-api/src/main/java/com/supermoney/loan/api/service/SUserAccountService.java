package com.supermoney.loan.api.service;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.entity.SAccountBalance;
import com.supermoney.loan.api.entity.SUserAccount;
import com.supermoney.loan.api.entity.SUserCash;
import com.supermoney.loan.api.entity.SUserPay;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.entity.vo.XenditCallBackVo;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/13.
 */
public interface SUserAccountService extends Service<SUserAccount> {

    /**
     * 现金账户提现处理
     * @param userId
     * @param cardAccount
     * @param amount
     * @param areaCode
     * @return
     */
    Result  moneyAccountCash(SUserCash userCash, Integer userId, String cardAccount , BigDecimal amount, Integer areaCode);
    /**
     * 现金账户用户借款打款
     * @param userPay
     * @param userId
     * @param cardAccount
     * @param amount
     * @param areaCode
     * @return
     */
    public  Result  moneyAccountLoanOrder(SUserPay userPay, Integer userId, String cardAccount , BigDecimal amount, Integer areaCode);

    /**
     * 现金账户提现回调处理
     * @param obj
     * @param callBackToken
     * @return
     */
      Result moneyAccountCashCallBack(JSONObject obj,String callBackToken);
    /**
     * 全部提现
     * @param userId
     * @return
     */
      Result moneyAllCash(Integer userId);
    /**
     * 印尼国家提现处理
     * @param userCash
     * @param userCard
     * @param userAccount
     * @param cashUsd
     * @param userId
     * @return
     */
    public  Result cashByIndonesia(SUserCash userCash,SUserBindVo userCard,SUserAccount userAccount, BigDecimal cashUsd,Integer userId);
    /**
     *  用户提现回调业务处理
     * @param vo
     * @param balance
     */
    public Result  loanOrderCallBackBuss(XenditCallBackVo vo,SAccountBalance balance );
    public  void  test();


}
