package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_company_credit")
public class SCompanyCredit {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 营业执照正面链接
     */
    @Column(name = "business_license_first_url")
    private String businessLicenseFirstUrl;

    /**
     * 营业执照反面面链接
     */
    @Column(name = "business_license_second_url")
    private String businessLicenseSecondUrl;

    /**
     * 开户许可证正面链接
     */
    @Column(name = "open_license_first_url")
    private String openLicenseFirstUrl;

    /**
     * 开户许可证反面链接
     */
    @Column(name = "open_license_second_url")
    private String openLicenseSecondUrl;

    /**
     * 法人身份证正面链接
     */
    @Column(name = "id_card_first_url")
    private String idCardFirstUrl;

    /**
     * 法人身份证反面链接
     */
    @Column(name = "id_card_second_url")
    private String idCardSecondUrl;

    /**
     * 法人手持身份证
     */
    @Column(name = "bank_card_url")
    private String bankCardUrl;

    /**
     * 合同状态,0不同意，1同意合同
     */
    @Column(name = "pact_status")
    private Integer pactStatus;

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
     * 合同信息
     */
    @Column(name = "pact_info")
    private String pactInfo;

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
     * 获取营业执照正面链接
     *
     * @return business_license_first_url - 营业执照正面链接
     */
    public String getBusinessLicenseFirstUrl() {
        return businessLicenseFirstUrl;
    }

    /**
     * 设置营业执照正面链接
     *
     * @param businessLicenseFirstUrl 营业执照正面链接
     */
    public void setBusinessLicenseFirstUrl(String businessLicenseFirstUrl) {
        this.businessLicenseFirstUrl = businessLicenseFirstUrl;
    }

    /**
     * 获取营业执照反面面链接
     *
     * @return business_license_second_url - 营业执照反面面链接
     */
    public String getBusinessLicenseSecondUrl() {
        return businessLicenseSecondUrl;
    }

    /**
     * 设置营业执照反面面链接
     *
     * @param businessLicenseSecondUrl 营业执照反面面链接
     */
    public void setBusinessLicenseSecondUrl(String businessLicenseSecondUrl) {
        this.businessLicenseSecondUrl = businessLicenseSecondUrl;
    }

    /**
     * 获取开户许可证正面链接
     *
     * @return open_license_first_url - 开户许可证正面链接
     */
    public String getOpenLicenseFirstUrl() {
        return openLicenseFirstUrl;
    }

    /**
     * 设置开户许可证正面链接
     *
     * @param openLicenseFirstUrl 开户许可证正面链接
     */
    public void setOpenLicenseFirstUrl(String openLicenseFirstUrl) {
        this.openLicenseFirstUrl = openLicenseFirstUrl;
    }

    /**
     * 获取开户许可证反面链接
     *
     * @return open_license_second_url - 开户许可证反面链接
     */
    public String getOpenLicenseSecondUrl() {
        return openLicenseSecondUrl;
    }

    /**
     * 设置开户许可证反面链接
     *
     * @param openLicenseSecondUrl 开户许可证反面链接
     */
    public void setOpenLicenseSecondUrl(String openLicenseSecondUrl) {
        this.openLicenseSecondUrl = openLicenseSecondUrl;
    }

    /**
     * 获取法人身份证正面链接
     *
     * @return id_card_first_url - 法人身份证正面链接
     */
    public String getIdCardFirstUrl() {
        return idCardFirstUrl;
    }

    /**
     * 设置法人身份证正面链接
     *
     * @param idCardFirstUrl 法人身份证正面链接
     */
    public void setIdCardFirstUrl(String idCardFirstUrl) {
        this.idCardFirstUrl = idCardFirstUrl;
    }

    /**
     * 获取法人身份证反面链接
     *
     * @return id_card_second_url - 法人身份证反面链接
     */
    public String getIdCardSecondUrl() {
        return idCardSecondUrl;
    }

    /**
     * 设置法人身份证反面链接
     *
     * @param idCardSecondUrl 法人身份证反面链接
     */
    public void setIdCardSecondUrl(String idCardSecondUrl) {
        this.idCardSecondUrl = idCardSecondUrl;
    }

    /**
     * 获取法人手持身份证
     *
     * @return bank_card_url - 法人手持身份证
     */
    public String getBankCardUrl() {
        return bankCardUrl;
    }

    /**
     * 设置法人手持身份证
     *
     * @param bankCardUrl 法人手持身份证
     */
    public void setBankCardUrl(String bankCardUrl) {
        this.bankCardUrl = bankCardUrl;
    }

    /**
     * 获取合同状态,0不同意，1同意合同
     *
     * @return pact_status - 合同状态,0不同意，1同意合同
     */
    public Integer getPactStatus() {
        return pactStatus;
    }

    /**
     * 设置合同状态,0不同意，1同意合同
     *
     * @param pactStatus 合同状态,0不同意，1同意合同
     */
    public void setPactStatus(Integer pactStatus) {
        this.pactStatus = pactStatus;
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

    /**
     * 获取合同信息
     *
     * @return pact_info - 合同信息
     */
    public String getPactInfo() {
        return pactInfo;
    }

    /**
     * 设置合同信息
     *
     * @param pactInfo 合同信息
     */
    public void setPactInfo(String pactInfo) {
        this.pactInfo = pactInfo;
    }
}