package com.supermoney.loan.api.service;

import com.supermoney.loan.api.entity.SUserPay;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wenyuhao on 2018/04/25.
 */
public interface SUserPayService extends Service<SUserPay> {

    public BigDecimal getPaySum(SUserPay sUserPay);
    /**
     * 查询
     * @param
     * @return
     */
    public List<SUserPay> getList(SUserPay sUserPay);

    /**
     * 领取奖励
     * @param
     * @return
     */
    public Result getAwardForNewUser(SUserPay sUserPay);
    /**
     *用户借款订单的打款操作
     */
    public  void  loanOrderToUser();

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    public  boolean  updateStatus(Integer id,Integer status);



}
