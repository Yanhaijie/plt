package com.supermoney.loan.api.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tangwenchi on 2018/1/13.
 */
@Table(name = "s_user_bind")
public class SUserBind {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * user_id 用户编号
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
    @Column(name = "country")
    private String country;

    /**
     * 所属银行Id   bank_id
     */
    @Column(name = "bank_id")
    private Integer bankId;

    /**
     * 银行卡号
     */
    @Column(name = "card_number")
    private String cardNumber;
    /**
     * 银行账号
     */
    @Column(name = "card_account")
    private  String cardAccount;

    /** 持卡人姓名 **/
    @Column(name = "holding_name")
    private String holdingName;

    /** 持卡人身份证号码 **/
    @Column(name = "holding_card")
    private String holdingCard;

    /**持卡人预留银行电话号码**/
    @Column(name = "holding_phone")
    private String holdingPhone;


    /**
     * 卡状态：0 绑定、 1 取消
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
     * xendit账户ID
     */
    @Column(name = "xendit_account_id")
    private  String xenditAccountId;
    /**
     *
     */
    @Column(name = "xendit_reference")
    private  String xenditReference;
    /**
     * 备注
     */
    @Column(name = "remark")
    private  String remark;

    /**
     * 国家地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHoldingName() {
        return holdingName;
    }

    public void setHoldingName(String holdingName) {
        this.holdingName = holdingName;
    }

    public String getHoldingCard() {
        return holdingCard;
    }

    public void setHoldingCard(String holdingCard) {
        this.holdingCard = holdingCard;
    }

    public String getHoldingPhone() {
        return holdingPhone;
    }

    public void setHoldingPhone(String holdingPhone) {
        this.holdingPhone = holdingPhone;
    }

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(String cardAccount) {
        this.cardAccount = cardAccount;
    }

    public String getXenditAccountId() {
        return xenditAccountId;
    }

    public void setXenditAccountId(String xenditAccountId) {
        this.xenditAccountId = xenditAccountId;
    }

    public String getXenditReference() {
        return xenditReference;
    }

    public void setXenditReference(String xenditReference) {
        this.xenditReference = xenditReference;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }
}
