package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SMerchantOrderMapper;
import com.supermoney.loan.mg.entity.SMerchantOrder;
import com.supermoney.loan.mg.service.SMerchantOrderService;
import com.supermoney.loan.mg.utils.AbstractService;
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
    public List<Map<String, Object>> selectByParam(Map<String, Object> param) {
        return sMerchantOrderMapper.selectByParam(param);
    }


}
