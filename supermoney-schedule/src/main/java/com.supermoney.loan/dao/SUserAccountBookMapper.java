package com.supermoney.loan.dao;

import com.supermoney.loan.vo.WaitkeOutBalanceVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SUserAccountBookMapper{
    List<WaitkeOutBalanceVo> getAllUserAccountBook();

    int getCount();
}