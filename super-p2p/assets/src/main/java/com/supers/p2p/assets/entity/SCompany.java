package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_company")
public class SCompany {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司类型，0资产方，1资金方
     */
    @Column(name = "company_type")
    private Integer companyType;

    /**
     * 公司全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 公司介绍
     */
    @Column(name = "dsc")
    private String dsc;

    /**
     * 公司简称
     */
    @Column(name = "abbreviation_name")
    private String abbreviationName;

    /**
     * 联系人
     */
    @Column(name = "link_man")
    private String linkMan;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 成立时间
     */
    private String birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 法人姓名
     */
    @Column(name = "legal_person_name")
    private String legalPersonName;

    /**
     * 身份证号
     */
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 性别0 男 1女
     */
    private Integer sex;

    /**
     * 身份证正面url
     */
    @Column(name = "id_card_first_url")
    private String idCardFirstUrl;

    /**
     * 身份证正面orc
     */
    @Column(name = "id_card_first_ocr")
    private String idCardFirstOcr;

    /**
     * 身份证反面url
     */
    @Column(name = "id_card_second_url")
    private String idCardSecondUrl;

    /**
     * 身份证反面orc
     */
    @Column(name = "id_card_second_ocr")
    private String idCardSecondOcr;

    /**
     * 统一社会信用代码证
     */
    @Column(name = "unify_credit_num")
    private String unifyCreditNum;

    /**
     * 统一社会信用代码证url
     */
    @Column(name = "unify_credit_url")
    private String unifyCreditUrl;

    /**
     * 统一社会信用代码证orc
     */
    @Column(name = "unify_credit_orc")
    private String unifyCreditOrc;

    /**
     * 银行开户许可证
     */
    @Column(name = "opening_bank_num")
    private String openingBankNum;

    /**
     * 银行开户许可证url
     */
    @Column(name = "opening_bank_url")
    private String openingBankUrl;

    /**
     * 银行开户许可证orc
     */
    @Column(name = "opening_bank_ocr")
    private String openingBankOcr;

    /**
     * 银行信用证
     */
    @Column(name = "bank_credit_num")
    private String bankCreditNum;

    /**
     * 银行信用证url
     */
    @Column(name = "bank_credit_url")
    private String bankCreditUrl;

    /**
     * 银行信用证orc
     */
    @Column(name = "bank_credit_orc")
    private String bankCreditOrc;

    /**
     * 营业执照
     */
    @Column(name = "business_license_url")
    private String businessLicenseUrl;

    /**
     * 税务登记证
     */
    @Column(name = "tax_register_url")
    private String taxRegisterUrl;

    /**
     * 资产规模
     */
    @Column(name = "asset_size")
    private String assetSize;


    /**
     * 公司类型，0待资质认证，1待审核资质认证，2,完成资质认证
     */
    @Column(name = "audit_status")
    private Integer auditStatus;


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
     * 获取公司类型，0资产方，1资金方
     *
     * @return company_type - 公司类型，0资产方，1资金方
     */
    public Integer getCompanyType() {
        return companyType;
    }

    /**
     * 设置公司类型，0资产方，1资金方
     *
     * @param companyType 公司类型，0资产方，1资金方
     */
    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取公司全称
     *
     * @return full_name - 公司全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置公司全称
     *
     * @param fullName 公司全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getDsc() {
        return dsc;
    }


    public void setDsc(String dsc) {
        this.dsc = dsc;
    }


    /**
     * 获取公司简称
     *
     * @return abbreviation_name - 公司简称
     */
    public String getAbbreviationName() {
        return abbreviationName;
    }

    /**
     * 设置公司简称
     *
     * @param abbreviationName 公司简称
     */
    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

    /**
     * 获取联系人
     *
     * @return link_man - 联系人
     */
    public String getLinkMan() {
        return linkMan;
    }

    /**
     * 设置联系人
     *
     * @param linkMan 联系人
     */
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取电子邮件
     *
     * @return email - 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     *
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取成立时间
     *
     * @return birthday - 成立时间
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置成立时间
     *
     * @param birthday 成立时间
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取法人姓名
     *
     * @return legal_person_name - 法人姓名
     */
    public String getLegalPersonName() {
        return legalPersonName;
    }

    /**
     * 设置法人姓名
     *
     * @param legalPersonName 法人姓名
     */
    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    /**
     * 获取身份证号
     *
     * @return id_number - 身份证号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证号
     *
     * @param idNumber 身份证号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取性别0 男 1女
     *
     * @return sex - 性别0 男 1女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别0 男 1女
     *
     * @param sex 性别0 男 1女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取身份证正面url
     *
     * @return id_card_first_url - 身份证正面url
     */
    public String getIdCardFirstUrl() {
        return idCardFirstUrl;
    }

    /**
     * 设置身份证正面url
     *
     * @param idCardFirstUrl 身份证正面url
     */
    public void setIdCardFirstUrl(String idCardFirstUrl) {
        this.idCardFirstUrl = idCardFirstUrl;
    }

    /**
     * 获取身份证正面orc
     *
     * @return id_card_first_ocr - 身份证正面orc
     */
    public String getIdCardFirstOcr() {
        return idCardFirstOcr;
    }

    /**
     * 设置身份证正面orc
     *
     * @param idCardFirstOcr 身份证正面orc
     */
    public void setIdCardFirstOcr(String idCardFirstOcr) {
        this.idCardFirstOcr = idCardFirstOcr;
    }

    /**
     * 获取身份证反面url
     *
     * @return id_card_second_url - 身份证反面url
     */
    public String getIdCardSecondUrl() {
        return idCardSecondUrl;
    }

    /**
     * 设置身份证反面url
     *
     * @param idCardSecondUrl 身份证反面url
     */
    public void setIdCardSecondUrl(String idCardSecondUrl) {
        this.idCardSecondUrl = idCardSecondUrl;
    }

    /**
     * 获取身份证反面orc
     *
     * @return id_card_second_ocr - 身份证反面orc
     */
    public String getIdCardSecondOcr() {
        return idCardSecondOcr;
    }

    /**
     * 设置身份证反面orc
     *
     * @param idCardSecondOcr 身份证反面orc
     */
    public void setIdCardSecondOcr(String idCardSecondOcr) {
        this.idCardSecondOcr = idCardSecondOcr;
    }

    /**
     * 获取统一社会信用代码证
     *
     * @return unify_credit_num - 统一社会信用代码证
     */
    public String getUnifyCreditNum() {
        return unifyCreditNum;
    }

    /**
     * 设置统一社会信用代码证
     *
     * @param unifyCreditNum 统一社会信用代码证
     */
    public void setUnifyCreditNum(String unifyCreditNum) {
        this.unifyCreditNum = unifyCreditNum;
    }

    /**
     * 获取统一社会信用代码证url
     *
     * @return unify_credit_url - 统一社会信用代码证url
     */
    public String getUnifyCreditUrl() {
        return unifyCreditUrl;
    }

    /**
     * 设置统一社会信用代码证url
     *
     * @param unifyCreditUrl 统一社会信用代码证url
     */
    public void setUnifyCreditUrl(String unifyCreditUrl) {
        this.unifyCreditUrl = unifyCreditUrl;
    }

    /**
     * 获取统一社会信用代码证orc
     *
     * @return unify_credit_orc - 统一社会信用代码证orc
     */
    public String getUnifyCreditOrc() {
        return unifyCreditOrc;
    }

    /**
     * 设置统一社会信用代码证orc
     *
     * @param unifyCreditOrc 统一社会信用代码证orc
     */
    public void setUnifyCreditOrc(String unifyCreditOrc) {
        this.unifyCreditOrc = unifyCreditOrc;
    }

    /**
     * 获取银行开户许可证
     *
     * @return opening_bank_num - 银行开户许可证
     */
    public String getOpeningBankNum() {
        return openingBankNum;
    }

    /**
     * 设置银行开户许可证
     *
     * @param openingBankNum 银行开户许可证
     */
    public void setOpeningBankNum(String openingBankNum) {
        this.openingBankNum = openingBankNum;
    }

    /**
     * 获取银行开户许可证url
     *
     * @return opening_bank_url - 银行开户许可证url
     */
    public String getOpeningBankUrl() {
        return openingBankUrl;
    }

    /**
     * 设置银行开户许可证url
     *
     * @param openingBankUrl 银行开户许可证url
     */
    public void setOpeningBankUrl(String openingBankUrl) {
        this.openingBankUrl = openingBankUrl;
    }

    /**
     * 获取银行开户许可证orc
     *
     * @return opening_bank_ocr - 银行开户许可证orc
     */
    public String getOpeningBankOcr() {
        return openingBankOcr;
    }

    /**
     * 设置银行开户许可证orc
     *
     * @param openingBankOcr 银行开户许可证orc
     */
    public void setOpeningBankOcr(String openingBankOcr) {
        this.openingBankOcr = openingBankOcr;
    }

    /**
     * 获取银行信用证
     *
     * @return bank_credit_num - 银行信用证
     */
    public String getBankCreditNum() {
        return bankCreditNum;
    }

    /**
     * 设置银行信用证
     *
     * @param bankCreditNum 银行信用证
     */
    public void setBankCreditNum(String bankCreditNum) {
        this.bankCreditNum = bankCreditNum;
    }

    /**
     * 获取银行信用证url
     *
     * @return bank_credit_url - 银行信用证url
     */
    public String getBankCreditUrl() {
        return bankCreditUrl;
    }

    /**
     * 设置银行信用证url
     *
     * @param bankCreditUrl 银行信用证url
     */
    public void setBankCreditUrl(String bankCreditUrl) {
        this.bankCreditUrl = bankCreditUrl;
    }

    /**
     * 获取银行信用证orc
     *
     * @return bank_credit_orc - 银行信用证orc
     */
    public String getBankCreditOrc() {
        return bankCreditOrc;
    }

    /**
     * 设置银行信用证orc
     *
     * @param bankCreditOrc 银行信用证orc
     */
    public void setBankCreditOrc(String bankCreditOrc) {
        this.bankCreditOrc = bankCreditOrc;
    }

    /**
     * 获取营业执照
     *
     * @return business_license_url - 营业执照
     */
    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    /**
     * 设置营业执照
     *
     * @param businessLicenseUrl 营业执照
     */
    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    /**
     * 获取税务登记证
     *
     * @return tax_register_url - 税务登记证
     */
    public String getTaxRegisterUrl() {
        return taxRegisterUrl;
    }

    /**
     * 设置税务登记证
     *
     * @param taxRegisterUrl 税务登记证
     */
    public void setTaxRegisterUrl(String taxRegisterUrl) {
        this.taxRegisterUrl = taxRegisterUrl;
    }

    /**
     * 获取资产规模
     *
     * @return asset_size - 资产规模
     */
    public String getAssetSize() {
        return assetSize;
    }

    /**
     * 设置资产规模
     *
     * @param assetSize 资产规模
     */
    public void setAssetSize(String assetSize) {
        this.assetSize = assetSize;
    }


    public Integer getAuditStatus() {
        return auditStatus;
    }


    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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
}