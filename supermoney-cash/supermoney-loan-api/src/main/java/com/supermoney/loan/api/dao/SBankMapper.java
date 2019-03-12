package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SBank;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/15.
 */
public interface SBankMapper extends Mapper<SBank> {
    List<SBank> getBankList(Map<String, Object> maps);
}
