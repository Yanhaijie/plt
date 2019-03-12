package com.supers.p2p.assets.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_item_info")
public class SItemInfo {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目标题
     */
    @Column(name = "item_title")
    private String itemTitle;

    /**
     * 借款金额
     */
    @Column(name = "borrow_amount")
    private Integer borrowAmount;

    /**
     * 金额单位，0人民币（元）
     */
    @Column(name = "amount_unit")
    private String amountUnit;

    /**
     * 是否拆分，0否，1是
     */
    @Column(name = "is_split")
    private Integer isSplit;

    /**
     * 拆分信息
     */
    @Column(name = "split_info")
    private String splitInfo;

    /**
     * 借款期限(单位月)  1 0~3个月|2  0~12个月|3 0~24个月|4 24个月以上
     */
    @Column(name = "borrow_deadline")
    private Integer borrowDeadline;

    /**
     * 还款方式 1 到期还本付息，2 等额本息,3 先息后本
     */
    @Column(name = "repay_way")
    private Integer repayWay;

    /**
     * 借款金额
     */
    @Column(name = "borrow_rate")
    private BigDecimal borrowRate;

    /**
     * 资产类型（资产信息）
     */
    @Column(name = "asset_info")
    private String assetInfo;

    /**
     * 借款人类型,0个人，1企业
     */
    @Column(name = "borrower_type")
    private Integer borrowerType;

    /**
     * 借款人信息编号
     */
    @Column(name = "borrower_info_id")
    private Integer borrowerInfoId;

    /**
     * 借款人审信材料编号
     */
    @Column(name = "borrower_credit_id")
    private Integer borrowerCreditId;

    /**
     * 借款人附加材料
     */
    @Column(name = "attach_info")
    private Integer attachInfo;

    /**
     * 资产用户编号
     */
    @Column(name = "asset_user_id")
    private Integer assetUserId;

    /**
     * 用户所属公司
     */
    @Column(name = "found_user_id")
    private Integer foundUserId;

    /**
     * 使用状态，0 草稿 1 待审核 2审核不通过，3审核不通过， 4发标中，5满标
     */
    @Column(name = "use_status")
    private Integer useStatus;

    @Column(name = "lock_time")
    private Date lockTime;

    /**
     * 单位秒
     */
    @Column(name = "lock_duration")
    private Integer lockDuration;

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
     * 获取项目标题
     *
     * @return item_title - 项目标题
     */
    public String getItemTitle() {
        return itemTitle;
    }

    /**
     * 设置项目标题
     *
     * @param itemTitle 项目标题
     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    /**
     * 获取借款金额
     *
     * @return borrow_amount - 借款金额
     */
    public Integer getBorrowAmount() {
        return borrowAmount;
    }

    /**
     * 设置借款金额
     *
     * @param borrowAmount 借款金额
     */
    public void setBorrowAmount(Integer borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    /**
     * 获取金额单位，0人民币（元）
     *
     * @return amount_unit - 金额单位，0人民币（元）
     */
    public String getAmountUnit() {
        return amountUnit;
    }

    /**
     * 设置金额单位，0人民币（元）
     *
     * @param amountUnit 金额单位，0人民币（元）
     */
    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    /**
     * 获取是否拆分，0否，1是
     *
     * @return is_split - 是否拆分，0否，1是
     */
    public Integer getIsSplit() {
        return isSplit;
    }

    /**
     * 设置是否拆分，0否，1是
     *
     * @param isSplit 是否拆分，0否，1是
     */
    public void setIsSplit(Integer isSplit) {
        this.isSplit = isSplit;
    }

    /**
     * 获取拆分信息
     *
     * @return split_info - 拆分信息
     */
    public String getSplitInfo() {
        return splitInfo;
    }

    /**
     * 设置拆分信息
     *
     * @param splitInfo 拆分信息
     */
    public void setSplitInfo(String splitInfo) {
        this.splitInfo = splitInfo;
    }

    /**
     * 获取借款期限(单位月)  1 0~3个月|2  0~12个月|3 0~24个月|4 24个月以上
     *
     * @return borrow_deadline - 借款期限(单位月)  1 0~3个月|2  0~12个月|3 0~24个月|4 24个月以上
     */
    public Integer getBorrowDeadline() {
        return borrowDeadline;
    }

    /**
     * 设置借款期限(单位月)  1 0~3个月|2  0~12个月|3 0~24个月|4 24个月以上
     *
     * @param borrowDeadline 借款期限(单位月)  1 0~3个月|2  0~12个月|3 0~24个月|4 24个月以上
     */
    public void setBorrowDeadline(Integer borrowDeadline) {
        this.borrowDeadline = borrowDeadline;
    }

    /**
     * 获取还款方式 1 到期还本付息，2 等额本息,3 先息后本
     *
     * @return repay_way - 还款方式 1 到期还本付息，2 等额本息,3 先息后本
     */
    public Integer getRepayWay() {
        return repayWay;
    }

    /**
     * 设置还款方式 1 到期还本付息，2 等额本息,3 先息后本
     *
     * @param repayWay 还款方式 1 到期还本付息，2 等额本息,3 先息后本
     */
    public void setRepayWay(Integer repayWay) {
        this.repayWay = repayWay;
    }

    /**
     * 获取借款金额
     *
     * @return borrow_rate - 借款金额
     */
    public BigDecimal getBorrowRate() {
        return borrowRate;
    }

    /**
     * 设置借款金额
     *
     * @param borrowRate 借款金额
     */
    public void setBorrowRate(BigDecimal borrowRate) {
        this.borrowRate = borrowRate;
    }

    /**
     * 获取资产类型（资产信息）
     *
     * @return asset_info - 资产类型（资产信息）
     */
    public String getAssetInfo() {
        return assetInfo;
    }

    /**
     * 设置资产类型（资产信息）
     *
     * @param assetInfo 资产类型（资产信息）
     */
    public void setAssetInfo(String assetInfo) {
        this.assetInfo = assetInfo;
    }

    /**
     * 获取借款人类型,0个人，1企业
     *
     * @return borrower_type - 借款人类型,0个人，1企业
     */
    public Integer getBorrowerType() {
        return borrowerType;
    }

    /**
     * 设置借款人类型,0个人，1企业
     *
     * @param borrowerType 借款人类型,0个人，1企业
     */
    public void setBorrowerType(Integer borrowerType) {
        this.borrowerType = borrowerType;
    }

    /**
     * 获取借款人信息编号
     *
     * @return borrower_info_id - 借款人信息编号
     */
    public Integer getBorrowerInfoId() {
        return borrowerInfoId;
    }

    /**
     * 设置借款人信息编号
     *
     * @param borrowerInfoId 借款人信息编号
     */
    public void setBorrowerInfoId(Integer borrowerInfoId) {
        this.borrowerInfoId = borrowerInfoId;
    }

    /**
     * 获取借款人审信材料编号
     *
     * @return borrower_credit_id - 借款人审信材料编号
     */
    public Integer getBorrowerCreditId() {
        return borrowerCreditId;
    }

    /**
     * 设置借款人审信材料编号
     *
     * @param borrowerCreditId 借款人审信材料编号
     */
    public void setBorrowerCreditId(Integer borrowerCreditId) {
        this.borrowerCreditId = borrowerCreditId;
    }

    /**
     * 获取借款人附加材料
     *
     * @return attach_info - 借款人附加材料
     */
    public Integer getAttachInfo() {
        return attachInfo;
    }

    /**
     * 设置借款人附加材料
     *
     * @param attachInfo 借款人附加材料
     */
    public void setAttachInfo(Integer attachInfo) {
        this.attachInfo = attachInfo;
    }

    /**
     * 获取资产用户编号
     *
     * @return asset_user_id - 资产用户编号
     */
    public Integer getAssetUserId() {
        return assetUserId;
    }

    /**
     * 设置资产用户编号
     *
     * @param assetUserId 资产用户编号
     */
    public void setAssetUserId(Integer assetUserId) {
        this.assetUserId = assetUserId;
    }

    /**
     * 获取用户所属公司
     *
     * @return found_user_id - 用户所属公司
     */
    public Integer getFoundUserId() {
        return foundUserId;
    }

    /**
     * 设置用户所属公司
     *
     * @param foundUserId 用户所属公司
     */
    public void setFoundUserId(Integer foundUserId) {
        this.foundUserId = foundUserId;
    }

    /**
     * 获取使用状态，0 草稿 1 待审核 2审核不通过，3审核不通过， 4发标中，5满标
     *
     * @return use_status - 使用状态，0 草稿 1 待审核 2审核不通过，3审核不通过， 4发标中，5满标
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**
     * 设置使用状态，0 草稿 1 待审核 2审核不通过，3审核不通过， 4发标中，5满标
     *
     * @param useStatus 使用状态，0 草稿 1 待审核 2审核不通过，3审核不通过， 4发标中，5满标
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * @return lock_time
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * @param lockTime
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 获取单位秒
     *
     * @return lock_duration - 单位秒
     */
    public Integer getLockDuration() {
        return lockDuration;
    }

    /**
     * 设置单位秒
     *
     * @param lockDuration 单位秒
     */
    public void setLockDuration(Integer lockDuration) {
        this.lockDuration = lockDuration;
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
}