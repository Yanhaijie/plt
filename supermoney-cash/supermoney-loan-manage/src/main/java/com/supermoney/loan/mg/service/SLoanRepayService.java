package com.supermoney.loan.mg.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SLoanGrant;
import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.entity.SLoanRepay;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/06/04.
 */
public interface SLoanRepayService extends Service<SLoanRepay> {
    /**
     * 生成借款还款账单
     * @param order
     * @param grant
     */
    public  void  loanOrderRepay(SLoanOrder order, SLoanGrant grant);
    /**
     * 分页
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);
    /**
     * 根据借款单号获取
     * @param userId
     * @param orderCode
     * @return
     */
    public List<SLoanRepay> getByOrderCode(Integer userId, String orderCode);


    /**
     * 用户还款记录
     * @param page
     * @param size
     * @param param
     * @return
     */
    public  PageInfo getRecordByPage(int page, int size, Map<String,Object> param);
    /**
     * 报损
     * @param orderCode
     * @param damagedAmountType
     * @param damagedType
     * @param orderStatus
     * @return
     */
    public Result damaged(String orderCode, Integer damagedAmountType, Integer damagedType, Integer orderStatus);

}
