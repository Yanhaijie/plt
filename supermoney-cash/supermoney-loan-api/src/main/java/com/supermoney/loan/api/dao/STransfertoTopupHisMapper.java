package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.STransfertoTopupHis;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface STransfertoTopupHisMapper extends Mapper<STransfertoTopupHis> {

    public void saveTopUpRecord(Map map);

    public List<Map<String,Object>> findByUserId(Map<String,Object> paramMap);

    public List<Map<String,Object>> topUpService(Map<String,Object> paramMap);

    public List<Map<String,Object>> getProductSum(Map<String,Object> paramMap);


}