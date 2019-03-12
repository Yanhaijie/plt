package com.supermoney.loan.market.service;

import com.supermoney.loan.market.entity.SMerchantOrder;
import com.supermoney.loan.market.entity.requestVo.DemandRequestVo;
import com.supermoney.loan.market.entity.respondVo.CreditRespondVo;
import com.supermoney.loan.market.entity.respondVo.DemandRespondVo;
import com.supermoney.loan.market.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by bear on 2018/08/06.
 */
public interface SMerchantOrderService extends Service<SMerchantOrder> {
    public List<SMerchantOrder> findNeedClearUser();

    public SMerchantOrder selectOrderByOrderId(String orderId);

    public List<DemandRespondVo> selectDemandOrder(DemandRequestVo requestVo);

    public List<CreditRespondVo> selectUserCreditInfo(String orderId);

    public Map<String, Object> selectUserWorkInfo(Integer userId);

    public void updateClearResult(Map<String,Object> map);

    public void updateDemandOrder(List<DemandRespondVo> param);

}
