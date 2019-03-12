package com.supermoney.loan.mg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_user_cash")
public class SUserCash {
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
     * 用户提现银行卡ID
     */
    @Column(name = "user_bind_id")
    private Integer userBindId;

    /**
     * 提现金额
     */
    @Column(name = "cash_money")
    private BigDecimal cashMoney;

    /**
     * 国家地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;

    /**
     * 提现平台
     */
    @Column(name = "cash_platform")
    private String cashPlatform;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态: 0 待审核  1 审核通过  2 审核失败 3 提现成功 4 提现失败 
     */
    @Column(name = "cash_status")
    private Integer cashStatus;

    /**
     * 提现类型：0现金提现
     */
    @Column(name = "cash_type")
    private Integer cashType;

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
     * 获取用户提现银行卡ID
     *
     * @return user_bind_id - 用户提现银行卡ID
     */
    public Integer getUserBindId() {
        return userBindId;
    }

    /**
     * 设置用户提现银行卡ID
     *
     * @param userBindId 用户提现银行卡ID
     */
    public void setUserBindId(Integer userBindId) {
        this.userBindId = userBindId;
    }

    /**
     * 获取提现金额
     *
     * @return cash_money - 提现金额
     */
    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    /**
     * 设置提现金额
     *
     * @param cashMoney 提现金额
     */
    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
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
     * 获取提现平台
     *
     * @return cash_platform - 提现平台
     */
    public String getCashPlatform() {
        return cashPlatform;
    }

    /**
     * 设置提现平台
     *
     * @param cashPlatform 提现平台
     */
    public void setCashPlatform(String cashPlatform) {
        this.cashPlatform = cashPlatform;
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
     * 获取状态: 0 待审核  1 审核通过  2 审核失败 3 提现成功 4 提现失败 
     *
     * @return cash_status - 状态: 0 待审核  1 审核通过  2 审核失败 3 提现成功 4 提现失败 
     */
    public Integer getCashStatus() {
        return cashStatus;
    }

    /**
     * 设置状态: 0 待审核  1 审核通过  2 审核失败 3 提现成功 4 提现失败 
     *
     * @param cashStatus 状态: 0 待审核  1 审核通过  2 审核失败 3 提现成功 4 提现失败 
     */
    public void setCashStatus(Integer cashStatus) {
        this.cashStatus = cashStatus;
    }

    /**
     * 获取提现类型：0现金提现
     *
     * @return cash_type - 提现类型：0现金提现
     */
    public Integer getCashType() {
        return cashType;
    }

    /**
     * 设置提现类型：0现金提现
     *
     * @param cashType 提现类型：0现金提现
     */
    public void setCashType(Integer cashType) {
        this.cashType = cashType;
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