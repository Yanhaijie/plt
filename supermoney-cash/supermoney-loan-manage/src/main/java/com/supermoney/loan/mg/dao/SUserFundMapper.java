package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SUserFund;
import com.supermoney.loan.mg.entity.vo.SUserFundVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/13.
 */
public interface SUserFundMapper extends Mapper<SUserFund> {

    /**
     * 新增用户的金额
     *
     * @param entity
     * @return
     */
    Integer addUserFund(SUserFund entity);

    /**
     * 金额在原有的基础上减少
     *
     * @param maps
     * @return
     */
    Integer editUserFundReduce(Map<String, Object> maps);

    /**
     * 金额在原有的基础上增加
     *
     * @param maps
     * @return
     */
    Integer editUserFundPlus(Map<String, Object> maps);

    /**
     * 根据用户Id获取当前用户的对象
     *
     * @param maps
     * @return
     */
    List<SUserFundVo> getUserFundEntity(Map<String, Object> maps);

}
