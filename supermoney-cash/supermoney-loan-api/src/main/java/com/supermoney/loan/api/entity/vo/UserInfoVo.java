package com.supermoney.loan.api.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bear on 2018/1/13.
 */
public class UserInfoVo  implements Serializable {

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户级别:0普通、1会员、2钻石会员
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer userLevel;
    /**
     * 头像
     */
    private String pic;
    /**
     * 信用分数
     */
    @Column(name = "credit_score")
    private String creditScore;
    /**
     * 信用等级（0 F (默认),1 E, 2 D , 3 C , 4 B , 5 A）
     */
    @Column(name = "credit_score")
    private Integer creditGrade;
    /**
     * 信用资料修改（0 否 ， 1 是）
     */
    @Column(name = "credit_score")
    private Integer creditModify;
    /**
     * 绑卡数量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private  Integer bindCardNum;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 真是姓名
     */
    @Column(name = "real_name")
    private String realName;
    /**
     * 年龄
     */
    private String age;
    /**
     * 性别
     */
    private String  sex;
    /**
     * 国家
     */
    private String country;
    /**
     * 国家地区
     */
    @Column(name = "country_area")
    private String countryArea;
    /**
     * 可用金额
     */
    @Column(name = "available_amount")
    private BigDecimal availableAmount;
    /**
     * 冻结金额
     */
    @Column(name = "freeze_amount")
    private  BigDecimal freezeAmount;
    /**
     * 提现金额
     */
    private  BigDecimal cashAmount;

    /**
     * 动态绑卡状态 1:没有正在绑卡 2正在绑卡
     */
    private  Integer bindStatus;
    /**
     * 动态实名认证: 0:实名认证审核中 1:没有审核
     */
    private  Integer realNameStatus;
    /**
     * 是否已领取首次奖励
     */
    private Integer gotLottery;



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getBindCardNum() {
        return bindCardNum;
    }

    public void setBindCardNum(Integer bindCardNum) {
        this.bindCardNum = bindCardNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getRealNameStatus() {
        return realNameStatus;
    }

    public void setRealNameStatus(Integer realNameStatus) {
        this.realNameStatus = realNameStatus;
    }

    public Integer getGotLottery() {
        return gotLottery;
    }

    public void setGotLottery(Integer gotLottery) {
        this.gotLottery = gotLottery;
    }

    public Integer getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(Integer creditGrade) {
        this.creditGrade = creditGrade;
    }

    public Integer getCreditModify() {
        return creditModify;
    }

    public void setCreditModify(Integer creditModify) {
        this.creditModify = creditModify;
    }
}
