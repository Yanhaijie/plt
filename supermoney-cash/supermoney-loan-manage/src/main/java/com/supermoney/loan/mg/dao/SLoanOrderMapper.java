package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.entity.vo.SAtCreditInformationVo;
import com.supermoney.loan.mg.entity.vo.SLoanOrderVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SLoanOrderMapper extends Mapper<SLoanOrder> {

    public List<SLoanOrderVo> selectList(Map<String,Object> map);

    /**
     * 
     * @param map
     * @return
     */
    public  List<SLoanOrder> overdueList (Map<String,Object> map);

    public List<SLoanOrder> selectBlackListOrderList();

}