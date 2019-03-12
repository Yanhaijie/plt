package com.supermoney.loan.mg.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "s_bounty_loan")
public class SBountyLoan {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务编号
     */
    @Column(name = "bounty_Id")
    private Integer bountyId;
    /**
     * 借款期限
     */
    @Column(name="loan_limit")
    private  Integer loanLimit;

    /**
     * 借款利率
     */
    @Column(name = "loan_rate")
    private BigDecimal loanRate;

    /**
     * 借款利率单位 0月1天
     */
    @Column(name = "loan_rate_unit")
    private Integer loanRateUnit;

    /**
     * 固定手续费
     */
    @Column(name = "fee_moeny")
    private BigDecimal feeMoeny;

    /**
     * 手续费利率
     */
    @Column(name = "fee_rate")
    private BigDecimal feeRate;

    /**
     * 逾期罚息利率
     */
    @Column(name = "overdue_rate")
    private BigDecimal overdueRate;
    /**
     * 还款方式
     */
    @Column(name = "repayment_method")
    private  Integer repaymentMethod;

    /**
     * 手续费利率单位 0月1天
     */
    @Column(name = "fee_rate_unit")
    private Integer feeRateUnit;

    /**
     * 是否先扣除手续费:0否、1是
     */
    @Column(name = "is_deduct_fee")
    private Integer isDeductFee;

    /**
     * 是否先扣除利息:0否、1是
     */
    @Column(name = "is_deduct_interest")
    private Integer isDeductInterest;

    /**
     * 状态:0不启用、1启用
     */
    @Column(name = "bounty_loan_status")
    private Integer bountyLoanStatus;

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

    /**
     * 天手续费
     */
    @Column(name = "day_rate")
    private BigDecimal dayRate;


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
     * 获取任务编号
     *
     * @return bounty_Id - 任务编号
     */
    public Integer getBountyId() {
        return bountyId;
    }

    /**
     * 设置任务编号
     *
     * @param bountyId 任务编号
     */
    public void setBountyId(Integer bountyId) {
        this.bountyId = bountyId;
    }

    /**
     * 获取借款利率
     *
     * @return loan_rate - 借款利率
     */
    public BigDecimal getLoanRate() {
        return loanRate;
    }

    /**
     * 设置借款利率
     *
     * @param loanRate 借款利率
     */
    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    /**
     * 获取借款利率单位 0月1天
     *
     * @return loan_rate_unit - 借款利率单位 0月1天
     */
    public Integer getLoanRateUnit() {
        return loanRateUnit;
    }

    /**
     * 设置借款利率单位 0月1天
     *
     * @param loanRateUnit 借款利率单位 0月1天
     */
    public void setLoanRateUnit(Integer loanRateUnit) {
        this.loanRateUnit = loanRateUnit;
    }

    /**
     * 获取固定手续费
     *
     * @return fee_moeny - 固定手续费
     */
    public BigDecimal getFeeMoeny() {
        return feeMoeny;
    }

    /**
     * 设置固定手续费
     *
     * @param feeMoeny 固定手续费
     */
    public void setFeeMoeny(BigDecimal feeMoeny) {
        this.feeMoeny = feeMoeny;
    }

    /**
     * 获取手续费利率
     *
     * @return fee_rate - 手续费利率
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * 设置手续费利率
     *
     * @param feeRate 手续费利率
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    /**
     * 获取手续费利率单位 0月1天
     *
     * @return fee_rate_unit - 手续费利率单位 0月1天
     */
    public Integer getFeeRateUnit() {
        return feeRateUnit;
    }

    /**
     * 设置手续费利率单位 0月1天
     *
     * @param feeRateUnit 手续费利率单位 0月1天
     */
    public void setFeeRateUnit(Integer feeRateUnit) {
        this.feeRateUnit = feeRateUnit;
    }

    /**
     * 获取是否先扣除手续费:0否、1是
     *
     * @return is_deduct_fee - 是否先扣除手续费:0否、1是
     */
    public Integer getIsDeductFee() {
        return isDeductFee;
    }

    /**
     * 设置是否先扣除手续费:0否、1是
     *
     * @param isDeductFee 是否先扣除手续费:0否、1是
     */
    public void setIsDeductFee(Integer isDeductFee) {
        this.isDeductFee = isDeductFee;
    }

    /**
     * 获取是否先扣除利息:0否、1是
     *
     * @return is_deduct_interest - 是否先扣除利息:0否、1是
     */
    public Integer getIsDeductInterest() {
        return isDeductInterest;
    }

    /**
     * 设置是否先扣除利息:0否、1是
     *
     * @param isDeductInterest 是否先扣除利息:0否、1是
     */
    public void setIsDeductInterest(Integer isDeductInterest) {
        this.isDeductInterest = isDeductInterest;
    }

    /**
     * 获取状态:0不启用、1启用
     *
     * @return bounty_loan_status - 状态:0不启用、1启用
     */
    public Integer getBountyLoanStatus() {
        return bountyLoanStatus;
    }

    /**
     * 设置状态:0不启用、1启用
     *
     * @param bountyLoanStatus 状态:0不启用、1启用
     */
    public void setBountyLoanStatus(Integer bountyLoanStatus) {
        this.bountyLoanStatus = bountyLoanStatus;
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

    public Integer getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Integer loanLimit) {
        this.loanLimit = loanLimit;
    }

    public BigDecimal getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(BigDecimal overdueRate) {
        this.overdueRate = overdueRate;
    }

    public Integer getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(Integer repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }

    public BigDecimal getDayRate() {
        return dayRate;
    }

    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }
}