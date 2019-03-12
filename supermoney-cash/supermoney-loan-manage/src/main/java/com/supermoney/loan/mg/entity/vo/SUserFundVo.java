package com.supermoney.loan.mg.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2018-01-29.
 */
public class SUserFundVo implements Serializable {

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer userId;

    /**
     * 可用余额
     */
    @Column(name = "available_amount")
    private String availableAmount;

    /**
     * 冻结余额
     */
    @Column(name = "freeze_amount")
    private String freezeAmount;

    /**
     * 待还金额
     */
    @Column(name = "wait_amount")
    private String waitAmount;

    /**
     * 已还金额
     */
    @Column(name = "repaymented_amount")
    private String repaymentedAmount;

    /**
     * 已得奖励金额
     */
    @Column(name = "reward_amount")
    private String rewardAmount;

    /**
     * 已得任务金额
     */
    @Column(name = "work_amount")
    private String workAmount;

    /**
     * 已充值金额
     */
    @Column(name = "recharge_amount")
    private String rechargeAmount;

    /**
     * 已提现金额
     */
    @Column(name = "cash_amount")
    private String cashAmount;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(String freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getWaitAmount() {
        return waitAmount;
    }

    public void setWaitAmount(String waitAmount) {
        this.waitAmount = waitAmount;
    }

    public String getRepaymentedAmount() {
        return repaymentedAmount;
    }

    public void setRepaymentedAmount(String repaymentedAmount) {
        this.repaymentedAmount = repaymentedAmount;
    }

    public String getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(String rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getWorkAmount() {
        return workAmount;
    }

    public void setWorkAmount(String workAmount) {
        this.workAmount = workAmount;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
