package com.supermoney.loan.api.entity;

import javax.persistence.*;

@Table(name = "s_xendit_virtual_account")
public class SXenditVirtualAccount {
    /**
     * Unique ID for the fixed virtual account. Can be used to create invoices linked to the FVA.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * Your user ID
     */
    @Column(name = "owner_id")
    private String ownerId;

    /**
     * An ID of your choice which you provided upon request
     */
    @Column(name = "external_id")
    private String externalId;

    /**
     * Bank code for the relevant bank, e.g. BNI
     */
    @Column(name = "bank_code")
    private String bankCode;

    /**
     * 5-digit merchant prefix to the full virtual account number
     */
    @Column(name = "merchant_code")
    private String merchantCode;

    /**
     * Name for the fixed virtual account
     */
    private String name;

    /**
     * Complete virtual account number (including prefix). This is what a user will need to enter into an ATM or their Internet/mobile banking.
     */
    @Column(name = "account_number")
    private String accountNumber;

    /**
     * value that determines whether a virtual account will be inactive after it is paid
     */
    @Column(name = "is_single_use")
    private String isSingleUse;

    /**
     * Status of fixed virtual account that defines if it’s pending or inactive. Status is inactive either because it is a paid single use fixed virtual account or it is already expired.
     */
    private String status;

    /**
     * 平台用户id
     */
    @Column(name = "user_id")
    private Integer userId;


    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 归属主
     */
    @Column(name = "xenditSecretKey")
    private String xenditsecretkey;

    /**
     * suggested amount for created fixed virtual account
     */
    @Column(name = "suggested_amount")
    private String suggestedAmount;

    /**
     * value that determines whether a virtual account is closed or not
     */
    @Column(name = "is_closed")
    private String isClosed;

    /**
     * OPTIONAL the amount that is expected when is_closed is true
     */
    @Column(name = "expected_amount")
    private String expectedAmount;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "udpate_time")
    private String udpateTime;

    private String opt;
    @Column(name = "account_type")
    private Integer accountType;

    /**
     * 获取Unique ID for the fixed virtual account. Can be used to create invoices linked to the FVA.
     *
     * @return id - Unique ID for the fixed virtual account. Can be used to create invoices linked to the FVA.
     */
    public String getId() {
        return id;
    }

    /**
     * 设置Unique ID for the fixed virtual account. Can be used to create invoices linked to the FVA.
     *
     * @param id Unique ID for the fixed virtual account. Can be used to create invoices linked to the FVA.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取Your user ID
     *
     * @return owner_id - Your user ID
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * 设置Your user ID
     *
     * @param ownerId Your user ID
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取An ID of your choice which you provided upon request
     *
     * @return external_id - An ID of your choice which you provided upon request
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * 设置An ID of your choice which you provided upon request
     *
     * @param externalId An ID of your choice which you provided upon request
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * 获取Bank code for the relevant bank, e.g. BNI
     *
     * @return bank_code - Bank code for the relevant bank, e.g. BNI
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 设置Bank code for the relevant bank, e.g. BNI
     *
     * @param bankCode Bank code for the relevant bank, e.g. BNI
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * 获取5-digit merchant prefix to the full virtual account number
     *
     * @return merchant_code - 5-digit merchant prefix to the full virtual account number
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * 设置5-digit merchant prefix to the full virtual account number
     *
     * @param merchantCode 5-digit merchant prefix to the full virtual account number
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    /**
     * 获取Name for the fixed virtual account
     *
     * @return name - Name for the fixed virtual account
     */
    public String getName() {
        return name;
    }

    /**
     * 设置Name for the fixed virtual account
     *
     * @param name Name for the fixed virtual account
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取Complete virtual account number (including prefix). This is what a user will need to enter into an ATM or their Internet/mobile banking.
     *
     * @return account_number - Complete virtual account number (including prefix). This is what a user will need to enter into an ATM or their Internet/mobile banking.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 设置Complete virtual account number (including prefix). This is what a user will need to enter into an ATM or their Internet/mobile banking.
     *
     * @param accountNumber Complete virtual account number (including prefix). This is what a user will need to enter into an ATM or their Internet/mobile banking.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 获取value that determines whether a virtual account will be inactive after it is paid
     *
     * @return is_single_use - value that determines whether a virtual account will be inactive after it is paid
     */
    public String getIsSingleUse() {
        return isSingleUse;
    }

    /**
     * 设置value that determines whether a virtual account will be inactive after it is paid
     *
     * @param isSingleUse value that determines whether a virtual account will be inactive after it is paid
     */
    public void setIsSingleUse(String isSingleUse) {
        this.isSingleUse = isSingleUse;
    }

    /**
     * 获取Status of fixed virtual account that defines if it’s pending or inactive. Status is inactive either because it is a paid single use fixed virtual account or it is already expired.
     *
     * @return status - Status of fixed virtual account that defines if it’s pending or inactive. Status is inactive either because it is a paid single use fixed virtual account or it is already expired.
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置Status of fixed virtual account that defines if it’s pending or inactive. Status is inactive either because it is a paid single use fixed virtual account or it is already expired.
     *
     * @param status Status of fixed virtual account that defines if it’s pending or inactive. Status is inactive either because it is a paid single use fixed virtual account or it is already expired.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取平台用户id
     *
     * @return user_id - 平台用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置平台用户id
     *
     * @param userId 平台用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取归属主
     *
     * @return xenditSecretKey - 归属主
     */
    public String getXenditsecretkey() {
        return xenditsecretkey;
    }

    /**
     * 设置归属主
     *
     * @param xenditsecretkey 归属主
     */
    public void setXenditsecretkey(String xenditsecretkey) {
        this.xenditsecretkey = xenditsecretkey;
    }

    /**
     * 获取suggested amount for created fixed virtual account
     *
     * @return suggested_amount - suggested amount for created fixed virtual account
     */
    public String getSuggestedAmount() {
        return suggestedAmount;
    }

    /**
     * 设置suggested amount for created fixed virtual account
     *
     * @param suggestedAmount suggested amount for created fixed virtual account
     */
    public void setSuggestedAmount(String suggestedAmount) {
        this.suggestedAmount = suggestedAmount;
    }

    /**
     * 获取value that determines whether a virtual account is closed or not
     *
     * @return is_closed - value that determines whether a virtual account is closed or not
     */
    public String getIsClosed() {
        return isClosed;
    }

    /**
     * 设置value that determines whether a virtual account is closed or not
     *
     * @param isClosed value that determines whether a virtual account is closed or not
     */
    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    /**
     * 获取OPTIONAL the amount that is expected when is_closed is true
     *
     * @return expected_amount - OPTIONAL the amount that is expected when is_closed is true
     */
    public String getExpectedAmount() {
        return expectedAmount;
    }

    /**
     * 设置OPTIONAL the amount that is expected when is_closed is true
     *
     * @param expectedAmount OPTIONAL the amount that is expected when is_closed is true
     */
    public void setExpectedAmount(String expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    /**
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return udpate_time
     */
    public String getUdpateTime() {
        return udpateTime;
    }

    /**
     * @param udpateTime
     */
    public void setUdpateTime(String udpateTime) {
        this.udpateTime = udpateTime;
    }

    /**
     * @return opt
     */
    public String getOpt() {
        return opt;
    }

    /**
     * @param opt
     */
    public void setOpt(String opt) {
        this.opt = opt;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAccountType() {
        return accountType;
    }


    
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}