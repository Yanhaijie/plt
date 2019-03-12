package com.supermoney.loan.market.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_nik_call_record")
public class SNikCallRecord {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 商户ID
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * NIK ID
     */
    @Column(name = "nik_id")
    private String nikId;

    /**
     * 调用状态（0 成功， 1 失败）
     */
    @Column(name = "call_status")
    private Integer callStatus;

    /**
     * NIK
     */
    private String nik;

    /**
     * 来源（0 advance 1 nik）
     */
    private Integer sourceTag;

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
     * 获取商户ID
     *
     * @return merchant_id - 商户ID
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户ID
     *
     * @param merchantId 商户ID
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取NIK ID
     *
     * @return nik_id - NIK ID
     */
    public String getNikId() {
        return nikId;
    }

    /**
     * 设置NIK ID
     *
     * @param nikId NIK ID
     */
    public void setNikId(String nikId) {
        this.nikId = nikId;
    }

    /**
     * 获取调用状态（0 成功， 1 失败）
     *
     * @return call_status - 调用状态（0 成功， 1 失败）
     */
    public Integer getCallStatus() {
        return callStatus;
    }

    /**
     * 设置调用状态（0 成功， 1 失败）
     *
     * @param callStatus 调用状态（0 成功， 1 失败）
     */
    public void setCallStatus(Integer callStatus) {
        this.callStatus = callStatus;
    }

    /**
     * 获取NIK
     *
     * @return nik - NIK
     */
    public String getNik() {
        return nik;
    }

    /**
     * 设置NIK
     *
     * @param nik NIK
     */
    public void setNik(String nik) {
        this.nik = nik;
    }

    public Integer getSourceTag() {
        return sourceTag;
    }

    public void setSourceTag(Integer sourceTag) {
        this.sourceTag = sourceTag;
    }
}