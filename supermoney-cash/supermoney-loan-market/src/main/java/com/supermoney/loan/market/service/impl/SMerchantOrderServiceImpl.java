package com.supermoney.loan.market.service.impl;

import com.supermoney.loan.market.dao.SMerchantOrderMapper;
import com.supermoney.loan.market.entity.SMerchantOrder;
import com.supermoney.loan.market.entity.requestVo.DemandRequestVo;
import com.supermoney.loan.market.entity.respondVo.CreditRespondVo;
import com.supermoney.loan.market.entity.respondVo.DemandRespondVo;
import com.supermoney.loan.market.service.SMerchantOrderService;
import com.supermoney.loan.market.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by bear on 2018/08/06.
 */
@Service
@Transactional
public class SMerchantOrderServiceImpl extends AbstractService<SMerchantOrder> implements SMerchantOrderService {
    @Resource
    private SMerchantOrderMapper sMerchantOrderMapper;


    @Override
    public SMerchantOrder selectOrderByOrderId(String orderId) {
        return sMerchantOrderMapper.selectOrderByOrderId(orderId);
    }

    @Override
    public List<DemandRespondVo> selectDemandOrder(DemandRequestVo requestVo) {
        return sMerchantOrderMapper.selectDemandOrder(requestVo);
    }

    @Override
    public List<CreditRespondVo> selectUserCreditInfo(String orderId) {
        return sMerchantOrderMapper.selectUserCreditInfo(orderId);
    }

    @Override
    public Map<String, Object> selectUserWorkInfo(Integer userId) {
        return sMerchantOrderMapper.selectUserWorkInfo(userId.toString());
    }

    public List<SMerchantOrder> findNeedClearUser(){
        List<SMerchantOrder> list = sMerchantOrderMapper.findNeedClearUser();
        return list;
    }

    public void updateClearResult(Map<String,Object> map){
        sMerchantOrderMapper.updateClearResult(map);
    }

    @Override
    public void updateDemandOrder(List<DemandRespondVo> param) {
        sMerchantOrderMapper.updateDemandOrder(param);
    }

}
