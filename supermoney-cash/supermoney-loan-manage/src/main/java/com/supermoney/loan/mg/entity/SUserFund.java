package com.supermoney.loan.mg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tangwenchi on 2018/1/13.
 */
@Table(name = "s_user_fund")
public class SUserFund {

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
     * 可用余额
     */
    @Column(name = "available_amount")
    private BigDecimal availableAmount;

    /**
     * 冻结余额
     */
    @Column(name = "freeze_amount")
    private BigDecimal freezeAmount;

    /**
     * 待还金额
     */
    @Column(name = "wait_amount")
    private BigDecimal waitAmount;

    /**
     * 已还金额
     */
    @Column(name = "repaymented_amount")
    private BigDecimal repaymentedAmount;

    /**
     * 已得奖励金额
     */
    @Column(name = "reward_amount")
    private BigDecimal rewardAmount;

    /**
     * 已得任务金额
     */
    @Column(name = "work_amount")
    private BigDecimal workAmount;

    /**
     * 已充值金额
     */
    @Column(name = "recharge_amount")
    private BigDecimal rechargeAmount;

    /**
     * 已提现金额
     */
    @Column(name = "cash_amount")
    private BigDecimal cashAmount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 操作人
     */
    private String opt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getWaitAmount() {
        return waitAmount;
    }

    public void setWaitAmount(BigDecimal waitAmount) {
        this.waitAmount = waitAmount;
    }

    public BigDecimal getRepaymentedAmount() {
        return repaymentedAmount;
    }

    public void setRepaymentedAmount(BigDecimal repaymentedAmount) {
        this.repaymentedAmount = repaymentedAmount;
    }

    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(BigDecimal rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public BigDecimal getWorkAmount() {
        return workAmount;
    }

    public void setWorkAmount(BigDecimal workAmount) {
        this.workAmount = workAmount;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
