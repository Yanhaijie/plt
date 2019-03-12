package com.supermoney.loan.market.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "s_merchant_order")
public class SMerchantOrder {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品编号
     */
    @Column(name = "bounty_id")
    private Integer bountyId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 商户ID
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 商户产品编号
     */
    @Column(name = "m_order_code")
    private String mOrderCode;

    /**
     * 订单业务编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 借款理由
     */
    @Column(name = "loan_reason")
    private String loanReason;

    /**
     * 借款金额
     */
    @Column(name = "loan_amount")
    private BigDecimal loanAmount;

    /**
     * 借款利率
     */
    private BigDecimal rate;

    /**
     * 服务费利率
     */
    @Column(name = "fee_rate")
    private BigDecimal feeRate;

    /**
     * 还款时长
     */
    private Integer duration;

    /**
     * 还款方式【0: 服务费利息前置 1：服务费前置利息后置】
     */
    @Column(name = "repay_method")
    private Integer repayMethod;

    /**
     * 到账金额
     */
    @Column(name = "got_amount")
    private BigDecimal gotAmount;

    /**
     * 利息费
     */
    @Column(name = "interest_amount")
    private BigDecimal interestAmount;

    /**
     * 服务费
     */
    @Column(name = "fee_amount")
    private BigDecimal feeAmount;

    /**
     * 逾期费
     */
    @Column(name = "overdue_amount")
    private BigDecimal overdueAmount;

    /**
     * 逾期天数
     */
    @Column(name = "overdue_limit")
    private Integer overdueLimit;

    /**
     * 待还总额
     */
    @Column(name = "wait_repay_amount")
    private BigDecimal waitRepayAmount;

    /**
     * 已还总额
     */
    @Column(name = "repaymented_amount")
    private BigDecimal repaymentedAmount;

    /**
     * 计划还款到期时间
     */
    @Column(name = "plan_repayment_time")
    private Date planRepaymentTime;

    /**
     * 还款完成时间
     */
    @Column(name = "repayment_time")
    private Date repaymentTime;

    /**
     * 订单状态 0待审核 1审核通过 2审核失败  3待还款 4放款失败 5逾期 6还款完成 
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 回款账号ID
     */
    @Column(name = "paycack_account_id")
    private String paycackAccountId;

    /**
     * 国家地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;


    /**
     * 用户数据接收状态 1:商家未接受数据，2：商家已接收数据
     */
    @Column(name = "use_status")
    private Integer useStatus;

    @Column(name = "user_name")
    private String userName;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取产品编号
     *
     * @return bounty_id - 产品编号
     */
    public Integer getBountyId() {
        return bountyId;
    }

    /**
     * 设置产品编号
     *
     * @param bountyId 产品编号
     */
    public void setBountyId(Integer bountyId) {
        this.bountyId = bountyId;
    }

    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取商户ID
     *
     * @return merchant_id - 商户ID
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户ID
     *
     * @param merchantId 商户ID
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取商户产品编号
     *
     * @return m_order_code - 商户产品编号
     */
    public String getmOrderCode() {
        return mOrderCode;
    }

    /**
     * 设置商户产品编号
     *
     * @param mOrderCode 商户产品编号
     */
    public void setmOrderCode(String mOrderCode) {
        this.mOrderCode = mOrderCode;
    }

    /**
     * 获取订单业务编号
     *
     * @return order_code - 订单业务编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单业务编号
     *
     * @param orderCode 订单业务编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取借款理由
     *
     * @return loan_reason - 借款理由
     */
    public String getLoanReason() {
        return loanReason;
    }

    /**
     * 设置借款理由
     *
     * @param loanReason 借款理由
     */
    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
    }

    /**
     * 获取借款金额
     *
     * @return loan_amount - 借款金额
     */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    /**
     * 设置借款金额
     *
     * @param loanAmount 借款金额
     */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * 获取借款利率
     *
     * @return rate - 借款利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置借款利率
     *
     * @param rate 借款利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取到账金额
     *
     * @return got_amount - 到账金额
     */
    public BigDecimal getGotAmount() {
        return gotAmount;
    }

    /**
     * 设置到账金额
     *
     * @param gotAmount 到账金额
     */
    public void setGotAmount(BigDecimal gotAmount) {
        this.gotAmount = gotAmount;
    }

    /**
     * 获取利息费
     *
     * @return interest_amount - 利息费
     */
    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    /**
     * 设置利息费
     *
     * @param interestAmount 利息费
     */
    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    /**
     * 获取服务费
     *
     * @return fee_amount - 服务费
     */
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * 设置服务费
     *
     * @param feeAmount 服务费
     */
    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * 获取逾期费
     *
     * @return overdue_amount - 逾期费
     */
    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }

    /**
     * 设置逾期费
     *
     * @param overdueAmount 逾期费
     */
    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    /**
     * 获取逾期天数
     *
     * @return overdue_limit - 逾期天数
     */
    public Integer getOverdueLimit() {
        return overdueLimit;
    }

    /**
     * 设置逾期天数
     *
     * @param overdueLimit 逾期天数
     */
    public void setOverdueLimit(Integer overdueLimit) {
        this.overdueLimit = overdueLimit;
    }

    /**
     * 获取待还总额
     *
     * @return wait_repay_amount - 待还总额
     */
    public BigDecimal getWaitRepayAmount() {
        return waitRepayAmount;
    }

    /**
     * 设置待还总额
     *
     * @param waitRepayAmount 待还总额
     */
    public void setWaitRepayAmount(BigDecimal waitRepayAmount) {
        this.waitRepayAmount = waitRepayAmount;
    }

    /**
     * 获取已还总额
     *
     * @return repaymented_amount - 已还总额
     */
    public BigDecimal getRepaymentedAmount() {
        return repaymentedAmount;
    }

    /**
     * 设置已还总额
     *
     * @param repaymentedAmount 已还总额
     */
    public void setRepaymentedAmount(BigDecimal repaymentedAmount) {
        this.repaymentedAmount = repaymentedAmount;
    }

    /**
     * 获取计划还款到期时间
     *
     * @return plan_repayment_time - 计划还款到期时间
     */
    public Date getPlanRepaymentTime() {
        return planRepaymentTime;
    }

    /**
     * 设置计划还款到期时间
     *
     * @param planRepaymentTime 计划还款到期时间
     */
    public void setPlanRepaymentTime(Date planRepaymentTime) {
        this.planRepaymentTime = planRepaymentTime;
    }

    /**
     * 获取还款完成时间
     *
     * @return repayment_time - 还款完成时间
     */
    public Date getRepaymentTime() {
        return repaymentTime;
    }

    /**
     * 设置还款完成时间
     *
     * @param repaymentTime 还款完成时间
     */
    public void setRepaymentTime(Date repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    /**
     * 获取订单状态 0待审核 1审核通过 2审核失败  3待还款 4放款失败 5逾期 6还款完成 
     *
     * @return order_status - 订单状态 0待审核 1审核通过 2审核失败  3待还款 4放款失败 5逾期 6还款完成 
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态 0待审核 1审核通过 2审核失败  3待还款 4放款失败 5逾期 6还款完成 
     *
     * @param orderStatus 订单状态 0待审核 1审核通过 2审核失败  3待还款 4放款失败 5逾期 6还款完成 
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取回款账号ID
     *
     * @return paycack_account_id - 回款账号ID
     */
    public String getPaycackAccountId() {
        return paycackAccountId;
    }

    /**
     * 设置回款账号ID
     *
     * @param paycackAccountId 回款账号ID
     */
    public void setPaycackAccountId(String paycackAccountId) {
        this.paycackAccountId = paycackAccountId;
    }

    /**
     * 获取国家地区编码
     *
     * @return area_code - 国家地区编码
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**
     * 设置国家地区编码
     *
     * @param areaCode 国家地区编码
     */
    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改时间
     *
     * @return update_time - 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRepayMethod() {
        return repayMethod;
    }

    public void setRepayMethod(Integer repayMethod) {
        this.repayMethod = repayMethod;
    }
}