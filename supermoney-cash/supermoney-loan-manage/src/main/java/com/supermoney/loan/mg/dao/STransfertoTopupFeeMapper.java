package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.STransfertoTopupFee;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface STransfertoTopupFeeMapper extends Mapper<STransfertoTopupFee> {

    public List<STransfertoTopupFee> selectList(Map<String,Object> param);
    public List<Map<String,Object>> topUpHistList(Map<String,Object> param);
}