package com.supermoney.loan.mg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_loan_grant")
public class SLoanGrant {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 放款业务编号
     */
    @Column(name = "grant_code")
    private String grantCode;

    /**
     * 订单业务编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 放款金额
     */
    @Column(name = "grant_amount")
    private BigDecimal grantAmount;
    /**
     * 还款金额
     */
    @Column(name = "repay_amount")
    private BigDecimal repayAmount;

    /**
     * 放款方式 0线上 1线下
     */
    @Column(name = "grant_method")
    private Integer grantMethod;

    /**
     * 还款方式:0服务费利息前置 1服务费前置利息后置
     */
    @Column(name = "repay_method")
    private Integer repayMethod;

    /**
     * 还款期数
     */
    @Column(name = "repay_limit")
    private Integer repayLimit;

    /**
     * 还款单位
     */
    @Column(name = "repay_unit")
    private Integer repayUnit;

    /**
     * 还款期数
     */
    private Integer  period;


    /**
     * 最后还款期数
     */
    @Column(name = "last_limit")
    private Integer lastLimit;

    /**
     * 产生逾期费用
     */
    @Column(name = "overdue_amount")
    private BigDecimal overdueAmount;

    /**
     * 逾期时长
     */
    @Column(name = "overdue_limit")
    private Integer overdueLimit;

    /**
     * 三方平台实际支出操作费用
     */
    @Column(name = "platform_amount")
    private BigDecimal platformAmount;

    /**
     * 使用抵消额度,还款使用奖励金额进行抵消还款金额.
     */
    @Column(name = "offset_amount")
    private BigDecimal offsetAmount;

    /**
     * 已还利息
     */
    @Column(name = "pay_interest")
    private BigDecimal payInterest;

    /**
     * 已还服务费
     */
    @Column(name = "pay_fee_amount")
    private BigDecimal payFeeAmount;

    /**
     * 已还本金
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 已还逾期费用
     */
    @Column(name = "pay_overdue_amount")
    private BigDecimal payOverdueAmount;

    /**
     * 回款状态 0回款中 1回款完成
     */
    @Column(name = "grant_status")
    private Integer grantStatus;

    /**
     * 还款到期时间
     */
    @Column(name = "repay_end_time")
    private Date repayEndTime;

    /**
     * 还款完成时间
     */
    @Column(name = "repayment_time")
    private Date repaymentTime;

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

    @Column(name = "grant_amount_usd")
    private BigDecimal grantAmountUsd;

    @Column(name = "overdue_amount_usd")
    private BigDecimal overdueAmountUsd;

    @Column(name = "offset_amount_usd")
    private BigDecimal offsetAmountUsd;

    @Column(name = "pay_overdue_amount_usd")
    private BigDecimal payOverdueAmountUsd;

    @Column(name = "repay_amount_usd")
    private BigDecimal repayAmountUsd;

    @Column(name = "pay_amount_usd")
    private BigDecimal payAmountUsd;

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
     * 获取放款业务编号
     *
     * @return grant_code - 放款业务编号
     */
    public String getGrantCode() {
        return grantCode;
    }

    /**
     * 设置放款业务编号
     *
     * @param grantCode 放款业务编号
     */
    public void setGrantCode(String grantCode) {
        this.grantCode = grantCode;
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
     * 获取放款金额
     *
     * @return grant_amount - 放款金额
     */
    public BigDecimal getGrantAmount() {
        return grantAmount;
    }

    /**
     * 设置放款金额
     *
     * @param grantAmount 放款金额
     */
    public void setGrantAmount(BigDecimal grantAmount) {
        this.grantAmount = grantAmount;
    }

    /**
     * 获取放款方式 0线上 1线下
     *
     * @return grant_method - 放款方式 0线上 1线下
     */
    public Integer getGrantMethod() {
        return grantMethod;
    }

    /**
     * 设置放款方式 0线上 1线下
     *
     * @param grantMethod 放款方式 0线上 1线下
     */
    public void setGrantMethod(Integer grantMethod) {
        this.grantMethod = grantMethod;
    }

    /**
     * 获取还款方式:0服务费利息前置 1服务费前置利息后置
     *
     * @return repay_method - 还款方式:0服务费利息前置 1服务费前置利息后置
     */
    public Integer getRepayMethod() {
        return repayMethod;
    }

    /**
     * 设置还款方式:0服务费利息前置 1服务费前置利息后置
     *
     * @param repayMethod 还款方式:0服务费利息前置 1服务费前置利息后置
     */
    public void setRepayMethod(Integer repayMethod) {
        this.repayMethod = repayMethod;
    }

    /**
     * 获取还款期数
     *
     * @return repay_limit - 还款期数
     */
    public Integer getRepayLimit() {
        return repayLimit;
    }

    /**
     * 设置还款期数
     *
     * @param repayLimit 还款期数
     */
    public void setRepayLimit(Integer repayLimit) {
        this.repayLimit = repayLimit;
    }

    /**
     * 获取还款单位
     *
     * @return repay_unit - 还款单位
     */
    public Integer getRepayUnit() {
        return repayUnit;
    }

    /**
     * 设置还款单位
     *
     * @param repayUnit 还款单位
     */
    public void setRepayUnit(Integer repayUnit) {
        this.repayUnit = repayUnit;
    }

    /**
     * 获取最后还款期数
     *
     * @return last_limit - 最后还款期数
     */
    public Integer getLastLimit() {
        return lastLimit;
    }

    /**
     * 设置最后还款期数
     *
     * @param lastLimit 最后还款期数
     */
    public void setLastLimit(Integer lastLimit) {
        this.lastLimit = lastLimit;
    }

    /**
     * 获取产生逾期费用
     *
     * @return overdue_amount - 产生逾期费用
     */
    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }

    /**
     * 设置产生逾期费用
     *
     * @param overdueAmount 产生逾期费用
     */
    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    /**
     * 获取逾期时长
     *
     * @return overdue_limit - 逾期时长
     */
    public Integer getOverdueLimit() {
        return overdueLimit;
    }

    /**
     * 设置逾期时长
     *
     * @param overdueLimit 逾期时长
     */
    public void setOverdueLimit(Integer overdueLimit) {
        this.overdueLimit = overdueLimit;
    }

    /**
     * 获取三方平台实际支出操作费用
     *
     * @return platform_amount - 三方平台实际支出操作费用
     */
    public BigDecimal getPlatformAmount() {
        return platformAmount;
    }

    /**
     * 设置三方平台实际支出操作费用
     *
     * @param platformAmount 三方平台实际支出操作费用
     */
    public void setPlatformAmount(BigDecimal platformAmount) {
        this.platformAmount = platformAmount;
    }

    /**
     * 获取使用抵消额度,还款使用奖励金额进行抵消还款金额.
     *
     * @return offset_amount - 使用抵消额度,还款使用奖励金额进行抵消还款金额.
     */
    public BigDecimal getOffsetAmount() {
        return offsetAmount;
    }

    /**
     * 设置使用抵消额度,还款使用奖励金额进行抵消还款金额.
     *
     * @param offsetAmount 使用抵消额度,还款使用奖励金额进行抵消还款金额.
     */
    public void setOffsetAmount(BigDecimal offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    /**
     * 获取已还利息
     *
     * @return pay_interest - 已还利息
     */
    public BigDecimal getPayInterest() {
        return payInterest;
    }

    /**
     * 设置已还利息
     *
     * @param payInterest 已还利息
     */
    public void setPayInterest(BigDecimal payInterest) {
        this.payInterest = payInterest;
    }

    /**
     * 获取已还服务费
     *
     * @return pay_fee_amount - 已还服务费
     */
    public BigDecimal getPayFeeAmount() {
        return payFeeAmount;
    }

    /**
     * 设置已还服务费
     *
     * @param payFeeAmount 已还服务费
     */
    public void setPayFeeAmount(BigDecimal payFeeAmount) {
        this.payFeeAmount = payFeeAmount;
    }

    /**
     * 获取已还本金
     *
     * @return pay_amount - 已还本金
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置已还本金
     *
     * @param payAmount 已还本金
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取已还逾期费用
     *
     * @return pay_overdue_amount - 已还逾期费用
     */
    public BigDecimal getPayOverdueAmount() {
        return payOverdueAmount;
    }

    /**
     * 设置已还逾期费用
     *
     * @param payOverdueAmount 已还逾期费用
     */
    public void setPayOverdueAmount(BigDecimal payOverdueAmount) {
        this.payOverdueAmount = payOverdueAmount;
    }

    /**
     * 获取回款状态 0回款中 1回款完成
     *
     * @return grant_status - 回款状态 0回款中 1回款完成
     */
    public Integer getGrantStatus() {
        return grantStatus;
    }

    /**
     * 设置回款状态 0回款中 1回款完成
     *
     * @param grantStatus 回款状态 0回款中 1回款完成
     */
    public void setGrantStatus(Integer grantStatus) {
        this.grantStatus = grantStatus;
    }

    /**
     * 获取还款到期时间
     *
     * @return repay_end_time - 还款到期时间
     */
    public Date getRepayEndTime() {
        return repayEndTime;
    }

    /**
     * 设置还款到期时间
     *
     * @param repayEndTime 还款到期时间
     */
    public void setRepayEndTime(Date repayEndTime) {
        this.repayEndTime = repayEndTime;
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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Date getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(Date repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public BigDecimal getGrantAmountUsd() {
        return grantAmountUsd;
    }

    public void setGrantAmountUsd(BigDecimal grantAmountUsd) {
        this.grantAmountUsd = grantAmountUsd;
    }

    public BigDecimal getOverdueAmountUsd() {
        return overdueAmountUsd;
    }

    public void setOverdueAmountUsd(BigDecimal overdueAmountUsd) {
        this.overdueAmountUsd = overdueAmountUsd;
    }

    public BigDecimal getOffsetAmountUsd() {
        return offsetAmountUsd;
    }

    public void setOffsetAmountUsd(BigDecimal offsetAmountUsd) {
        this.offsetAmountUsd = offsetAmountUsd;
    }

    public BigDecimal getPayOverdueAmountUsd() {
        return payOverdueAmountUsd;
    }

    public void setPayOverdueAmountUsd(BigDecimal payOverdueAmountUsd) {
        this.payOverdueAmountUsd = payOverdueAmountUsd;
    }

    public BigDecimal getRepayAmountUsd() {
        return repayAmountUsd;
    }

    public void setRepayAmountUsd(BigDecimal repayAmountUsd) {
        this.repayAmountUsd = repayAmountUsd;
    }

    public BigDecimal getPayAmountUsd() {
        return payAmountUsd;
    }

    public void setPayAmountUsd(BigDecimal payAmountUsd) {
        this.payAmountUsd = payAmountUsd;
    }
}