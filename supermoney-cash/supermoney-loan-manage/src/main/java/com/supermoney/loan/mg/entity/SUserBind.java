package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_user_bind")
public class SUserBind {
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
     * 卡类型：0储蓄卡、1信用卡
     */
    @Column(name = "card_type")
    private Integer cardType;

    /**
     * 所属国家
     */
    private String country;

    /**
     * 所属银行
     */
    @Column(name = "bank_id")
    private Integer bankId;

    /**
     * 银行卡号
     */
    @Column(name = "card_number")
    private String cardNumber;

    /**
     * 持卡人姓名
     */
    @Column(name = "holding_name")
    private String holdingName;

    /**
     * 持卡人身份证号码
     */
    @Column(name = "holding_card")
    private String holdingCard;

    /**
     * 持卡人预留电话号码
     */
    @Column(name = "holding_phone")
    private String holdingPhone;

    /**
     * 卡状态：0 绑定、 1 取消 2:绑定中
     */
    @Column(name = "card_status")
    private Integer cardStatus;

    /**
     * 绑定时间
     */
    @Column(name = "bind_time")
    private Date bindTime;

    /**
     * 取消绑定时间
     */
    @Column(name = "cancel_time")
    private Date cancelTime;

    /**
     * 银行账号
     */
    @Column(name = "card_account")
    private String cardAccount;

    /**
     * xendit id
     */
    @Column(name = "xendit_account_id")
    private String xenditAccountId;

    /**
     * xendit id
     */
    @Column(name = "xendit_reference")
    private String xenditReference;

    /**
     * 备注
     */
    private String remark;

    /**
     * 国家地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;

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
     * 获取卡类型：0储蓄卡、1信用卡
     *
     * @return card_type - 卡类型：0储蓄卡、1信用卡
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * 设置卡类型：0储蓄卡、1信用卡
     *
     * @param cardType 卡类型：0储蓄卡、1信用卡
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * 获取所属国家
     *
     * @return country - 所属国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置所属国家
     *
     * @param country 所属国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取所属银行
     *
     * @return bank_id - 所属银行
     */
    public Integer getBankId() {
        return bankId;
    }

    /**
     * 设置所属银行
     *
     * @param bankId 所属银行
     */
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * 获取银行卡号
     *
     * @return card_number - 银行卡号
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * 设置银行卡号
     *
     * @param cardNumber 银行卡号
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * 获取持卡人姓名
     *
     * @return holding_name - 持卡人姓名
     */
    public String getHoldingName() {
        return holdingName;
    }

    /**
     * 设置持卡人姓名
     *
     * @param holdingName 持卡人姓名
     */
    public void setHoldingName(String holdingName) {
        this.holdingName = holdingName;
    }

    /**
     * 获取持卡人身份证号码
     *
     * @return holding_card - 持卡人身份证号码
     */
    public String getHoldingCard() {
        return holdingCard;
    }

    /**
     * 设置持卡人身份证号码
     *
     * @param holdingCard 持卡人身份证号码
     */
    public void setHoldingCard(String holdingCard) {
        this.holdingCard = holdingCard;
    }

    /**
     * 获取持卡人预留电话号码
     *
     * @return holding_phone - 持卡人预留电话号码
     */
    public String getHoldingPhone() {
        return holdingPhone;
    }

    /**
     * 设置持卡人预留电话号码
     *
     * @param holdingPhone 持卡人预留电话号码
     */
    public void setHoldingPhone(String holdingPhone) {
        this.holdingPhone = holdingPhone;
    }

    /**
     * 获取卡状态：0 绑定、 1 取消 2:绑定中
     *
     * @return card_status - 卡状态：0 绑定、 1 取消 2:绑定中
     */
    public Integer getCardStatus() {
        return cardStatus;
    }

    /**
     * 设置卡状态：0 绑定、 1 取消 2:绑定中
     *
     * @param cardStatus 卡状态：0 绑定、 1 取消 2:绑定中
     */
    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    /**
     * 获取绑定时间
     *
     * @return bind_time - 绑定时间
     */
    public Date getBindTime() {
        return bindTime;
    }

    /**
     * 设置绑定时间
     *
     * @param bindTime 绑定时间
     */
    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    /**
     * 获取取消绑定时间
     *
     * @return cancel_time - 取消绑定时间
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 设置取消绑定时间
     *
     * @param cancelTime 取消绑定时间
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * 获取银行账号
     *
     * @return card_account - 银行账号
     */
    public String getCardAccount() {
        return cardAccount;
    }

    /**
     * 设置银行账号
     *
     * @param cardAccount 银行账号
     */
    public void setCardAccount(String cardAccount) {
        this.cardAccount = cardAccount;
    }

    /**
     * 获取xendit id
     *
     * @return xendit_account_id - xendit id
     */
    public String getXenditAccountId() {
        return xenditAccountId;
    }

    /**
     * 设置xendit id
     *
     * @param xenditAccountId xendit id
     */
    public void setXenditAccountId(String xenditAccountId) {
        this.xenditAccountId = xenditAccountId;
    }

    /**
     * 获取xendit id
     *
     * @return xendit_reference - xendit id
     */
    public String getXenditReference() {
        return xenditReference;
    }

    /**
     * 设置xendit id
     *
     * @param xenditReference xendit id
     */
    public void setXenditReference(String xenditReference) {
        this.xenditReference = xenditReference;
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
}