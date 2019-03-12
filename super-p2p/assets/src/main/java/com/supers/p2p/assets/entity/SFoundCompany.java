package com.supers.p2p.assets.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_found_company")
public class SFoundCompany {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司基础信息编号
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 资产类型编号串
     */
    @Column(name = "asset_info")
    private String assetInfo;

    /**
     * 预测资产需求量
     */
    @Column(name = "forecast_asset_demand")
    private BigDecimal forecastAssetDemand;

    /**
     * 要求条件
     */
    @Column(name = "found_condition")
    private String foundCondition;

    /**
     * 资金年化率范围
     */
    @Column(name = "found_year_rate_rand")
    private String foundYearRateRand;

    /**
     * 资金成本
     */
    @Column(name = "found_cost")
    private BigDecimal foundCost;

    /**
     * 资金成本
     */
    @Column(name = "month_transaction_rate")
    private BigDecimal monthTransactionRate;

    /**
     * 募集失败率
     */
    @Column(name = "collect_fail_rate")
    private BigDecimal collectFailRate;

    /**
     * 业务区域
     */
    @Column(name = "business_area")
    private String businessArea;

    /**
     * 平台名称
     */
    @Column(name = "platform_name")
    private String platformName;

    /**
     * 网址
     */
    private String url;

    /**
     * 待收本金
     */
    @Column(name = "unReceived_principal")
    private BigDecimal unreceivedPrincipal;

    /**
     * 月投金额
     */
    @Column(name = "month_investment_amount")
    private BigDecimal monthInvestmentAmount;

    /**
     * 有无存管 0无，1有
     */
    private Integer escrow;

    /**
     * 是否直投 0 否 1是
     */
    @Column(name = "is_direct_investment")
    private Integer isDirectInvestment;

    /**
     * 是否直还 0否，1是
     */
    @Column(name = "is_straight_back")
    private Integer isStraightBack;


    /**
     * 月交易额
     */
    @Column(name = "month_transaction_amount")
    private BigDecimal monthTransactionAmount;

    /**
     * 保证金
     */
    @Column(name = "margin")
    private BigDecimal margin;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 操作人
     */
    private String opt;

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
     * 获取公司基础信息编号
     *
     * @return company_id - 公司基础信息编号
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司基础信息编号
     *
     * @param companyId 公司基础信息编号
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取资产类型编号串
     *
     * @return asset_info - 资产类型编号串
     */
    public String getAssetInfo() {
        return assetInfo;
    }

    /**
     * 设置资产类型编号串
     *
     * @param assetInfo 资产类型编号串
     */
    public void setAssetInfo(String assetInfo) {
        this.assetInfo = assetInfo;
    }

    /**
     * 获取预测资产需求量
     *
     * @return forecast_asset_demand - 预测资产需求量
     */
    public BigDecimal getForecastAssetDemand() {
        return forecastAssetDemand;
    }

    /**
     * 设置预测资产需求量
     *
     * @param forecastAssetDemand 预测资产需求量
     */
    public void setForecastAssetDemand(BigDecimal forecastAssetDemand) {
        this.forecastAssetDemand = forecastAssetDemand;
    }

    /**
     * 获取要求条件
     *
     * @return found_condition - 要求条件
     */
    public String getFoundCondition() {
        return foundCondition;
    }

    /**
     * 设置要求条件
     *
     * @param foundCondition 要求条件
     */
    public void setFoundCondition(String foundCondition) {
        this.foundCondition = foundCondition;
    }

    /**
     * 获取资金年化率范围
     *
     * @return found_year_rate_rand - 资金年化率范围
     */
    public String getFoundYearRateRand() {
        return foundYearRateRand;
    }

    /**
     * 设置资金年化率范围
     *
     * @param foundYearRateRand 资金年化率范围
     */
    public void setFoundYearRateRand(String foundYearRateRand) {
        this.foundYearRateRand = foundYearRateRand;
    }

    /**
     * 获取资金成本
     *
     * @return found_cost - 资金成本
     */
    public BigDecimal getFoundCost() {
        return foundCost;
    }

    /**
     * 设置资金成本
     *
     * @param foundCost 资金成本
     */
    public void setFoundCost(BigDecimal foundCost) {
        this.foundCost = foundCost;
    }

    /**
     * 获取资金成本
     *
     * @return month_transaction_rate - 资金成本
     */
    public BigDecimal getMonthTransactionRate() {
        return monthTransactionRate;
    }

    /**
     * 设置资金成本
     *
     * @param monthTransactionRate 资金成本
     */
    public void setMonthTransactionRate(BigDecimal monthTransactionRate) {
        this.monthTransactionRate = monthTransactionRate;
    }

    /**
     * 获取募集失败率
     *
     * @return collect_fail_rate - 募集失败率
     */
    public BigDecimal getCollectFailRate() {
        return collectFailRate;
    }

    /**
     * 设置募集失败率
     *
     * @param collectFailRate 募集失败率
     */
    public void setCollectFailRate(BigDecimal collectFailRate) {
        this.collectFailRate = collectFailRate;
    }

    /**
     * 获取业务区域
     *
     * @return business_area - 业务区域
     */
    public String getBusinessArea() {
        return businessArea;
    }

    /**
     * 设置业务区域
     *
     * @param businessArea 业务区域
     */
    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    /**
     * 获取平台名称
     *
     * @return platform_name - 平台名称
     */
    public String getPlatformName() {
        return platformName;
    }

    /**
     * 设置平台名称
     *
     * @param platformName 平台名称
     */
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    /**
     * 获取网址
     *
     * @return url - 网址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置网址
     *
     * @param url 网址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取待收本金
     *
     * @return unReceived_principal - 待收本金
     */
    public BigDecimal getUnreceivedPrincipal() {
        return unreceivedPrincipal;
    }

    /**
     * 设置待收本金
     *
     * @param unreceivedPrincipal 待收本金
     */
    public void setUnreceivedPrincipal(BigDecimal unreceivedPrincipal) {
        this.unreceivedPrincipal = unreceivedPrincipal;
    }

    /**
     * 获取月投金额
     *
     * @return month_investment_amount - 月投金额
     */
    public BigDecimal getMonthInvestmentAmount() {
        return monthInvestmentAmount;
    }

    /**
     * 设置月投金额
     *
     * @param monthInvestmentAmount 月投金额
     */
    public void setMonthInvestmentAmount(BigDecimal monthInvestmentAmount) {
        this.monthInvestmentAmount = monthInvestmentAmount;
    }

    /**
     * 获取有无存管 0无，1有
     *
     * @return escrow - 有无存管 0无，1有
     */
    public Integer getEscrow() {
        return escrow;
    }

    /**
     * 设置有无存管 0无，1有
     *
     * @param escrow 有无存管 0无，1有
     */
    public void setEscrow(Integer escrow) {
        this.escrow = escrow;
    }

    /**
     * 获取是否直投 0 否 1是
     *
     * @return is_direct_investment - 是否直投 0 否 1是
     */
    public Integer getIsDirectInvestment() {
        return isDirectInvestment;
    }

    /**
     * 设置是否直投 0 否 1是
     *
     * @param isDirectInvestment 是否直投 0 否 1是
     */
    public void setIsDirectInvestment(Integer isDirectInvestment) {
        this.isDirectInvestment = isDirectInvestment;
    }

    /**
     * 获取是否直还 0否，1是
     *
     * @return is_straight_back - 是否直还 0否，1是
     */
    public Integer getIsStraightBack() {
        return isStraightBack;
    }

    /**
     * 设置是否直还 0否，1是
     *
     * @param isStraightBack 是否直还 0否，1是
     */
    public void setIsStraightBack(Integer isStraightBack) {
        this.isStraightBack = isStraightBack;
    }

    public BigDecimal getMonthTransactionAmount() {
        return monthTransactionAmount;
    }

    public void setMonthTransactionAmount(BigDecimal monthTransactionAmount) {
        this.monthTransactionAmount = monthTransactionAmount;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
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


    public SFoundCompany toPoEntity(){
        //亿转为元
        BigDecimal forecastAssetDemand = this.getForecastAssetDemand();
        //亿转为元
/*        BigDecimal foundCost = this.getFoundCost();*/
        //亿转为元
        BigDecimal unReceivedPrincipal = this.getUnreceivedPrincipal();
        //亿转为元
        BigDecimal monthInvestmentAmount = this.getMonthInvestmentAmount();
        //百分比转为小数
        BigDecimal monthTransactionRate = this.getMonthTransactionRate();
        //百分比转为小数
        BigDecimal collectFailRate = this.getCollectFailRate();
        //亿转为元
        BigDecimal monthTransactionAmount = this.getMonthTransactionAmount();
        //百分比转为小数
        BigDecimal margin = this.getMargin();
        if(forecastAssetDemand != null){
            this.setForecastAssetDemand(forecastAssetDemand.multiply(new BigDecimal("100000000")));
        }
      /*  if(foundCost != null){
            this.setFoundCost(foundCost.intValue()*100000000);
        }*/
        if(unReceivedPrincipal != null){
            this.setUnreceivedPrincipal(unReceivedPrincipal.multiply(new BigDecimal("100000000")));
        }
        if(monthInvestmentAmount != null){
            this.setMonthInvestmentAmount(monthInvestmentAmount.multiply(new BigDecimal("100000000")));
        }
        if(monthTransactionRate != null){
            this.setMonthTransactionRate(monthTransactionRate.divide(new BigDecimal("100")));
        }
        if(collectFailRate != null){
            this.setCollectFailRate(monthTransactionRate.divide(new BigDecimal("100")));
        }
        if(monthTransactionAmount != null){
            this.setMonthTransactionAmount(monthTransactionAmount.multiply(new BigDecimal("100000000")));
        }
        if(margin != null){
            this.setMargin(margin.divide(new BigDecimal("100")));
        }
        return this;
    }

    public SFoundCompany toVoEntity(){
        //元转为亿
        BigDecimal forecastAssetDemand = this.getForecastAssetDemand();
  /*      //元转为亿
        Integer foundCost = this.getFoundCost();*/
        //元转为亿
        BigDecimal unReceivedPrincipal = this.getUnreceivedPrincipal();
        //元转为亿
        BigDecimal monthInvestmentAmount = this.getMonthInvestmentAmount();
        //小数转为百分比
        BigDecimal collectFailRate = this.getCollectFailRate();
        //小数转为百分比
        BigDecimal monthTransactionRate = this.getMonthTransactionRate();
        //元转为亿
        BigDecimal monthTransactionAmount = this.getMonthTransactionAmount();
        //小数转为百分比
        BigDecimal margin = this.getMargin();


        if(forecastAssetDemand != null){
            this.setForecastAssetDemand(forecastAssetDemand.divide(new BigDecimal("100000000")));
        }
       /* if(foundCost != null){
            this.setFoundCost(foundCost.divide(new BigDecimal("100000000")));
        }*/
        if(unReceivedPrincipal != null){
            this.setUnreceivedPrincipal(unReceivedPrincipal.divide(new BigDecimal("100000000")));
        }
        if(monthInvestmentAmount != null){
            this.setMonthInvestmentAmount(monthInvestmentAmount.divide(new BigDecimal("100000000")));
        }
        if(monthTransactionRate != null){
            this.setMonthTransactionRate(monthTransactionRate.multiply(new BigDecimal("100")));
        }
        if(collectFailRate != null){
            this.setCollectFailRate(collectFailRate.multiply(new BigDecimal("100")));
        }
        if(monthTransactionAmount != null){
            this.setMonthTransactionAmount(monthTransactionAmount.divide(new BigDecimal("100000000")));
        }
        if(margin != null){
            this.setMargin(margin.multiply(new BigDecimal("100")));
        }
        return this;
    }
}


