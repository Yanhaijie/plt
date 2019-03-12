package com.supermoney.loan.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "s_user_account_book")
public class SUserAccountBook {
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
     * 账户编号
     */
    @Column(name = "account_id")
    private String accountId;

    /**
     * 业务流程:0放款流程、1还款流程、2充值流程,3冲话费等
     */
    @Column(name = "buss_type")
    private Integer bussType;

    /**
     * 操作类型:放款到账、还款支出，3等
     */
    @Column(name = "opt_type")
    private Integer optType;
    /**
     * 相关业务ID
     */
    @Column(name = "buss_id")
    private Integer bussId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作金额
     */
    private BigDecimal amount;

    /**
     * 操作后金额
     */
    @Column(name = "after_amount")
    private BigDecimal afterAmount;

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
     * 获取账户编号
     *
     * @return account_id - 账户编号
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 设置账户编号
     *
     * @param accountId 账户编号
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取业务流程:0放款流程、1还款流程、2充值流程等
     *
     * @return buss_type - 业务流程:0放款流程、1还款流程、2充值流程等
     */
    public Integer getBussType() {
        return bussType;
    }

    /**
     * 设置业务流程:0放款流程、1还款流程、2充值流程等
     *
     * @param bussType 业务流程:0放款流程、1还款流程、2充值流程等
     */
    public void setBussType(Integer bussType) {
        this.bussType = bussType;
    }

    /**
     * 获取操作类型:放款到账、还款支出等
     *
     * @return opt_type - 操作类型:放款到账、还款支出等
     */
    public Integer getOptType() {
        return optType;
    }

    /**
     * 设置操作类型:放款到账、还款支出等
     *
     * @param optType 操作类型:放款到账、还款支出等
     */
    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    /**
     * 获取相关业务ID
     *
     * @return buss_id - 相关业务ID
     */
    public Integer getBussId() {
        return bussId;
    }

    /**
     * 设置相关业务ID
     *
     * @param bussId 相关业务ID
     */
    public void setBussId(Integer bussId) {
        this.bussId = bussId;
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
     * 获取操作金额
     *
     * @return amount - 操作金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置操作金额
     *
     * @param amount 操作金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取操作后金额
     *
     * @return after_amount - 操作后金额
     */
    public BigDecimal getAfterAmount() {
        return afterAmount;
    }

    /**
     * 设置操作后金额
     *
     * @param afterAmount 操作后金额
     */
    public void setAfterAmount(BigDecimal afterAmount) {
        this.afterAmount = afterAmount;
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