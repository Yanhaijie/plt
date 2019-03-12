package com.supers.p2p.assets.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_asset_company")
public class SAssetCompany {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司基础信息id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 资产类型编号串
     */
    @Column(name = "asset_info")
    private String assetInfo;

    /**
     * 资产规模(元)
     */
    @Column(name = "asset_size")
    private BigDecimal assetSize;

    /**
     * 月新增规模（元）
     */
    @Column(name = "month_size")
    private BigDecimal monthSize;

    /**
     * 单笔资产期限
     */
    @Column(name = "every_deadline")
    private String everyDeadline;

    /**
     * 单笔资产金额（元）
     */
    @Column(name = "every_sum")
    private BigDecimal everySum;

    /**
     * 逾期情况
     */
    @Column(name = "overdue_M0")
    private String overdueM0;

    /**
     * 逾期情况
     */
    @Column(name = "overdue_M1")
    private String overdueM1;

    /**
     * 逾期情况
     */
    @Column(name = "overdue_M2")
    private String overdueM2;

    /**
     * 逾期情况
     */
    @Column(name = "overdue_M3")
    private String overdueM3;

    /**
     * 可接受资金成本
     */
    @Column(name = "accept_found")
    private BigDecimal acceptFound;

    /**
     * 业务区域
     */
    @Column(name = "business_area")
    private String businessArea;

    /**
     * 是否有履约险0 否 1 是
     */
    @Column(name = "is_performance_insurance")
    private Integer isPerformanceInsurance;

    /**
     * 是否担保0 否 1 是
     */
    @Column(name = "is_guarantee")
    private Integer isGuarantee;

    /**
     * 存量
     */
    private BigDecimal inventory;

    /**
     * 起投量
     */
    @Column(name = "starting_amount")
    private BigDecimal startingAmount;

    /**
     * 保证金（有无） 0 :无，1：有
     */
    @Column(name = "is_margin")
    private Integer isMargin;

    /**
     * 放款形式
     */
    @Column(name = "loan_form")
    private String loanForm;

    /**
     * 汇款形式
     */
    @Column(name = "remittance_form")
    private String remittanceForm;

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
     * 获取公司基础信息id
     *
     * @return company_id - 公司基础信息id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司基础信息id
     *
     * @param companyId 公司基础信息id
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
     * 获取资产规模(元)
     *
     * @return asset_size - 资产规模(元)
     */
    public BigDecimal getAssetSize() {
        return assetSize;
    }

    /**
     * 设置资产规模(元)
     *
     * @param assetSize 资产规模(元)
     */
    public void setAssetSize(BigDecimal assetSize) {
        this.assetSize = assetSize;
    }

    /**
     * 获取月新增规模（元）
     *
     * @return month_size - 月新增规模（元）
     */
    public BigDecimal getMonthSize() {
        return monthSize;
    }

    /**
     * 设置月新增规模（元）
     *
     * @param monthSize 月新增规模（元）
     */
    public void setMonthSize(BigDecimal monthSize) {
        this.monthSize = monthSize;
    }

    /**
     * 获取单笔资产期限
     *
     * @return every_deadline - 单笔资产期限
     */
    public String getEveryDeadline() {
        return everyDeadline;
    }

    /**
     * 设置单笔资产期限
     *
     * @param everyDeadline 单笔资产期限
     */
    public void setEveryDeadline(String everyDeadline) {
        this.everyDeadline = everyDeadline;
    }

    /**
     * 获取单笔资产金额（元）
     *
     * @return every_sum - 单笔资产金额（元）
     */
    public BigDecimal getEverySum() {
        return everySum;
    }

    /**
     * 设置单笔资产金额（元）
     *
     * @param everySum 单笔资产金额（元）
     */
    public void setEverySum(BigDecimal everySum) {
        this.everySum = everySum;
    }

    /**
     * 获取逾期情况
     *
     * @return overdue_M0 - 逾期情况
     */
    public String getOverdueM0() {
        return overdueM0;
    }

    /**
     * 设置逾期情况
     *
     * @param overdueM0 逾期情况
     */
    public void setOverdueM0(String overdueM0) {
        this.overdueM0 = overdueM0;
    }

    /**
     * 获取逾期情况
     *
     * @return overdue_M1 - 逾期情况
     */
    public String getOverdueM1() {
        return overdueM1;
    }

    /**
     * 设置逾期情况
     *
     * @param overdueM1 逾期情况
     */
    public void setOverdueM1(String overdueM1) {
        this.overdueM1 = overdueM1;
    }

    /**
     * 获取逾期情况
     *
     * @return overdue_M2 - 逾期情况
     */
    public String getOverdueM2() {
        return overdueM2;
    }

    /**
     * 设置逾期情况
     *
     * @param overdueM2 逾期情况
     */
    public void setOverdueM2(String overdueM2) {
        this.overdueM2 = overdueM2;
    }

    /**
     * 获取逾期情况
     *
     * @return overdue_M3 - 逾期情况
     */
    public String getOverdueM3() {
        return overdueM3;
    }

    /**
     * 设置逾期情况
     *
     * @param overdueM3 逾期情况
     */
    public void setOverdueM3(String overdueM3) {
        this.overdueM3 = overdueM3;
    }

    /**
     * 获取可接受资金成本
     *
     * @return accept_found - 可接受资金成本
     */
    public BigDecimal getAcceptFound() {
        return acceptFound;
    }

    /**
     * 设置可接受资金成本
     *
     * @param acceptFound 可接受资金成本
     */
    public void setAcceptFound(BigDecimal acceptFound) {
        this.acceptFound = acceptFound;
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
     * 获取是否有履约险0 否 1 是
     *
     * @return is_performance_insurance - 是否有履约险0 否 1 是
     */
    public Integer getIsPerformanceInsurance() {
        return isPerformanceInsurance;
    }

    /**
     * 设置是否有履约险0 否 1 是
     *
     * @param isPerformanceInsurance 是否有履约险0 否 1 是
     */
    public void setIsPerformanceInsurance(Integer isPerformanceInsurance) {
        this.isPerformanceInsurance = isPerformanceInsurance;
    }

    /**
     * 获取是否担保0 否 1 是
     *
     * @return is_guarantee - 是否担保0 否 1 是
     */
    public Integer getIsGuarantee() {
        return isGuarantee;
    }

    /**
     * 设置是否担保0 否 1 是
     *
     * @param isGuarantee 是否担保0 否 1 是
     */
    public void setIsGuarantee(Integer isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    /**
     * 获取存量
     *
     * @return inventory - 存量
     */
    public BigDecimal getInventory() {
        return inventory;
    }

    /**
     * 设置存量
     *
     * @param inventory 存量
     */
    public void setInventory(BigDecimal inventory) {
        this.inventory = inventory;
    }

    /**
     * 获取起投量
     *
     * @return starting_amount - 起投量
     */
    public BigDecimal getStartingAmount() {
        return startingAmount;
    }

    /**
     * 设置起投量
     *
     * @param startingAmount 起投量
     */
    public void setStartingAmount(BigDecimal startingAmount) {
        this.startingAmount = startingAmount;
    }

    /**
     * 获取保证金（有无） 0 :无，1：有
     *
     * @return is_margin - 保证金（有无） 0 :无，1：有
     */
    public Integer getIsMargin() {
        return isMargin;
    }

    /**
     * 设置保证金（有无） 0 :无，1：有
     *
     * @param isMargin 保证金（有无） 0 :无，1：有
     */
    public void setIsMargin(Integer isMargin) {
        this.isMargin = isMargin;
    }

    /**
     * 获取放款形式
     *
     * @return loan_form - 放款形式
     */
    public String getLoanForm() {
        return loanForm;
    }

    /**
     * 设置放款形式
     *
     * @param loanForm 放款形式
     */
    public void setLoanForm(String loanForm) {
        this.loanForm = loanForm;
    }

    /**
     * 获取汇款形式
     *
     * @return remittance_form - 汇款形式
     */
    public String getRemittanceForm() {
        return remittanceForm;
    }

    /**
     * 设置汇款形式
     *
     * @param remittanceForm 汇款形式
     */
    public void setRemittanceForm(String remittanceForm) {
        this.remittanceForm = remittanceForm;
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


    public SAssetCompany toPoEntity(){
        //亿转为元
        BigDecimal assetSize = this.getAssetSize();
        //亿转为元
        BigDecimal monthSize = this.getMonthSize();
        //亿转为元
        BigDecimal inventory = this.getInventory();
        //万转为元
        BigDecimal startingAmount = this.getStartingAmount();
        //百分比转为小数
        BigDecimal acceptFound = this.getAcceptFound();

        if(assetSize != null){
            this.setAssetSize(assetSize.multiply(new BigDecimal("100000000")));
        }
        if(monthSize != null){
            this.setMonthSize(monthSize.multiply(new BigDecimal("100000000")));
        }

        if(inventory != null){
            this.setInventory(inventory.multiply(new BigDecimal("100000000")));
        }
        if(startingAmount != null){
            this.setStartingAmount(startingAmount.multiply(new BigDecimal("10000")));
        }
        if(acceptFound != null){
            this.setAcceptFound(acceptFound.divide(new BigDecimal("100")));
        }


        return this;
    }

    public SAssetCompany toVoEntity(){
        //元转为亿
        BigDecimal assetSize = this.getAssetSize();
        //元转为亿
        BigDecimal monthSize = this.getMonthSize();
        //元转为亿
        BigDecimal inventory = this.getInventory();
        //元转为万
        BigDecimal startingAmount = this.getStartingAmount();
        //小数转为百分比
        BigDecimal acceptFound = this.getAcceptFound();

        if(assetSize != null){
            this.setAssetSize(assetSize.divide(new BigDecimal("100000000")));
        }
        if(monthSize != null){
            this.setMonthSize(monthSize.divide(new BigDecimal("100000000")));
        }

        if(inventory != null){
            this.setInventory(inventory.divide(new BigDecimal("100000000")));
        }
        if(startingAmount != null){
            this.setStartingAmount(startingAmount.divide(new BigDecimal("10000")));
        }
        if(acceptFound != null){
            this.setAcceptFound(acceptFound.multiply(new BigDecimal("100")));
        }
        return this;
    }
}