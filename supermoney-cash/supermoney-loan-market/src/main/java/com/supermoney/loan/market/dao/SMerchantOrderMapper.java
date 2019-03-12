package com.supermoney.loan.market.dao;

import com.supermoney.loan.market.entity.SMerchantOrder;
import com.supermoney.loan.market.entity.requestVo.DemandRequestVo;
import com.supermoney.loan.market.entity.respondVo.CreditRespondVo;
import com.supermoney.loan.market.entity.respondVo.DemandRespondVo;
import com.supermoney.loan.market.utils.Mapper;

import java.util.List;
import java.util.Map;


public interface SMerchantOrderMapper extends Mapper<SMerchantOrder> {

    public SMerchantOrder selectOrderByOrderId(String orderId);

    public List<DemandRespondVo> selectDemandOrder(DemandRequestVo requestVo);

    public void updateDemandOrder(List<DemandRespondVo> param);

    public List<SMerchantOrder> findNeedClearUser();

    public List<CreditRespondVo> selectUserCreditInfo(String orderId);

    public Map<String, Object> selectUserWorkInfo(String userId);

    public void updateClearResult(Map<String,Object> map);
}