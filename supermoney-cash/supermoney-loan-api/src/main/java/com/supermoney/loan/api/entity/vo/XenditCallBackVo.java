package com.supermoney.loan.api.entity.vo;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tangwenchi on 2018/1/16.
 */
public class XenditCallBackVo implements Serializable {

    /**
     * 支出Id，对方订单号
     */
    private String id;

    /**
     * Xendit 商业Id
     */
    @Column(name = "user_id")
    private String user_id;

    /**
     * 创建支出设置的Id，订单Id
     */
    private String external_id;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 银行代码
     */
    private String bank_code;

    /**
     * 持卡人姓名
     */
    private String account_holder_name;

    /**
     * 提交支付的时候设置的描述
     */
    private String disbursement_description;

    /**
     * 成功/失败 状态
     * COMPLETED   支付成功
     * FAILED      由于失败代码，支付失败
     */
    private String status;

    /**
     * 可选参数
     * INSUFFICIENT_BALANCE : 账户余额不足
     * INVALID_DESTINATION：目标账户未注册
     * DIRECT_DISBURSEMENT_FAILED：银行拒绝支付未明确的原因
     */
    private String failure_code;


    /**
     * 支出Id
     */
    private String disbursement_id;


    /**
     * 交易订单号
     */
    private String transaction_id;

    /**
     * 交易序列号
     */
    private String transaction_sequence;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getDisbursement_description() {
        return disbursement_description;
    }

    public void setDisbursement_description(String disbursement_description) {
        this.disbursement_description = disbursement_description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailure_code() {
        return failure_code;
    }

    public void setFailure_code(String failure_code) {
        this.failure_code = failure_code;
    }

    public String getDisbursement_id() {
        return disbursement_id;
    }

    public void setDisbursement_id(String disbursement_id) {
        this.disbursement_id = disbursement_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_sequence() {
        return transaction_sequence;
    }

    public void setTransaction_sequence(String transaction_sequence) {
        this.transaction_sequence = transaction_sequence;
    }
}
