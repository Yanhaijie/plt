package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SMerchantOrder;
import com.supermoney.loan.api.utils.Mapper;

import java.util.Map;

public interface SMerchantOrderMapper extends Mapper<SMerchantOrder> {
    /**
     * 是否有未处理完成的订单
     * @param map
     * @return
     */
    public Integer bountyHasOrder (Map<String,Object> map);

    /**
     * 是否已有用户的采集数据
     * @param map
     * @return
     */
    public Integer hasUserData (Map<String,Object> map);
}