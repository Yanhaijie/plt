package com.supermoney.loan.mg.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "s_bounty")
public class SBounty {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务名称
     */
    @Column(name = "bounty_name")
    private String bountyName;
    /**
     * company
     */
    @Column(name = "bounty_company")
    private  String bountyCompany;

    /**
     * 任务说明
     */
    @Column(name = "bounty_dsc")
    private String bountyDsc;

    /**
     * 任务赏金
     */
    @Column(name = "bounty_money")
    private BigDecimal bountyMoney;
    /**
     * 每天赏金
     */
    @Column(name = "day_money")
    private BigDecimal dayMoney;

    /**
     * 评分
     */
    @Column(name = "bounty_score")
    private BigDecimal bountyScore;

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
    private Integer adsType;

    /**
     * 广告ID
     */
    @Column(name = "ads_id")
    private String adsId;

    /**
     * 贷款最小额度
     */
    @Column(name = "loan_min_money")
    private BigDecimal loanMinMoney;

    /**
     * 贷款最大额度
     */
    @Column(name = "loan_max_money")
    private BigDecimal loanMaxMoney;

    /**
     * 贷款最小利率
     */
    @Column(name = "loan_min_rate")
    private BigDecimal loanMinRate;

    /**
     * 贷款最大利率
     */
    @Column(name = "loan_max_rate")
    private BigDecimal loanMaxRate;

    /**
     * 贷款期限
     */
    @Column(name = "loan_limit")
    private Integer loanLimit;
    /**
     * 贷款期限 最大值
     */
    @Column(name = "loan_limit_max")
    private Integer loanLimitMax;

    /**
     * 贷款期限单位 0月1天
     */
    @Column(name = "loan_limit_unit")
    private Integer loanLimitUnit;
    /**
     * 放款期限
     */
    @Column(name = "lenders_limit")
    private Integer lendersLimit;

    /**
     * 放款期限单位 0月1天2时
     */
    @Column(name = "lenders_limit_unit")
    private Integer lendersLimitUnit;

    /**
     * 放期描述
     */
    @Column(name = "lenders_dsc")
    private String  lendersDsc;

    /**
     * 借期描述
     */
    @Column(name = "limit_dsc")
    private String  limitDsc;

    /**
     * 广告地址
     */
    @Column(name = "ads_url")
    private String adsUrl;

    /**
     * 广告回掉地址
     */
    @Column(name = "ads_callback_url")
    private String adsCallbackUrl;

    /**
     * H5引导地址
     */
    @Column(name = "lead_url")
    private String leadUrl;

    /**
     * H5引导地址类型
     */
    @Column(name = "lead_url_type")
    private Integer leadUrlType;

    /**
     * 任务状态:0待发布、1发布、2停发
     */
    @Column(name = "bounty_status")
    private Integer bountyStatus;

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
     * 操作人
     */
    private String opt;
    /**
     * 借款简介
     */
    @Column(name = "loan_simple_dsc")
    private  String loanSimpleDsc;

    /**
     * 借款介绍
     */
    @Column(name = "loan_dsc")
    private String  loanDsc;


    /**
     * 借款介绍
     */
    @Column(name = "project_dsc")
    private String  projectDsc;
    /**
     * 前台排序
     */
    @Column(name="front_sort")
    private Integer frontSort;
    /**
     * 国家地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;

    /**
     * API商户ID
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * API商户产品编号
     */
    @Column(name = "merchant_product_code")
    private String merchantProductCode;


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
     * 获取任务名称
     *
     * @return bounty_name - 任务名称
     */
    public String getBountyName() {
        return bountyName;
    }

    /**
     * 设置任务名称
     *
     * @param bountyName 任务名称
     */
    public void setBountyName(String bountyName) {
        this.bountyName = bountyName;
    }

    /**
     * 获取任务说明
     *
     * @return bounty_dsc - 任务说明
     */
    public String getBountyDsc() {
        return bountyDsc;
    }

    /**
     * 设置任务说明
     *
     * @param bountyDsc 任务说明
     */
    public void setBountyDsc(String bountyDsc) {
        this.bountyDsc = bountyDsc;
    }

    /**
     * 获取任务赏金
     *
     * @return bounty_money - 任务赏金
     */
    public BigDecimal getBountyMoney() {
        return bountyMoney;
    }

    /**
     * 设置任务赏金
     *
     * @param bountyMoney 任务赏金
     */
    public void setBountyMoney(BigDecimal bountyMoney) {
        this.bountyMoney = bountyMoney;
    }

    /**
     * 获取广告图标
     *
     * @return ads_ico - 广告图标
     */
    public String getAdsIco() {
        return adsIco;
    }

    /**
     * 设置广告图标
     *
     * @param adsIco 广告图标
     */
    public void setAdsIco(String adsIco) {
        this.adsIco = adsIco;
    }

    /**
     * 获取广告图片
     *
     * @return ads_pic - 广告图片
     */
    public String getAdsPic() {
        return adsPic;
    }

    /**
     * 设置广告图片
     *
     * @param adsPic 广告图片
     */
    public void setAdsPic(String adsPic) {
        this.adsPic = adsPic;
    }

    /**
     * 获取广告类型
     *
     * @return ads_type - 广告类型
     */
    public Integer getAdsType() {
        return adsType;
    }

    /**
     * 设置广告类型
     *
     * @param adsType 广告类型
     */
    public void setAdsType(Integer adsType) {
        this.adsType = adsType;
    }

    /**
     * 获取广告ID
     *
     * @return ads_id - 广告ID
     */
    public String getAdsId() {
        return adsId;
    }

    /**
     * 设置广告ID
     *
     * @param adsId 广告ID
     */
    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }

    /**
     * 获取贷款最小额度
     *
     * @return loan_min_money - 贷款最小额度
     */
    public BigDecimal getLoanMinMoney() {
        return loanMinMoney;
    }

    /**
     * 设置贷款最小额度
     *
     * @param loanMinMoney 贷款最小额度
     */
    public void setLoanMinMoney(BigDecimal loanMinMoney) {
        this.loanMinMoney = loanMinMoney;
    }

    /**
     * 获取贷款最大额度
     *
     * @return loan_max_money - 贷款最大额度
     */
    public BigDecimal getLoanMaxMoney() {
        return loanMaxMoney;
    }

    /**
     * 设置贷款最大额度
     *
     * @param loanMaxMoney 贷款最大额度
     */
    public void setLoanMaxMoney(BigDecimal loanMaxMoney) {
        this.loanMaxMoney = loanMaxMoney;
    }

    /**
     * 获取贷款最小利率
     *
     * @return loan_min_rate - 贷款最小利率
     */
    public BigDecimal getLoanMinRate() {
        return loanMinRate;
    }

    /**
     * 设置贷款最小利率
     *
     * @param loanMinRate 贷款最小利率
     */
    public void setLoanMinRate(BigDecimal loanMinRate) {
        this.loanMinRate = loanMinRate;
    }

    /**
     * 获取贷款最大利率
     *
     * @return loan_max_rate - 贷款最大利率
     */
    public BigDecimal getLoanMaxRate() {
        return loanMaxRate;
    }

    /**
     * 设置贷款最大利率
     *
     * @param loanMaxRate 贷款最大利率
     */
    public void setLoanMaxRate(BigDecimal loanMaxRate) {
        this.loanMaxRate = loanMaxRate;
    }

    /**
     * 获取贷款期限
     *
     * @return loan_limit - 贷款期限
     */
    public Integer getLoanLimit() {
        return loanLimit;
    }

    /**
     * 设置贷款期限
     *
     * @param loanLimit 贷款期限
     */
    public void setLoanLimit(Integer loanLimit) {
        this.loanLimit = loanLimit;
    }

    /**
     * 获取贷款期限单位 0月1天
     *
     * @return loan_limit_unit - 贷款期限单位 0月1天
     */
    public Integer getLoanLimitUnit() {
        return loanLimitUnit;
    }

    /**
     * 设置贷款期限单位 0月1天
     *
     * @param loanLimitUnit 贷款期限单位 0月1天
     */
    public void setLoanLimitUnit(Integer loanLimitUnit) {
        this.loanLimitUnit = loanLimitUnit;
    }

    /**
     * 获取广告地址
     *
     * @return ads_url - 广告地址
     */
    public String getAdsUrl() {
        return adsUrl;
    }

    /**
     * 设置广告地址
     *
     * @param adsUrl 广告地址
     */
    public void setAdsUrl(String adsUrl) {
        this.adsUrl = adsUrl;
    }

    /**
     * 获取广告回掉地址
     *
     * @return ads_callback_url - 广告回掉地址
     */
    public String getAdsCallbackUrl() {
        return adsCallbackUrl;
    }

    public String getLeadUrl() {
        return leadUrl;
    }

    public void setLeadUrl(String leadUrl) {
        this.leadUrl = leadUrl;
    }

    public Integer getLeadUrlType() {
        return leadUrlType;
    }

    public void setLeadUrlType(Integer leadUrlType) {
        this.leadUrlType = leadUrlType;
    }

    /**
     * 设置广告回掉地址
     *
     * @param adsCallbackUrl 广告回掉地址
     */
    public void setAdsCallbackUrl(String adsCallbackUrl) {
        this.adsCallbackUrl = adsCallbackUrl;
    }

    /**
     * 获取任务状态:0待发布、1发布、2停发
     *
     * @return bounty_status - 任务状态:0待发布、1发布、2停发
     */
    public Integer getBountyStatus() {
        return bountyStatus;
    }

    /**
     * 设置任务状态:0待发布、1发布、2停发
     *
     * @param bountyStatus 任务状态:0待发布、1发布、2停发
     */
    public void setBountyStatus(Integer bountyStatus) {
        this.bountyStatus = bountyStatus;
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
     * 获取操作人
     *
     * @return opt - 操作人
     */
    public String getOpt() {
        return opt;
    }

    /**
     * 设置操作人
     *
     * @param opt 操作人
     */
    public void setOpt(String opt) {
        this.opt = opt;
    }

    public BigDecimal getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(BigDecimal dayMoney) {
        this.dayMoney = dayMoney;
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

    public String getLendersDsc() {
        return lendersDsc;
    }

    public void setLendersDsc(String lendersDsc) {
        this.lendersDsc = lendersDsc;
    }

    public String getLimitDsc() {
        return limitDsc;
    }

    public void setLimitDsc(String limitDsc) {
        this.limitDsc = limitDsc;
    }

    public BigDecimal getBountyScore() {
        return bountyScore;
    }

    public void setBountyScore(BigDecimal bountyScore) {
        this.bountyScore = bountyScore;
    }

    public String getBountyCompany() {
        return bountyCompany;
    }

    public void setBountyCompany(String bountyCompany) {
        this.bountyCompany = bountyCompany;
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

    public String getLoanSimpleDsc() {
        return loanSimpleDsc;
    }

    public void setLoanSimpleDsc(String loanSimpleDsc) {
        this.loanSimpleDsc = loanSimpleDsc;
    }

    public Integer getLoanLimitMax() {
        return loanLimitMax;
    }

    public void setLoanLimitMax(Integer loanLimitMax) {
        this.loanLimitMax = loanLimitMax;
    }

    public Integer getFrontSort() {
        return frontSort;
    }

    public void setFrontSort(Integer frontSort) {
        this.frontSort = frontSort;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantProductCode() {
        return merchantProductCode;
    }

    public void setMerchantProductCode(String merchantProductCode) {
        this.merchantProductCode = merchantProductCode;
    }
}