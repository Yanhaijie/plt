package com.supermoney.loan.market.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_merchant_user")
public class SMerchantUser {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商户ID-生成
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 商户名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 商户类型 0上游商户 1下游商户 
     */
    @Column(name = "merchant_type")
    private Integer merchantType;

    /**
     * 公司地址
     */
    @Column(name = "merchant_address")
    private String merchantAddress;

    /**
     * 邮件
     */
    @Column(name = "merchant_emial")
    private String merchantEmial;

    /**
     * 电话
     */
    @Column(name = "merchant_mobile")
    private String merchantMobile;

    /**
     * 商户状态 0可用 1不可用 
     */
    @Column(name = "merchant_status")
    private Integer merchantStatus;

    /**
     * 公钥
     */
    @Column(name = "public_key")
    private String publicKey;

    /**
     * 私钥
     */
    @Column(name = "private_key")
    private String privateKey;

    /**
     * 秘钥
     */
    @Column(name = "secret_key")
    private String secretKey;

    /**
     * 校验TOKEN
     */
    @Column(name = "validationo_token")
    private String validationoToken;

    /**
     * test公钥
     */
    @Column(name = "test_public_key")
    private String testPublicKey;

    /**
     * test私钥
     */
    @Column(name = "test_private_key")
    private String testPrivateKey;

    /**
     * test秘钥
     */
    @Column(name = "test_secret_key")
    private String testSecretKey;

    /**
     * test校验TOKEN
     */
    @Column(name = "test_validationo_token")
    private String testValidationoToken;

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
     * 获取商户ID-生成
     *
     * @return merchant_id - 商户ID-生成
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户ID-生成
     *
     * @param merchantId 商户ID-生成
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取商户名称
     *
     * @return merchant_name - 商户名称
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 设置商户名称
     *
     * @param merchantName 商户名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取商户类型 0上游商户 1下游商户 
     *
     * @return merchant_type - 商户类型 0上游商户 1下游商户 
     */
    public Integer getMerchantType() {
        return merchantType;
    }

    /**
     * 设置商户类型 0上游商户 1下游商户 
     *
     * @param merchantType 商户类型 0上游商户 1下游商户 
     */
    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    /**
     * 获取公司地址
     *
     * @return merchant_address - 公司地址
     */
    public String getMerchantAddress() {
        return merchantAddress;
    }

    /**
     * 设置公司地址
     *
     * @param merchantAddress 公司地址
     */
    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    /**
     * 获取邮件
     *
     * @return merchant_emial - 邮件
     */
    public String getMerchantEmial() {
        return merchantEmial;
    }

    /**
     * 设置邮件
     *
     * @param merchantEmial 邮件
     */
    public void setMerchantEmial(String merchantEmial) {
        this.merchantEmial = merchantEmial;
    }

    /**
     * 获取电话
     *
     * @return merchant_mobile - 电话
     */
    public String getMerchantMobile() {
        return merchantMobile;
    }

    /**
     * 设置电话
     *
     * @param merchantMobile 电话
     */
    public void setMerchantMobile(String merchantMobile) {
        this.merchantMobile = merchantMobile;
    }

    /**
     * 获取商户状态 0可用 1不可用 
     *
     * @return merchant_status - 商户状态 0可用 1不可用 
     */
    public Integer getMerchantStatus() {
        return merchantStatus;
    }

    /**
     * 设置商户状态 0可用 1不可用 
     *
     * @param merchantStatus 商户状态 0可用 1不可用 
     */
    public void setMerchantStatus(Integer merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    /**
     * 获取公钥
     *
     * @return public_key - 公钥
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * 设置公钥
     *
     * @param publicKey 公钥
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * 获取私钥
     *
     * @return private_key - 私钥
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * 设置私钥
     *
     * @param privateKey 私钥
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * 获取秘钥
     *
     * @return secret_key - 秘钥
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * 设置秘钥
     *
     * @param secretKey 秘钥
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * 获取校验TOKEN
     *
     * @return validationo_token - 校验TOKEN
     */
    public String getValidationoToken() {
        return validationoToken;
    }

    /**
     * 设置校验TOKEN
     *
     * @param validationoToken 校验TOKEN
     */
    public void setValidationoToken(String validationoToken) {
        this.validationoToken = validationoToken;
    }

    /**
     * 获取test公钥
     *
     * @return test_public_key - test公钥
     */
    public String getTestPublicKey() {
        return testPublicKey;
    }

    /**
     * 设置test公钥
     *
     * @param testPublicKey test公钥
     */
    public void setTestPublicKey(String testPublicKey) {
        this.testPublicKey = testPublicKey;
    }

    /**
     * 获取test私钥
     *
     * @return test_private_key - test私钥
     */
    public String getTestPrivateKey() {
        return testPrivateKey;
    }

    /**
     * 设置test私钥
     *
     * @param testPrivateKey test私钥
     */
    public void setTestPrivateKey(String testPrivateKey) {
        this.testPrivateKey = testPrivateKey;
    }

    /**
     * 获取test秘钥
     *
     * @return test_secret_key - test秘钥
     */
    public String getTestSecretKey() {
        return testSecretKey;
    }

    /**
     * 设置test秘钥
     *
     * @param testSecretKey test秘钥
     */
    public void setTestSecretKey(String testSecretKey) {
        this.testSecretKey = testSecretKey;
    }

    /**
     * 获取test校验TOKEN
     *
     * @return test_validationo_token - test校验TOKEN
     */
    public String getTestValidationoToken() {
        return testValidationoToken;
    }

    /**
     * 设置test校验TOKEN
     *
     * @param testValidationoToken test校验TOKEN
     */
    public void setTestValidationoToken(String testValidationoToken) {
        this.testValidationoToken = testValidationoToken;
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
}