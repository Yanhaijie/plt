package com.supermoney.loan.mg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "s_voucher")
public class SVoucher {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 奖券名称
     */
    @Column(name = "voucher_name")
    private String voucherName;

    /**
     * 奖券介绍
     */
    @Column(name = "voucher_dsc")
    private String voucherDsc;

    /**
     * 奖券业务类型:0赏金、1贷款
     */
    @Column(name = "voucher_buss_type")
    private Integer voucherBussType;

    /**
     * 兑换类型:0金钱、1利率 2积分
     */
    @Column(name = "voucher_type")
    private Integer voucherType;

    /**
     * 兑换值
     */
    @Column(name = "voucher_val")
    private BigDecimal voucherVal;

    /**
     * 有效期-开始
     */
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 有效期-结束
     */
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 使用期-当日起几天后
     */
    @Column(name = "use_day")
    private Integer useDay;

    /**
     * 状态:0不可用 1可用
     */
    @Column(name = "voucher_status")
    private Integer voucherStatus;

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
    /**
     * 可用任务IDS
     */
    @Column(name="bounty_ids")
    private String bountyIds;

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
     * 获取奖券名称
     *
     * @return voucher_name - 奖券名称
     */
    public String getVoucherName() {
        return voucherName;
    }

    /**
     * 设置奖券名称
     *
     * @param voucherName 奖券名称
     */
    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    /**
     * 获取奖券介绍
     *
     * @return voucher_dsc - 奖券介绍
     */
    public String getVoucherDsc() {
        return voucherDsc;
    }

    /**
     * 设置奖券介绍
     *
     * @param voucherDsc 奖券介绍
     */
    public void setVoucherDsc(String voucherDsc) {
        this.voucherDsc = voucherDsc;
    }

    /**
     * 获取奖券业务类型:0赏金、1贷款
     *
     * @return voucher_buss_type - 奖券业务类型:0赏金、1贷款
     */
    public Integer getVoucherBussType() {
        return voucherBussType;
    }

    /**
     * 设置奖券业务类型:0赏金、1贷款
     *
     * @param voucherBussType 奖券业务类型:0赏金、1贷款
     */
    public void setVoucherBussType(Integer voucherBussType) {
        this.voucherBussType = voucherBussType;
    }

    /**
     * 获取兑换类型:0金钱、1利率 2积分
     *
     * @return voucher_type - 兑换类型:0金钱、1利率 2积分
     */
    public Integer getVoucherType() {
        return voucherType;
    }

    /**
     * 设置兑换类型:0金钱、1利率 2积分
     *
     * @param voucherType 兑换类型:0金钱、1利率 2积分
     */
    public void setVoucherType(Integer voucherType) {
        this.voucherType = voucherType;
    }

    /**
     * 获取兑换值
     *
     * @return voucher_val - 兑换值
     */
    public BigDecimal getVoucherVal() {
        return voucherVal;
    }

    /**
     * 设置兑换值
     *
     * @param voucherVal 兑换值
     */
    public void setVoucherVal(BigDecimal voucherVal) {
        this.voucherVal = voucherVal;
    }

    /**
     * 获取有效期-开始
     *
     * @return start_time - 有效期-开始
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置有效期-开始
     *
     * @param startTime 有效期-开始
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取有效期-结束
     *
     * @return end_time - 有效期-结束
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置有效期-结束
     *
     * @param endTime 有效期-结束
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取使用期-当日起几天后
     *
     * @return use_day - 使用期-当日起几天后
     */
    public Integer getUseDay() {
        return useDay;
    }

    /**
     * 设置使用期-当日起几天后
     *
     * @param useDay 使用期-当日起几天后
     */
    public void setUseDay(Integer useDay) {
        this.useDay = useDay;
    }

    /**
     * 获取状态:0不可用 1可用
     *
     * @return voucher_status - 状态:0不可用 1可用
     */
    public Integer getVoucherStatus() {
        return voucherStatus;
    }

    /**
     * 设置状态:0不可用 1可用
     *
     * @param voucherStatus 状态:0不可用 1可用
     */
    public void setVoucherStatus(Integer voucherStatus) {
        this.voucherStatus = voucherStatus;
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

    public String getBountyIds() {
        return bountyIds;
    }

    public void setBountyIds(String bountyIds) {
        this.bountyIds = bountyIds;
    }
}