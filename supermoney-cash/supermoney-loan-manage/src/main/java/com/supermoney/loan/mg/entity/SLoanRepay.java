package com.supermoney.loan.mg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_loan_repay")
public class SLoanRepay {
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
     * 还款业务编号
     */
    @Column(name = "repay_code")
    private String repayCode;

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
     * 还款方式 0线上 1线下
     */
    @Column(name = "repay_method")
    private Integer repayMethod;

    /**
     * 还款金额
     */
    @Column(name = "repay_amount")
    private BigDecimal repayAmount;

    /**
     * 还款期数
     */
    private Integer period;

    /**
     * 当前期数
     */
    @Column(name = "cur_period")
    private Integer curPeriod;

    /**
     * 已还金额
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 还款到期时间
     */
    @Column(name = "repay_end_time")
    private Date repayEndTime;

    /**
     * 实际还款时间
     */
    @Column(name = "repay_time")
    private Date repayTime;

    /**
     * 逾期时长
     */
    @Column(name = "overdue_limit")
    private Integer overdueLimit;

    /**
     * 产生逾期费用
     */
    @Column(name = "overdue_amount")
    private BigDecimal overdueAmount;

    /**
     * 已还逾期费用
     */
    @Column(name = "pay_overdue_amount")
    private BigDecimal payOverdueAmount;

    /**
     * 还款状态 0创建 1待还款 2还款完成 3还款失败
     */
    @Column(name = "repay_status")
    private Integer repayStatus;

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

    @Column(name = "repay_amount_usd")
    private BigDecimal repayAmountUsd;

    @Column(name = "pay_amount_usd")
    private BigDecimal payAmountUsd;

    @Column(name = "overdue_amount_usd")
    private BigDecimal overdueAmountUsd;

    @Column(name = "pay_overdue_amount_usd")
    private BigDecimal payOverdueAmountUsd;




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
     * 获取还款业务编号
     *
     * @return repay_code - 还款业务编号
     */
    public String getRepayCode() {
        return repayCode;
    }

    /**
     * 设置还款业务编号
     *
     * @param repayCode 还款业务编号
     */
    public void setRepayCode(String repayCode) {
        this.repayCode = repayCode;
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
     * 获取还款方式 0线上 1线下
     *
     * @return repay_method - 还款方式 0线上 1线下
     */
    public Integer getRepayMethod() {
        return repayMethod;
    }

    /**
     * 设置还款方式 0线上 1线下
     *
     * @param repayMethod 还款方式 0线上 1线下
     */
    public void setRepayMethod(Integer repayMethod) {
        this.repayMethod = repayMethod;
    }

    /**
     * 获取还款金额
     *
     * @return repay_amount - 还款金额
     */
    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    /**
     * 设置还款金额
     *
     * @param repayAmount 还款金额
     */
    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    /**
     * 获取还款期数
     *
     * @return period - 还款期数
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置还款期数
     *
     * @param period 还款期数
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取当前期数
     *
     * @return cur_period - 当前期数
     */
    public Integer getCurPeriod() {
        return curPeriod;
    }

    /**
     * 设置当前期数
     *
     * @param curPeriod 当前期数
     */
    public void setCurPeriod(Integer curPeriod) {
        this.curPeriod = curPeriod;
    }

    /**
     * 获取已还金额
     *
     * @return pay_amount - 已还金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置已还金额
     *
     * @param payAmount 已还金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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
     * 获取实际还款时间
     *
     * @return repay_time - 实际还款时间
     */
    public Date getRepayTime() {
        return repayTime;
    }

    /**
     * 设置实际还款时间
     *
     * @param repayTime 实际还款时间
     */
    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
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
     * 获取还款状态 0创建 1待还款 2还款完成 3还款失败
     *
     * @return repay_status - 还款状态 0创建 1待还款 2还款完成 3还款失败
     */
    public Integer getRepayStatus() {
        return repayStatus;
    }

    /**
     * 设置还款状态 0创建 1待还款 2还款完成 3还款失败
     *
     * @param repayStatus 还款状态 0创建 1待还款 2还款完成 3还款失败
     */
    public void setRepayStatus(Integer repayStatus) {
        this.repayStatus = repayStatus;
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

    public BigDecimal getOverdueAmountUsd() {
        return overdueAmountUsd;
    }

    public void setOverdueAmountUsd(BigDecimal overdueAmountUsd) {
        this.overdueAmountUsd = overdueAmountUsd;
    }

    public BigDecimal getPayOverdueAmountUsd() {
        return payOverdueAmountUsd;
    }

    public void setPayOverdueAmountUsd(BigDecimal payOverdueAmountUsd) {
        this.payOverdueAmountUsd = payOverdueAmountUsd;
    }
}