package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SMerchantOrder;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SMerchantOrderMapper extends Mapper<SMerchantOrder> {

    public List<Map<String, Object>> selectByParam(Map<String, Object> param);
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