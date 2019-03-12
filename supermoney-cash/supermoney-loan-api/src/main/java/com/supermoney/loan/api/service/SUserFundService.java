package com.supermoney.loan.api.service;

import com.supermoney.loan.api.entity.SUserFund;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/13.
 */
public interface SUserFundService extends Service<SUserFund> {

    /**
     * 新增用户的金额
     *
     * @param entity
     * @return
     */
    Result addUserFund(SUserFund entity);

    /**
     * 金额在原有的基础上减少
     *
     * @param maps
     * @return
     */
    Result editUserFundReduce(Map<String, Object> maps);

    /**
     * 金额在原有的基础上增加
     *
     * @param maps
     * @return
     */
    Result editUserFundPlus(Map<String, Object> maps);

    /**
     * 根据用户Id获取当前用户的对象
     * @param userId
     * @return
     */
    Result getUserAccountEntity(Integer userId);

}
