package com.supermoney.loan.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by bear on 2018/1/13.
 */
public class SBountyVo  implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 任务名称
     */
    @Column(name = "bounty_name")
    private String bountyName;

    /**
     * 任务说明
     */
    @Column(name = "bounty_dsc")
    private String bountyDsc;

    /**
     * 任务赏金
     */
    @Column(name = "bounty_money")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal bountyMoney;
    /**
     * 赏金金额转换
     */
    private String formatBountyMoney;

    /**
     * 广告地址
     */
    @Column(name = "ads_url")
    private String adsUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 用户ID
     */
    private  String userId;

    /**
     * 分享URl
     */
    private  String sharUrl;
    /**
     * 广告图标
     */
    @Column(name = "ads_ico")
    private String adsIco;

    /**
     * 广告图片
     */
    @Column(name = "ads_pic")
    private String adsPic;

    /**
     * 广告类型
     */
    @Column(name = "ads_type")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer adsType;

    /**
     * 广告ID
     */
    @Column(name = "ads_id")
    private String adsId;
    /**
     * 贷款最小额度
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal loanMinMoney;

    /**
     * 贷款最大额度
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal loanMaxMoney;
    /**
     * 贷款期限
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer loanLimit;
    /**
     * 贷款期限-最大
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer loanLimitMax;

    /**
     * 贷款期限单位 0月1天
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer loanLimitUnit;
    /**
     * 放款期限
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer lendersLimit;

    /**
     * 放款期限单位 0月1天2时
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer lendersLimitUnit;
    /**
     * 借期描述
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String  limitDsc;
    /**
     * 贷款最小利率
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal loanMinRate;

    /**
     * 贷款最大利率
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal loanMaxRate;
    /**
     * 放款时间
     */
    private String lendersDay;
    /**
     * 评分
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private  BigDecimal score;
    /**
     * 每日登录赏金
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal dayMoney;

    private String formatDayMoney;

    private  String bountyCompany;
    /**
     * 借款简介
     */
    private  String loanSimpleDsc;
    /**
     * 借款详情
     */
    private  String loanDsc;
    /**
     * 项目介绍
     */
    private  String projectDsc;

    /**
     * 抵用券 名称和ID
     */
    private List<SVoucherVo> vouchers;

    /**
     * 汇率
     */
    private BigDecimal exChangeVal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBountyName() {
        return bountyName;
    }

    public void setBountyName(String bountyName) {
        this.bountyName = bountyName;
    }

    public String getBountyDsc() {
        return bountyDsc;
    }

    public void setBountyDsc(String bountyDsc) {
        this.bountyDsc = bountyDsc;
    }

    public BigDecimal getBountyMoney() {
        return bountyMoney;
    }

    public void setBountyMoney(BigDecimal bountyMoney) {
        this.bountyMoney = bountyMoney;
    }

    public String getAdsUrl() {
        return adsUrl;
    }

    public void setAdsUrl(String adsUrl) {
        this.adsUrl = adsUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSharUrl() {
        return sharUrl;
    }

    public void setSharUrl(String sharUrl) {
        this.sharUrl = sharUrl;
    }

    public String getAdsIco() {
        return adsIco;
    }

    public void setAdsIco(String adsIco) {
        this.adsIco = adsIco;
    }

    public String getAdsPic() {
        return adsPic;
    }

    public void setAdsPic(String adsPic) {
        this.adsPic = adsPic;
    }

    public Integer getAdsType() {
        return adsType;
    }

    public void setAdsType(Integer adsType) {
        this.adsType = adsType;
    }

    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }

    public BigDecimal getLoanMinMoney() {
        return loanMinMoney;
    }

    public void setLoanMinMoney(BigDecimal loanMinMoney) {
        this.loanMinMoney = loanMinMoney;
    }

    public BigDecimal getLoanMaxMoney() {
        return loanMaxMoney;
    }

    public void setLoanMaxMoney(BigDecimal loanMaxMoney) {
        this.loanMaxMoney = loanMaxMoney;
    }

    public Integer getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Integer loanLimit) {
        this.loanLimit = loanLimit;
    }

    public Integer getLoanLimitUnit() {
        return loanLimitUnit;
    }

    public void setLoanLimitUnit(Integer loanLimitUnit) {
        this.loanLimitUnit = loanLimitUnit;
    }

    public Integer getLendersLimit() {
        return lendersLimit;
    }

    public void setLendersLimit(Integer lendersLimit) {
        this.lendersLimit = lendersLimit;
    }

    public Integer getLendersLimitUnit() {
        return lendersLimitUnit;
    }

    public void setLendersLimitUnit(Integer lendersLimitUnit) {
        this.lendersLimitUnit = lendersLimitUnit;
    }

    public String getLimitDsc() {
        return limitDsc;
    }

    public void setLimitDsc(String limitDsc) {
        this.limitDsc = limitDsc;
    }

    public BigDecimal getLoanMinRate() {
        return loanMinRate;
    }

    public void setLoanMinRate(BigDecimal loanMinRate) {
        this.loanMinRate = loanMinRate;
    }

    public BigDecimal getLoanMaxRate() {
        return loanMaxRate;
    }

    public void setLoanMaxRate(BigDecimal loanMaxRate) {
        this.loanMaxRate = loanMaxRate;
    }

    public String getLendersDay() {
        return lendersDay;
    }

    public void setLendersDay(String lendersDay) {
        this.lendersDay = lendersDay;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(BigDecimal dayMoney) {
        this.dayMoney = dayMoney;
    }

    public String getBountyCompany() {
        return bountyCompany;
    }

    public void setBountyCompany(String bountyCompany) {
        this.bountyCompany = bountyCompany;
    }

    public List<SVoucherVo> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<SVoucherVo> vouchers) {
        this.vouchers = vouchers;
    }

    public Integer getLoanLimitMax() {
        return loanLimitMax;
    }

    public void setLoanLimitMax(Integer loanLimitMax) {
        this.loanLimitMax = loanLimitMax;
    }

    public String getLoanSimpleDsc() {
        return loanSimpleDsc;
    }

    public void setLoanSimpleDsc(String loanSimpleDsc) {
        this.loanSimpleDsc = loanSimpleDsc;
    }

    public String getLoanDsc() {
        return loanDsc;
    }

    public void setLoanDsc(String loanDsc) {
        this.loanDsc = loanDsc;
    }

    public String getProjectDsc() {
        return projectDsc;
    }

    public void setProjectDsc(String projectDsc) {
        this.projectDsc = projectDsc;
    }

    public String getFormatBountyMoney() {
        return formatBountyMoney;
    }

    public void setFormatBountyMoney(String formatBountyMoney) {
        this.formatBountyMoney = formatBountyMoney;
    }

    public String getFormatDayMoney() {
        return formatDayMoney;
    }

    public void setFormatDayMoney(String formatDayMoney) {
        this.formatDayMoney = formatDayMoney;
    }

    public BigDecimal getExChangeVal() {
        return exChangeVal;
    }

    public void setExChangeVal(BigDecimal exChangeVal) {
        this.exChangeVal = exChangeVal;
    }
}
