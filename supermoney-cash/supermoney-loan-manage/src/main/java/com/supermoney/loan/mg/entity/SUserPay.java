package com.supermoney.loan.mg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_user_pay")
public class SUserPay {
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
     * 充值来源编号
     */
    @Column(name = "pay_source_id")
    private Integer paySourceId;

    /**
     * 关联业务ID和编号
     */
    @Column(name = "related_id")
    private String relatedId;

    /**
     * 充值金额
     */
    @Column(name = "pay_money")
    private BigDecimal payMoney;

    /**
     * 国家地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;

    /**
     * 充值平台
     */
    @Column(name = "pay_platform")
    private String payPlatform;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态: 0 待审核  1 审核通过  2 审核失败 3 充值成功 4 充值失败 
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 充值类型：0现金充值
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 领取状态：0 否 1 是
     */
    @Column(name = "get_status")
    private Integer getStatus;

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
     * 获取充值来源编号
     *
     * @return pay_source_id - 充值来源编号
     */
    public Integer getPaySourceId() {
        return paySourceId;
    }

    /**
     * 设置充值来源编号
     *
     * @param paySourceId 充值来源编号
     */
    public void setPaySourceId(Integer paySourceId) {
        this.paySourceId = paySourceId;
    }

    /**
     * 获取充值金额
     *
     * @return pay_money - 充值金额
     */
    public BigDecimal getPayMoney() {
        return payMoney;
    }

    /**
     * 设置充值金额
     *
     * @param payMoney 充值金额
     */
    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
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
     * 获取充值平台
     *
     * @return pay_platform - 充值平台
     */
    public String getPayPlatform() {
        return payPlatform;
    }

    /**
     * 设置充值平台
     *
     * @param payPlatform 充值平台
     */
    public void setPayPlatform(String payPlatform) {
        this.payPlatform = payPlatform;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取状态: 0 待审核  1 审核通过  2 审核失败 3 充值成功 4 充值失败 
     *
     * @return pay_status - 状态: 0 待审核  1 审核通过  2 审核失败 3 充值成功 4 充值失败 
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置状态: 0 待审核  1 审核通过  2 审核失败 3 充值成功 4 充值失败 
     *
     * @param payStatus 状态: 0 待审核  1 审核通过  2 审核失败 3 充值成功 4 充值失败 
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取充值类型：0现金充值
     *
     * @return pay_type - 充值类型：0现金充值
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置充值类型：0现金充值
     *
     * @param payType 充值类型：0现金充值
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 获取领取状态：0 否 1 是
     *
     * @return get_status - 领取状态：0 否 1 是
     */
    public Integer getGetStatus() {
        return getStatus;
    }

    /**
     * 设置领取状态：0 否 1 是
     *
     * @param getStatus 领取状态：0 否 1 是
     */
    public void setGetStatus(Integer getStatus) {
        this.getStatus = getStatus;
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

    public String getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId;
    }
}