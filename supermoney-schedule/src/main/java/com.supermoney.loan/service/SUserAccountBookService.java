package com.supermoney.loan.service;

import com.supermoney.loan.vo.WaitkeOutBalanceVo;

import java.util.List;

/**
 * Created by xionghuifeng on 2018/01/18.
 */
public interface SUserAccountBookService{

    List<WaitkeOutBalanceVo> getAllUserAccountBook();

    int getCount();
}
