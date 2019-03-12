package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_company_info")
public class SCompanyInfo {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 统一号
     */
    @Column(name = "company_number")
    private String companyNumber;

    /**
     * 类型
     */
    @Column(name = "company_type")
    private Integer companyType;

    /**
     * 成立时间
     */
    @Column(name = "comany_birthday")
    private String comanyBirthday;

    /**
     * 住址
     */
    @Column(name = "comany_address")
    private String comanyAddress;

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
     * 性别
     */
    private Integer sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 住址
     */
    private String address;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 银行卡号
     */
    @Column(name = "bank_card_number")
    private String bankCardNumber;

    /**
     * 开户行
     */
    @Column(name = "opening_bank")
    private String openingBank;

    /**
     * 开卡预留号码
     */
    @Column(name = "bank_cark_mobile")
    private String bankCarkMobile;

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
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取统一号
     *
     * @return company_number - 统一号
     */
    public String getCompanyNumber() {
        return companyNumber;
    }

    /**
     * 设置统一号
     *
     * @param companyNumber 统一号
     */
    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    /**
     * 获取类型
     *
     * @return company_type - 类型
     */
    public Integer getCompanyType() {
        return companyType;
    }

    /**
     * 设置类型
     *
     * @param companyType 类型
     */
    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取成立时间
     *
     * @return comany_birthday - 成立时间
     */
    public String getComanyBirthday() {
        return comanyBirthday;
    }

    /**
     * 设置成立时间
     *
     * @param comanyBirthday 成立时间
     */
    public void setComanyBirthday(String comanyBirthday) {
        this.comanyBirthday = comanyBirthday;
    }

    /**
     * 获取住址
     *
     * @return comany_address - 住址
     */
    public String getComanyAddress() {
        return comanyAddress;
    }

    /**
     * 设置住址
     *
     * @param comanyAddress 住址
     */
    public void setComanyAddress(String comanyAddress) {
        this.comanyAddress = comanyAddress;
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
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取住址
     *
     * @return address - 住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置住址
     *
     * @param address 住址
     */
    public void setAddress(String address) {
        this.address = address;
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
     * 获取银行卡号
     *
     * @return bank_card_number - 银行卡号
     */
    public String getBankCardNumber() {
        return bankCardNumber;
    }

    /**
     * 设置银行卡号
     *
     * @param bankCardNumber 银行卡号
     */
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    /**
     * 获取开户行
     *
     * @return opening_bank - 开户行
     */
    public String getOpeningBank() {
        return openingBank;
    }

    /**
     * 设置开户行
     *
     * @param openingBank 开户行
     */
    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    /**
     * 获取开卡预留号码
     *
     * @return bank_cark_mobile - 开卡预留号码
     */
    public String getBankCarkMobile() {
        return bankCarkMobile;
    }

    /**
     * 设置开卡预留号码
     *
     * @param bankCarkMobile 开卡预留号码
     */
    public void setBankCarkMobile(String bankCarkMobile) {
        this.bankCarkMobile = bankCarkMobile;
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