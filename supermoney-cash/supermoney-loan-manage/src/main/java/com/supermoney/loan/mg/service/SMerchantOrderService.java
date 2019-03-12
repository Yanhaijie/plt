package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SMerchantOrder;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by bear on 2018/08/06.
 */
public interface SMerchantOrderService extends Service<SMerchantOrder> {

    public List<Map<String, Object>> selectByParam(Map<String, Object> param);
}
