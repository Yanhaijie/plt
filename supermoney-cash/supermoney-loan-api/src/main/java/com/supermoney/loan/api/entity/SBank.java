package com.supermoney.loan.api.entity;

import javax.persistence.*;

/**
 * Created by tangwenchi on 2018/1/13.
 * <desc>银行列表</desc>
 */
@Table(name = "s_bank")
public class SBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所属国家
     */
    private String country;

    /**
     * 英文名称
     */
    @Column(name = "en_name")
    private String enName;

    /**
     * 中文名称
     */
    @Column(name = "cn_name")
    private String cnName;

    /**
     * 缩写名称
     */
    @Column(name = "ab_name")
    private String abName;

    /**
     * 银行编号
     */
    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "swift_code")
    private String swiftCode;

    /**
     * 获取 编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 编号
     *
     * @param id - 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 所属国家
     *
     * @return country - 所属国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置 所属国家
     *
     * @param country - 所属国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取 英文名称
     *
     * @return en_name - 英文名称
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置 英文名称
     *
     * @param enName - 英文名称
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 获取 中文名称
     *
     * @return cn_name - 中文名称
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 设置 中文名称
     *
     * @param cnName - 中文名称
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    /**
     * 获取 缩写名称
     *
     * @return ab_name - 缩写名称
     */
    public String getAbName() {
        return abName;
    }

    /**
     * 设置 缩写名称
     *
     * @param abName - 缩写名称
     */
    public void setAbName(String abName) {
        this.abName = abName;
    }

    /**
     * 获取 银行编号
     *
     * @return bank_code - 银行编号
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 设置 银行编号
     *
     * @param bankCode - 银行编号
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
