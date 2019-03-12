package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SLoanOrder;
import com.supermoney.loan.api.utils.Mapper;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;
import java.util.Map;

public interface SLoanOrderMapper extends Mapper<SLoanOrder> {
    public List<SLoanOrder> selectList(Map<String,Object> map);

    public List<Map<String, Object>> selectOrderList(Map<String,Object> map);

    public Map<String, Object> selectOrderDetail(Map<String,Object> map);

    /**
     * 订单还款账户
     * @param map
     * @return
     */
    public Map<String, Object> selectOrderVirtualAccount(Map<String,Object> map);

    /**
     * 实名且有审核通过的借款订单
     * @param map
     * @return
     */
    public  Map<String,Object> identityOrder(Map<String,Object> map);

    /**
     * 是否有未处理完成的订单
     * @param map
     * @return
     */
    public Integer bountyHasOrder (Map<String,Object> map);

    /**
     * 根据   还款账户ID 或者 逾期还款账户ID
     * @param map
     * @return
     */
    public  SLoanOrder  selectByVirtualId(Map<String,Object> map);
}