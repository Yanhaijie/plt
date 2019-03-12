package com.supermoney.loan.api.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "s_transferto_topup_his")
public class STransfertoTopupHis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "destination_currency")
    private String destinationCurrency;

    private String transactionid;

    private String msisdn;

    @Column(name = "destination_msisdn")
    private String destinationMsisdn;

    private String country;

    private String countryid;

    private String operator;

    private String operatorid;

    @Column(name = "reference_operator")
    private String referenceOperator;

    @Column(name = "originating_currency")
    private String originatingCurrency;

    @Column(name = "product_requested")
    private String productRequested;

    @Column(name = "actual_product_sent")
    private String actualProductSent;

    @Column(name = "wholesale_price")
    private String wholesalePrice;

    @Column(name = "retail_price")
    private String retailPrice;

    private String balance;

    @Column(name = "sms_sent")
    private String smsSent;

    private String sms;

    private String cid1;

    private String cid2;

    private String cid3;

    @Column(name = "info_txt")
    private String infoTxt;

    @Column(name = "authentication_key")
    private String authenticationKey;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_txt")
    private String errorTxt;

    @Column(name = "response_body")
    private String responseBody;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "transaction_price")
    private Integer transactionPrice;

    @Column(name = "transaction_price_usd")
    private Integer transactionPriceUsd;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;



    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return destination_currency
     */
    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    /**
     * @param destinationCurrency
     */
    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    /**
     * @return transactionid
     */
    public String getTransactionid() {
        return transactionid;
    }

    /**
     * @param transactionid
     */
    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    /**
     * @return msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return destination_msisdn
     */
    public String getDestinationMsisdn() {
        return destinationMsisdn;
    }

    /**
     * @param destinationMsisdn
     */
    public void setDestinationMsisdn(String destinationMsisdn) {
        this.destinationMsisdn = destinationMsisdn;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return countryid
     */
    public String getCountryid() {
        return countryid;
    }

    /**
     * @param countryid
     */
    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return operatorid
     */
    public String getOperatorid() {
        return operatorid;
    }

    /**
     * @param operatorid
     */
    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }

    /**
     * @return reference_operator
     */
    public String getReferenceOperator() {
        return referenceOperator;
    }

    /**
     * @param referenceOperator
     */
    public void setReferenceOperator(String referenceOperator) {
        this.referenceOperator = referenceOperator;
    }

    /**
     * @return originating_currency
     */
    public String getOriginatingCurrency() {
        return originatingCurrency;
    }

    /**
     * @param originatingCurrency
     */
    public void setOriginatingCurrency(String originatingCurrency) {
        this.originatingCurrency = originatingCurrency;
    }

    /**
     * @return product_requested
     */
    public String getProductRequested() {
        return productRequested;
    }

    /**
     * @param productRequested
     */
    public void setProductRequested(String productRequested) {
        this.productRequested = productRequested;
    }

    /**
     * @return actual_product_sent
     */
    public String getActualProductSent() {
        return actualProductSent;
    }

    /**
     * @param actualProductSent
     */
    public void setActualProductSent(String actualProductSent) {
        this.actualProductSent = actualProductSent;
    }

    /**
     * @return wholesale_price
     */
    public String getWholesalePrice() {
        return wholesalePrice;
    }

    /**
     * @param wholesalePrice
     */
    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    /**
     * @return retail_price
     */
    public String getRetailPrice() {
        return retailPrice;
    }

    /**
     * @param retailPrice
     */
    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * @return balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }

    /**
     * @return sms_sent
     */
    public String getSmsSent() {
        return smsSent;
    }

    /**
     * @param smsSent
     */
    public void setSmsSent(String smsSent) {
        this.smsSent = smsSent;
    }

    /**
     * @return sms
     */
    public String getSms() {
        return sms;
    }

    /**
     * @param sms
     */
    public void setSms(String sms) {
        this.sms = sms;
    }

    /**
     * @return cid1
     */
    public String getCid1() {
        return cid1;
    }

    /**
     * @param cid1
     */
    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    /**
     * @return cid2
     */
    public String getCid2() {
        return cid2;
    }

    /**
     * @param cid2
     */
    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    /**
     * @return cid3
     */
    public String getCid3() {
        return cid3;
    }

    /**
     * @param cid3
     */
    public void setCid3(String cid3) {
        this.cid3 = cid3;
    }

    /**
     * @return info_txt
     */
    public String getInfoTxt() {
        return infoTxt;
    }

    /**
     * @param infoTxt
     */
    public void setInfoTxt(String infoTxt) {
        this.infoTxt = infoTxt;
    }

    /**
     * @return authentication_key
     */
    public String getAuthenticationKey() {
        return authenticationKey;
    }

    /**
     * @param authenticationKey
     */
    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    /**
     * @return error_code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return error_txt
     */
    public String getErrorTxt() {
        return errorTxt;
    }

    /**
     * @param errorTxt
     */
    public void setErrorTxt(String errorTxt) {
        this.errorTxt = errorTxt;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(Integer transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTransactionPriceUsd() {
        return transactionPriceUsd;
    }

    public void setTransactionPriceUsd(Integer transactionPriceUsd) {
        this.transactionPriceUsd = transactionPriceUsd;
    }
}