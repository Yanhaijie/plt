package com.supermoney.loan.api.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "s_xendit_virtual_account_callback")
public class SXenditVirtualAccountCallback {
    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * payment_id
     */
    @Column(name = "payment_id")
    private String paymentId;

    /**
     * owner_id
     */
    @Column(name = "owner_id")
    private String ownerId;


    @Column(name = "callback_virtual_account_id")
    private String callbackVirtualAccountId;

    @Column(name = "callback_virtual_account_payment_id")
    private String callbackVirtualAccountPaymentId;


    /**
     * external_id
     */
    @Column(name = "external_id")
    private String externalId;

    /**
     * account_number
     */
    @Column(name = "account_number")
    private String accountNumber;

    /**
     * bank_code
     */
    @Column(name = "bank_code")
    private String bankCode;

    /**
     * amount
     */
    private String amount;

    /**
     * transaction_timestamp
     */
    @Column(name = "transaction_timestamp")
    private Date transactionTimestamp;

    /**
     * merchant_code
     */
    @Column(name = "merchant_code")
    private String merchantCode;

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

    @Column(name = "xenditSecretKey")
    private String xenditsecretkey;

    /**
     * 获取Unique ID
     *
     * @return id - Unique ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置Unique ID
     *
     * @param id Unique ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取payment_id
     *
     * @return payment_id - payment_id
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * 设置payment_id
     *
     * @param paymentId payment_id
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * 获取owner_id
     *
     * @return owner_id - owner_id
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * 设置owner_id
     *
     * @param ownerId owner_id
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取external_id
     *
     * @return external_id - external_id
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * 设置external_id
     *
     * @param externalId external_id
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * 获取account_number
     *
     * @return account_number - account_number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 设置account_number
     *
     * @param accountNumber account_number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 获取bank_code
     *
     * @return bank_code - bank_code
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 设置bank_code
     *
     * @param bankCode bank_code
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * 获取amount
     *
     * @return amount - amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 设置amount
     *
     * @param amount amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * 获取transaction_timestamp
     *
     * @return transaction_timestamp - transaction_timestamp
     */
    public Date getTransactionTimestamp() {
        return transactionTimestamp;
    }

    /**
     * 设置transaction_timestamp
     *
     * @param transactionTimestamp transaction_timestamp
     */
    public void setTransactionTimestamp(Date transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    /**
     * 获取merchant_code
     *
     * @return merchant_code - merchant_code
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * 设置merchant_code
     *
     * @param merchantCode merchant_code
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
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
     * @return xenditSecretKey
     */
    public String getXenditsecretkey() {
        return xenditsecretkey;
    }

    /**
     * @param xenditsecretkey
     */
    public void setXenditsecretkey(String xenditsecretkey) {
        this.xenditsecretkey = xenditsecretkey;
    }

    public String getCallbackVirtualAccountId() {
        return callbackVirtualAccountId;
    }

    public void setCallbackVirtualAccountId(String callbackVirtualAccountId) {
        this.callbackVirtualAccountId = callbackVirtualAccountId;
    }

    public String getCallbackVirtualAccountPaymentId() {
        return callbackVirtualAccountPaymentId;
    }

    public void setCallbackVirtualAccountPaymentId(String callbackVirtualAccountPaymentId) {
        this.callbackVirtualAccountPaymentId = callbackVirtualAccountPaymentId;
    }
}