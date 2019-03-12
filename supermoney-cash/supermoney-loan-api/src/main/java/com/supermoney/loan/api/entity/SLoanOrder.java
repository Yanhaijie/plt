package com.supermoney.loan.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_loan_order")
public class SLoanOrder {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;


    /**
     * 产品ID
     */
    @Column(name = "bounty_id")
    private Integer bountyId;

    /**
     * 还款期数
     */
    private Integer  period;

    /**
     * 订单业务编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 需要借款金额
     */
    @Column(name = "need_amount")
    private BigDecimal needAmount;

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
     * 借款利息
     */
    @Column(name = "loan_interest")
    private BigDecimal loanInterest;

    /**
     * 服务费
     */
    @Column(name = "fee_amount")
    private BigDecimal feeAmount;

    /**
     * 三方平台支出费用
     */
    @Column(name = "platform_amount")
    private BigDecimal platformAmount;
    /**
     * 所有产生费用
     */
    @Column(name = "all_amount")
    private BigDecimal allAmount;
    /**
     * 到账金额
     */
    @Column(name = "got_amount")
    private BigDecimal gotAmount;

    /**
     * 逾期时长
     */
    @Column(name = "overdue_limit")
    private Integer overdueLimit;


    /**
     * 逾期费用
     */
    @Column(name = "overdue_amount")
    private BigDecimal overdueAmount;

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
     * 借款期数
     */
    @Column(name = "loan_limit")
    private Integer loanLimit;

    /**
     * 借款单位
     */
    @Column(name = "loan_unit")
    private Integer loanUnit;

    /**
     * 逾期罚息利率
     */
    @Column(name = "overdue_rate")
    private BigDecimal overdueRate;

    /**
     * 还款方式:0服务费利息前置 1服务费前置利息后置
     */
    @Column(name = "repayment_method")
    private Integer repaymentMethod;

    /**
     * 已还期数
     */
    @Column(name = "repayment_limit")
    private Integer repaymentLimit;

    /**
     * 已还总额
     */
    @Column(name = "repayment_total")
    private BigDecimal repaymentTotal;

    /**
     * 订单审核通过时间
     */
    @Column(name = "order_audit_time")
    private Date orderAuditTime;

    /**
     * 放款审核通过时间
     */
    @Column(name = "loan_audit_time")
    private Date loanAuditTime;

    /**
     * 放款时间
     */
    @Column(name = "loan_time")
    private Date loanTime;

    /**
     * 起息时间
     */
    @Column(name = "interest_time")
    private Date interestTime;

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
     * 订单状态 0待审核 1审核通过 2审核失败 3放款审核通过 4放款审核失败 5放款成功 6放款失败 7待还款 8还款完成 9还款延期 10还款报损
     */
    @Column(name = "order_status")
    private Integer orderStatus;

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
     * 操作人
     */
    private String opt;
    @Column(name="virtual_account_id")
    private  String virtualAccountId;


    @Column(name="need_amount_usd")
    private BigDecimal needAmountUsd;

    @Column(name="loan_amount_usd")
    private BigDecimal loanAmountUsd;

    @Column(name="fee_amount_usd")
    private BigDecimal feeAmountUsd;

    @Column(name="overdue_amount_usd")
    private BigDecimal overdueAmountUsd;

    @Column(name="platform_amount_usd")
    private BigDecimal platformAmountUsd;

    @Column(name="repayment_total_usd")
    private BigDecimal repaymentTotalUsd;

    @Column(name="all_amount_usd")
    private BigDecimal allAmountUsd;

    @Column(name="got_amount_usd")
    private BigDecimal gotAmountUsd;

    @Column(name = "loan_interest_usd")
    private BigDecimal loanInterestUsd;

    @Column(name="overdue_virtual_account_id")
    private String overdueVirtualAccountId;
    /**
     * 已还逾期费用
     */
    @Column(name = "pay_overdue_amount")
    private BigDecimal payOverdueAmount;

    @Column(name = "pay_overdue_amount_usd")
    private BigDecimal payOverdueAmountUsd;

    @Column(name = "overdue_num")
    private  Integer overdueNum;

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
     * 获取需要借款金额
     *
     * @return need_amount - 需要借款金额
     */
    public BigDecimal getNeedAmount() {
        return needAmount;
    }

    /**
     * 设置需要借款金额
     *
     * @param needAmount 需要借款金额
     */
    public void setNeedAmount(BigDecimal needAmount) {
        this.needAmount = needAmount;
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
     * 获取借款利息
     *
     * @return loan_interest - 借款利息
     */
    public BigDecimal getLoanInterest() {
        return loanInterest;
    }

    /**
     * 设置借款利息
     *
     * @param loanInterest 借款利息
     */
    public void setLoanInterest(BigDecimal loanInterest) {
        this.loanInterest = loanInterest;
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
     * 获取三方平台支出费用
     *
     * @return platform_amount - 三方平台支出费用
     */
    public BigDecimal getPlatformAmount() {
        return platformAmount;
    }

    /**
     * 设置三方平台支出费用
     *
     * @param platformAmount 三方平台支出费用
     */
    public void setPlatformAmount(BigDecimal platformAmount) {
        this.platformAmount = platformAmount;
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
     * 获取借款期数
     *
     * @return loan_limit - 借款期数
     */
    public Integer getLoanLimit() {
        return loanLimit;
    }

    /**
     * 设置借款期数
     *
     * @param loanLimit 借款期数
     */
    public void setLoanLimit(Integer loanLimit) {
        this.loanLimit = loanLimit;
    }

    /**
     * 获取借款单位
     *
     * @return loan_unit - 借款单位
     */
    public Integer getLoanUnit() {
        return loanUnit;
    }

    /**
     * 设置借款单位
     *
     * @param loanUnit 借款单位
     */
    public void setLoanUnit(Integer loanUnit) {
        this.loanUnit = loanUnit;
    }

    /**
     * 获取逾期罚息利率
     *
     * @return overdue_rate - 逾期罚息利率
     */
    public BigDecimal getOverdueRate() {
        return overdueRate;
    }

    /**
     * 设置逾期罚息利率
     *
     * @param overdueRate 逾期罚息利率
     */
    public void setOverdueRate(BigDecimal overdueRate) {
        this.overdueRate = overdueRate;
    }

    /**
     * 获取还款方式:0服务费利息前置 1服务费前置利息后置
     *
     * @return repayment_method - 还款方式:0服务费利息前置 1服务费前置利息后置
     */
    public Integer getRepaymentMethod() {
        return repaymentMethod;
    }

    /**
     * 设置还款方式:0服务费利息前置 1服务费前置利息后置
     *
     * @param repaymentMethod 还款方式:0服务费利息前置 1服务费前置利息后置
     */
    public void setRepaymentMethod(Integer repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }

    /**
     * 获取已还期数
     *
     * @return repayment_limit - 已还期数
     */
    public Integer getRepaymentLimit() {
        return repaymentLimit;
    }

    /**
     * 设置已还期数
     *
     * @param repaymentLimit 已还期数
     */
    public void setRepaymentLimit(Integer repaymentLimit) {
        this.repaymentLimit = repaymentLimit;
    }

    /**
     * 获取已还总额
     *
     * @return repayment_total - 已还总额
     */
    public BigDecimal getRepaymentTotal() {
        return repaymentTotal;
    }

    /**
     * 设置已还总额
     *
     * @param repaymentTotal 已还总额
     */
    public void setRepaymentTotal(BigDecimal repaymentTotal) {
        this.repaymentTotal = repaymentTotal;
    }

    /**
     * 获取订单审核通过时间
     *
     * @return order_audit_time - 订单审核通过时间
     */
    public Date getOrderAuditTime() {
        return orderAuditTime;
    }

    /**
     * 设置订单审核通过时间
     *
     * @param orderAuditTime 订单审核通过时间
     */
    public void setOrderAuditTime(Date orderAuditTime) {
        this.orderAuditTime = orderAuditTime;
    }

    /**
     * 获取放款审核通过时间
     *
     * @return loan_audit_time - 放款审核通过时间
     */
    public Date getLoanAuditTime() {
        return loanAuditTime;
    }

    /**
     * 设置放款审核通过时间
     *
     * @param loanAuditTime 放款审核通过时间
     */
    public void setLoanAuditTime(Date loanAuditTime) {
        this.loanAuditTime = loanAuditTime;
    }

    /**
     * 获取放款时间
     *
     * @return loan_time - 放款时间
     */
    public Date getLoanTime() {
        return loanTime;
    }

    /**
     * 设置放款时间
     *
     * @param loanTime 放款时间
     */
    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    /**
     * 获取起息时间
     *
     * @return interest_time - 起息时间
     */
    public Date getInterestTime() {
        return interestTime;
    }

    /**
     * 设置起息时间
     *
     * @param interestTime 起息时间
     */
    public void setInterestTime(Date interestTime) {
        this.interestTime = interestTime;
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
     * 获取订单状态 0待审核 1审核通过 2审核失败 3放款审核通过 4放款审核失败 5放款成功 6放款失败 7待还款 8还款完成 9还款延期 10还款报损
     *
     * @return order_status - 订单状态 0待审核 1审核通过 2审核失败 3放款审核通过 4放款审核失败 5放款成功 6放款失败 7待还款 8还款完成 9还款延期 10还款报损
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态 0待审核 1审核通过 2审核失败 3放款审核通过 4放款审核失败 5放款成功 6放款失败 7待还款 8还款完成 9还款延期 10还款报损
     *
     * @param orderStatus 订单状态 0待审核 1审核通过 2审核失败 3放款审核通过 4放款审核失败 5放款成功 6放款失败 7待还款 8还款完成 9还款延期 10还款报损
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    /**
     * 获取操作人
     *
     * @return opt - 操作人
     */
    public String getOpt() {
        return opt;
    }

    /**
     * 设置操作人
     *
     * @param opt 操作人
     */
    public void setOpt(String opt) {
        this.opt = opt;
    }

    public Integer getBountyId() {
        return bountyId;
    }

    public void setBountyId(Integer bountyId) {
        this.bountyId = bountyId;
    }

    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(BigDecimal allAmount) {
        this.allAmount = allAmount;
    }

    public BigDecimal getGotAmount() {
        return gotAmount;
    }

    public void setGotAmount(BigDecimal gotAmount) {
        this.gotAmount = gotAmount;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getVirtualAccountId() {
        return virtualAccountId;
    }

    public void setVirtualAccountId(String virtualAccountId) {
        this.virtualAccountId = virtualAccountId;
    }


    public Integer getOverdueLimit() {
        return overdueLimit;
    }

    public void setOverdueLimit(Integer overdueLimit) {
        this.overdueLimit = overdueLimit;
    }

    public BigDecimal getNeedAmountUsd() {
        return needAmountUsd;
    }

    public void setNeedAmountUsd(BigDecimal needAmountUsd) {
        this.needAmountUsd = needAmountUsd;
    }

    public BigDecimal getLoanAmountUsd() {
        return loanAmountUsd;
    }

    public void setLoanAmountUsd(BigDecimal loanAmountUsd) {
        this.loanAmountUsd = loanAmountUsd;
    }

    public BigDecimal getFeeAmountUsd() {
        return feeAmountUsd;
    }

    public void setFeeAmountUsd(BigDecimal feeAmountUsd) {
        this.feeAmountUsd = feeAmountUsd;
    }

    public BigDecimal getOverdueAmountUsd() {
        return overdueAmountUsd;
    }

    public void setOverdueAmountUsd(BigDecimal overdueAmountUsd) {
        this.overdueAmountUsd = overdueAmountUsd;
    }

    public BigDecimal getPlatformAmountUsd() {
        return platformAmountUsd;
    }

    public void setPlatformAmountUsd(BigDecimal platformAmountUsd) {
        this.platformAmountUsd = platformAmountUsd;
    }

    public BigDecimal getRepaymentTotalUsd() {
        return repaymentTotalUsd;
    }

    public void setRepaymentTotalUsd(BigDecimal repaymentTotalUsd) {
        this.repaymentTotalUsd = repaymentTotalUsd;
    }

    public BigDecimal getAllAmountUsd() {
        return allAmountUsd;
    }

    public void setAllAmountUsd(BigDecimal allAmountUsd) {
        this.allAmountUsd = allAmountUsd;
    }

    public BigDecimal getGotAmountUsd() {
        return gotAmountUsd;
    }

    public void setGotAmountUsd(BigDecimal gotAmountUsd) {
        this.gotAmountUsd = gotAmountUsd;
    }

    public BigDecimal getLoanInterestUsd() {
        return loanInterestUsd;
    }

    public void setLoanInterestUsd(BigDecimal loanInterestUsd) {
        this.loanInterestUsd = loanInterestUsd;
    }

    public String getOverdueVirtualAccountId() {
        return overdueVirtualAccountId;
    }

    public void setOverdueVirtualAccountId(String overdueVirtualAccountId) {
        this.overdueVirtualAccountId = overdueVirtualAccountId;
    }

    public BigDecimal getPayOverdueAmount() {
        return payOverdueAmount;
    }

    public void setPayOverdueAmount(BigDecimal payOverdueAmount) {
        this.payOverdueAmount = payOverdueAmount;
    }

    public BigDecimal getPayOverdueAmountUsd() {
        return payOverdueAmountUsd;
    }

    public void setPayOverdueAmountUsd(BigDecimal payOverdueAmountUsd) {
        this.payOverdueAmountUsd = payOverdueAmountUsd;
    }

    public Integer getOverdueNum() {
        return overdueNum;
    }

    public void setOverdueNum(Integer overdueNum) {
        this.overdueNum = overdueNum;
    }
}