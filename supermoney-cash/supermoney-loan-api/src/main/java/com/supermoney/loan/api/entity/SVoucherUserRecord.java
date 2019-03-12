package com.supermoney.loan.api.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "s_voucher_user_record")
public class SVoucherUserRecord {
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
     * 奖券编号
     */
    @Column(name = "voucher_id")
    private Integer voucherId;

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
     * 有效期-开始
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 有效期-结束
     */
    @Column(name = "end_time")
    private Date endTime;

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
     * 使用值
     */
    @Column(name = "use_val")
    private BigDecimal useVal;

    /**
     * 使用相关业务ID
     */
    @Column(name = "use_id")
    private Integer useId;

    /**
     * 状态:0未使用 1使用中 2使用完
     */
    @Column(name = "record_status")
    private Integer recordStatus;

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
     * 可用任务IDS
     */
    @Column(name="bounty_ids")
    private String bountyIds;
    /**
     * 是否已查看
     */
    private  Integer viewed;

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
     * 获取奖券编号
     *
     * @return voucher_id - 奖券编号
     */
    public Integer getVoucherId() {
        return voucherId;
    }

    /**
     * 设置奖券编号
     *
     * @param voucherId 奖券编号
     */
    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
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
     * 获取使用值
     *
     * @return use_val - 使用值
     */
    public BigDecimal getUseVal() {
        return useVal;
    }

    /**
     * 设置使用值
     *
     * @param useVal 使用值
     */
    public void setUseVal(BigDecimal useVal) {
        this.useVal = useVal;
    }

    /**
     * 获取使用相关业务ID
     *
     * @return use_id - 使用相关业务ID
     */
    public Integer getUseId() {
        return useId;
    }

    /**
     * 设置使用相关业务ID
     *
     * @param useId 使用相关业务ID
     */
    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    /**
     * 获取状态:0未使用 1使用中 2使用完
     *
     * @return record_status - 状态:0未使用 1使用中 2使用完
     */
    public Integer getRecordStatus() {
        return recordStatus;
    }

    /**
     * 设置状态:0未使用 1使用中 2使用完
     *
     * @param recordStatus 状态:0未使用 1使用中 2使用完
     */
    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
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

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }
}