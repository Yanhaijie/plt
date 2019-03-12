package com.supermoney.loan.market.dao;

import com.supermoney.loan.market.entity.SNikInfo;
import com.supermoney.loan.market.utils.Mapper;

public interface SNikInfoMapper extends Mapper<SNikInfo> {

    public SNikInfo selectNikInfoByNik(String nik);
}