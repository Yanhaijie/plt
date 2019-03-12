package com.supermoney.loan.api.service;

import com.supermoney.loan.api.entity.SAccountBalance;
import com.supermoney.loan.api.utils.Service;

import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/18.
 */
public interface SAccountBalanceService extends Service<SAccountBalance> {
    /**
     * 获取账户交易记录
     * @param maps
     * @return
     */
    SAccountBalance getAccountBalance(Map<String,Object> maps);


}
