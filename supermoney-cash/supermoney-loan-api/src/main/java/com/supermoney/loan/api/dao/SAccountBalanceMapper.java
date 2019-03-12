package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SAccountBalance;
import com.supermoney.loan.api.utils.Mapper;

import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/18.
 */
public interface SAccountBalanceMapper extends Mapper<SAccountBalance> {

    SAccountBalance getbalanceByOrderSn(Map<String, Object> maps);

}
