package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_persional_credit")
public class SPersionalCredit {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 身份证正面链接
     */
    @Column(name = "id_card_first_url")
    private String idCardFirstUrl;

    /**
     * 身份证反面链接
     */
    @Column(name = "id_card_second_url")
    private String idCardSecondUrl;

    /**
     * 银行卡正面链接
     */
    @Column(name = "bank_card_first_url")
    private String bankCardFirstUrl;

    /**
     * 银行卡反面链接
     */
    @Column(name = "bank_card_second_url")
    private String bankCardSecondUrl;

    /**
     * 手持身份证
     */
    @Column(name = "person_id_card_url")
    private String personIdCardUrl;

    /**
     * 手持身份证
     */
    @Column(name = "person_bank_card_url")
    private String personBankCardUrl;

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
     * 身份证正面信息
     */
    @Column(name = "id_card_fitst_orc")
    private String idCardFitstOrc;

    /**
     * 身份证反面信息
     */
    @Column(name = "id_card_second_orc")
    private String idCardSecondOrc;

    /**
     * 银行卡正面信息
     */
    @Column(name = "bank_card_first_ocr")
    private String bankCardFirstOcr;

    /**
     * 银行卡反面信息
     */
    @Column(name = "bank_card_second_ocr")
    private String bankCardSecondOcr;

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
     * 获取身份证正面链接
     *
     * @return id_card_first_url - 身份证正面链接
     */
    public String getIdCardFirstUrl() {
        return idCardFirstUrl;
    }

    /**
     * 设置身份证正面链接
     *
     * @param idCardFirstUrl 身份证正面链接
     */
    public void setIdCardFirstUrl(String idCardFirstUrl) {
        this.idCardFirstUrl = idCardFirstUrl;
    }

    /**
     * 获取身份证反面链接
     *
     * @return id_card_second_url - 身份证反面链接
     */
    public String getIdCardSecondUrl() {
        return idCardSecondUrl;
    }

    /**
     * 设置身份证反面链接
     *
     * @param idCardSecondUrl 身份证反面链接
     */
    public void setIdCardSecondUrl(String idCardSecondUrl) {
        this.idCardSecondUrl = idCardSecondUrl;
    }

    /**
     * 获取银行卡正面链接
     *
     * @return bank_card_first_url - 银行卡正面链接
     */
    public String getBankCardFirstUrl() {
        return bankCardFirstUrl;
    }

    /**
     * 设置银行卡正面链接
     *
     * @param bankCardFirstUrl 银行卡正面链接
     */
    public void setBankCardFirstUrl(String bankCardFirstUrl) {
        this.bankCardFirstUrl = bankCardFirstUrl;
    }

    /**
     * 获取银行卡反面链接
     *
     * @return bank_card_second_url - 银行卡反面链接
     */
    public String getBankCardSecondUrl() {
        return bankCardSecondUrl;
    }

    /**
     * 设置银行卡反面链接
     *
     * @param bankCardSecondUrl 银行卡反面链接
     */
    public void setBankCardSecondUrl(String bankCardSecondUrl) {
        this.bankCardSecondUrl = bankCardSecondUrl;
    }

    /**
     * 获取手持身份证
     *
     * @return person_id_card_url - 手持身份证
     */
    public String getPersonIdCardUrl() {
        return personIdCardUrl;
    }

    /**
     * 设置手持身份证
     *
     * @param personIdCardUrl 手持身份证
     */
    public void setPersonIdCardUrl(String personIdCardUrl) {
        this.personIdCardUrl = personIdCardUrl;
    }

    /**
     * 获取手持身份证
     *
     * @return person_bank_card_url - 手持身份证
     */
    public String getPersonBankCardUrl() {
        return personBankCardUrl;
    }

    /**
     * 设置手持身份证
     *
     * @param personBankCardUrl 手持身份证
     */
    public void setPersonBankCardUrl(String personBankCardUrl) {
        this.personBankCardUrl = personBankCardUrl;
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
     * 获取身份证正面信息
     *
     * @return id_card_fitst_orc - 身份证正面信息
     */
    public String getIdCardFitstOrc() {
        return idCardFitstOrc;
    }

    /**
     * 设置身份证正面信息
     *
     * @param idCardFitstOrc 身份证正面信息
     */
    public void setIdCardFitstOrc(String idCardFitstOrc) {
        this.idCardFitstOrc = idCardFitstOrc;
    }

    /**
     * 获取身份证反面信息
     *
     * @return id_card_second_orc - 身份证反面信息
     */
    public String getIdCardSecondOrc() {
        return idCardSecondOrc;
    }

    /**
     * 设置身份证反面信息
     *
     * @param idCardSecondOrc 身份证反面信息
     */
    public void setIdCardSecondOrc(String idCardSecondOrc) {
        this.idCardSecondOrc = idCardSecondOrc;
    }

    /**
     * 获取银行卡正面信息
     *
     * @return bank_card_first_ocr - 银行卡正面信息
     */
    public String getBankCardFirstOcr() {
        return bankCardFirstOcr;
    }

    /**
     * 设置银行卡正面信息
     *
     * @param bankCardFirstOcr 银行卡正面信息
     */
    public void setBankCardFirstOcr(String bankCardFirstOcr) {
        this.bankCardFirstOcr = bankCardFirstOcr;
    }

    /**
     * 获取银行卡反面信息
     *
     * @return bank_card_second_ocr - 银行卡反面信息
     */
    public String getBankCardSecondOcr() {
        return bankCardSecondOcr;
    }

    /**
     * 设置银行卡反面信息
     *
     * @param bankCardSecondOcr 银行卡反面信息
     */
    public void setBankCardSecondOcr(String bankCardSecondOcr) {
        this.bankCardSecondOcr = bankCardSecondOcr;
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