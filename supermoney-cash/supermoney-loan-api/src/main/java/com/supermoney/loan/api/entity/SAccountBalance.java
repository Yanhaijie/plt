package com.supermoney.loan.api.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tangwenchi on 2018/1/18.
 */
@Table(name = "s_account_balance")
public class SAccountBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "usd_amount")
    private BigDecimal usdAmount;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "cash_id")
    private int cashId;

    @Column(name = "buss_type")
    private Integer bussType;


    @Column(name = "account_id")
    private String accountId;

    @Column(name = "order_sn")
    private String orderSn;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "remark")
    private String remark;

    @Column(name = "status")
    private String status;

    @Column(name = "ext_id")
    private String extId;

    @Column(name = "failure_code")
    private String failureCode;

    @Column(name = "transaction_id")
    private String transactiond;

    @Column(name = "transaction_sequence")
    private String transactionSequence;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "card_number")
    private String cardNumber;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public String getTransactiond() {
        return transactiond;
    }

    public void setTransactiond(String transactiond) {
        this.transactiond = transactiond;
    }

    public String getTransactionSequence() {
        return transactionSequence;
    }

    public void setTransactionSequence(String transactionSequence) {
        this.transactionSequence = transactionSequence;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public BigDecimal getUsdAmount() {
        return usdAmount;
    }

    public void setUsdAmount(BigDecimal usdAmount) {
        this.usdAmount = usdAmount;
    }

    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
    }

    public Integer getBussType() {
        return bussType;
    }

    public void setBussType(Integer bussType) {
        this.bussType = bussType;
    }
}
