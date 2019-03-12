package com.supermoney.loan.mg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_loan_damaged")
public class SLoanDamaged {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单业务编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 报损状态 8还款完成 10还款报损
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 已还款金额
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 已还款金额USD
     */
    @Column(name = "pay_amount_usd")
    private BigDecimal payAmountUsd;

    /**
     * 已还逾期费用
     */
    @Column(name = "pay_overdue_amount")
    private BigDecimal payOverdueAmount;

    /**
     * 已还逾期费用USD
     */
    @Column(name = "pay_overdue_amount_usd")
    private BigDecimal payOverdueAmountUsd;

    /**
     * 报损还款金额
     */
    @Column(name = "damaged_repay_amount")
    private BigDecimal damagedRepayAmount;

    /**
     * 报损还款金额USD
     */
    @Column(name = "damaged_repay_amount_usd")
    private BigDecimal damagedRepayAmountUsd;

    /**
     * 报损逾期金额
     */
    @Column(name = "damaged_overdue_amount")
    private BigDecimal damagedOverdueAmount;

    /**
     * 报损逾期金额USD
     */
    @Column(name = "damaged_overdue_amount_usd")
    private BigDecimal damagedOverdueAmountUsd;

    /**
     * 报损总额
     */
    @Column(name = "damaged_all_amount")
    private BigDecimal damagedAllAmount;

    /**
     * 报损总额USD
     */
    @Column(name = "damaged_all_amount_usd")
    private BigDecimal damagedAllAmountUsd;

    /**
     * 报损金额类型  0所有未还 1还部分本息 2已还本息逾期未还 3已还本息还部分逾期 4所有已还
     */
    @Column(name = "damaged_amount_type")
    private Integer damagedAmountType;

    /**
     * 报损类型  0还款报损 1业务测试 2系统问题
     */
    @Column(name = "damaged_type")
    private Integer damagedType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作人
     */
    private String opt;

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
     * 获取报损状态 8还款完成 10还款报损
     *
     * @return order_status - 报损状态 8还款完成 10还款报损
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置报损状态 8还款完成 10还款报损
     *
     * @param orderStatus 报损状态 8还款完成 10还款报损
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取已还款金额
     *
     * @return pay_amount - 已还款金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置已还款金额
     *
     * @param payAmount 已还款金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取已还款金额USD
     *
     * @return pay_amount_usd - 已还款金额USD
     */
    public BigDecimal getPayAmountUsd() {
        return payAmountUsd;
    }

    /**
     * 设置已还款金额USD
     *
     * @param payAmountUsd 已还款金额USD
     */
    public void setPayAmountUsd(BigDecimal payAmountUsd) {
        this.payAmountUsd = payAmountUsd;
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
     * 获取已还逾期费用USD
     *
     * @return pay_overdue_amount_usd - 已还逾期费用USD
     */
    public BigDecimal getPayOverdueAmountUsd() {
        return payOverdueAmountUsd;
    }

    /**
     * 设置已还逾期费用USD
     *
     * @param payOverdueAmountUsd 已还逾期费用USD
     */
    public void setPayOverdueAmountUsd(BigDecimal payOverdueAmountUsd) {
        this.payOverdueAmountUsd = payOverdueAmountUsd;
    }

    /**
     * 获取报损还款金额
     *
     * @return damaged_repay_amount - 报损还款金额
     */
    public BigDecimal getDamagedRepayAmount() {
        return damagedRepayAmount;
    }

    /**
     * 设置报损还款金额
     *
     * @param damagedRepayAmount 报损还款金额
     */
    public void setDamagedRepayAmount(BigDecimal damagedRepayAmount) {
        this.damagedRepayAmount = damagedRepayAmount;
    }

    /**
     * 获取报损还款金额USD
     *
     * @return damaged_repay_amount_usd - 报损还款金额USD
     */
    public BigDecimal getDamagedRepayAmountUsd() {
        return damagedRepayAmountUsd;
    }

    /**
     * 设置报损还款金额USD
     *
     * @param damagedRepayAmountUsd 报损还款金额USD
     */
    public void setDamagedRepayAmountUsd(BigDecimal damagedRepayAmountUsd) {
        this.damagedRepayAmountUsd = damagedRepayAmountUsd;
    }

    /**
     * 获取报损逾期金额
     *
     * @return damaged_overdue_amount - 报损逾期金额
     */
    public BigDecimal getDamagedOverdueAmount() {
        return damagedOverdueAmount;
    }

    /**
     * 设置报损逾期金额
     *
     * @param damagedOverdueAmount 报损逾期金额
     */
    public void setDamagedOverdueAmount(BigDecimal damagedOverdueAmount) {
        this.damagedOverdueAmount = damagedOverdueAmount;
    }

    /**
     * 获取报损逾期金额USD
     *
     * @return damaged_overdue_amount_usd - 报损逾期金额USD
     */
    public BigDecimal getDamagedOverdueAmountUsd() {
        return damagedOverdueAmountUsd;
    }

    /**
     * 设置报损逾期金额USD
     *
     * @param damagedOverdueAmountUsd 报损逾期金额USD
     */
    public void setDamagedOverdueAmountUsd(BigDecimal damagedOverdueAmountUsd) {
        this.damagedOverdueAmountUsd = damagedOverdueAmountUsd;
    }

    /**
     * 获取报损总额
     *
     * @return damaged_all_amount - 报损总额
     */
    public BigDecimal getDamagedAllAmount() {
        return damagedAllAmount;
    }

    /**
     * 设置报损总额
     *
     * @param damagedAllAmount 报损总额
     */
    public void setDamagedAllAmount(BigDecimal damagedAllAmount) {
        this.damagedAllAmount = damagedAllAmount;
    }

    /**
     * 获取报损总额USD
     *
     * @return damaged_all_amount_usd - 报损总额USD
     */
    public BigDecimal getDamagedAllAmountUsd() {
        return damagedAllAmountUsd;
    }

    /**
     * 设置报损总额USD
     *
     * @param damagedAllAmountUsd 报损总额USD
     */
    public void setDamagedAllAmountUsd(BigDecimal damagedAllAmountUsd) {
        this.damagedAllAmountUsd = damagedAllAmountUsd;
    }

    /**
     * 获取报损金额类型  0所有未还 1还部分本息 2已还本息逾期未还 3已还本息还部分逾期 4所有已还
     *
     * @return damaged_amount_type - 报损金额类型  0所有未还 1还部分本息 2已还本息逾期未还 3已还本息还部分逾期 4所有已还
     */
    public Integer getDamagedAmountType() {
        return damagedAmountType;
    }

    /**
     * 设置报损金额类型  0所有未还 1还部分本息 2已还本息逾期未还 3已还本息还部分逾期 4所有已还
     *
     * @param damagedAmountType 报损金额类型  0所有未还 1还部分本息 2已还本息逾期未还 3已还本息还部分逾期 4所有已还
     */
    public void setDamagedAmountType(Integer damagedAmountType) {
        this.damagedAmountType = damagedAmountType;
    }

    /**
     * 获取报损类型  0还款报损 1业务测试 2系统问题
     *
     * @return damaged_type - 报损类型  0还款报损 1业务测试 2系统问题
     */
    public Integer getDamagedType() {
        return damagedType;
    }

    /**
     * 设置报损类型  0还款报损 1业务测试 2系统问题
     *
     * @param damagedType 报损类型  0还款报损 1业务测试 2系统问题
     */
    public void setDamagedType(Integer damagedType) {
        this.damagedType = damagedType;
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
}