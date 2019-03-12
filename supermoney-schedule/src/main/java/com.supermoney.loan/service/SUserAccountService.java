package com.supermoney.loan.service;

import com.supermoney.loan.vo.WaitkeOutBalanceVo;

import java.util.List;

/**
 * Created by tangwenchi on 2018/1/13.
 */
public interface SUserAccountService {


    List<WaitkeOutBalanceVo> getUserAccount();

    int getCount();
}
