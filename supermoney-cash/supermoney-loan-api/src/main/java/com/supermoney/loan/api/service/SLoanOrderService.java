package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SLoanOrder;
import com.supermoney.loan.api.entity.vo.LoanMoneyLimitRateVo;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/29.
 */
public interface SLoanOrderService extends Service<SLoanOrder> {

    /**
     * 借款下单
     * @param bountyId
     * @param needAmount
     * @param limit
     * @param unit
     * @param reason
     * @param countryCode
     * @param userId
     * @return
     */
    public Result toOrder(Integer bountyId, Integer needAmount, Integer limit, String unit, String reason, String countryCode, Integer userId);
    /**
     * 获取借钱金额区间利率
     * @return
     */
    public List<LoanMoneyLimitRateVo> getLoanLimitRate();
    /**
     * 获取产品计算配置参数
     * @return
     */
    public  Result getloanSet(Integer bountyId);
    /**
     * 获取订单列表
     * @return
     */
    public  Result orderList(Integer orderType, Integer userId, Integer page, Integer size);
    /**
     * 获取订单详情
     * @return
     */
    public  Result orderDetail(Integer orderId, Integer userId);

    /**
     * 借款订单回调业务,更新状态和还款生效时间
     * @param userId
     * @param payId
     * @return
     */
    public Result orderCallBackBuss(Integer userId, Integer payId);
    /**
     * 借款订单还款业务
     * @param virtualId
     * @param amount
     * @return
     */
    public  Result orderRepay(String virtualId,BigDecimal amount);

    /**
     * 逾期费用计算
     * @param orderCode
     */
    public  void  overdueMoneyCount(String  orderCode);

    /**
     * 获取订单还款账户
     * @return
     */
    public Map<String, Object> orderVirtualAccount(Integer orderId, Integer userId);
    /**
     * 实名且有订单
     * @param userId
     * @return
     */
    public Map<String,Object> hasIdentityOrder(Integer userId);

    public  void oneceRepay(SLoanOrder order,BigDecimal amount,BigDecimal usdAmount);
    /**
     * 解决回调错误重新执行业务
     * @param bussType
     * @param relateId
     * @return
     */
    public Result  fixRepayCallBack(Integer bussType,String relateId);

}
