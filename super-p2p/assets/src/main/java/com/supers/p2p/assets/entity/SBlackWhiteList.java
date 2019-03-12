package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_black_white_list")
public class SBlackWhiteList {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 主公司编号
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 公司全称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 目标公司编号
     */
    @Column(name = "target_company_id")
    private Integer targetCompanyId;

    /**
     * 目标公司全称
     */
    @Column(name = "target_company_name")
    private String targetCompanyName;

    /**
     * 关联类型，0黑名单，1白名单
     */
    @Column(name = "relation_type")
    private Integer relationType;

    @Column(name = "owner_type")
    private Integer ownerType;

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
     * 获取主公司编号
     *
     * @return company_id - 主公司编号
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置主公司编号
     *
     * @param companyId 主公司编号
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取公司全称
     *
     * @return company_name - 公司全称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司全称
     *
     * @param companyName 公司全称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取目标公司编号
     *
     * @return target_company_id - 目标公司编号
     */
    public Integer getTargetCompanyId() {
        return targetCompanyId;
    }

    /**
     * 设置目标公司编号
     *
     * @param targetCompanyId 目标公司编号
     */
    public void setTargetCompanyId(Integer targetCompanyId) {
        this.targetCompanyId = targetCompanyId;
    }

    /**
     * 获取目标公司全称
     *
     * @return target_company_name - 目标公司全称
     */
    public String getTargetCompanyName() {
        return targetCompanyName;
    }

    /**
     * 设置目标公司全称
     *
     * @param targetCompanyName 目标公司全称
     */
    public void setTargetCompanyName(String targetCompanyName) {
        this.targetCompanyName = targetCompanyName;
    }

    /**
     * 获取关联类型，0黑名单，1白名单
     *
     * @return relation_type - 关联类型，0黑名单，1白名单
     */
    public Integer getRelationType() {
        return relationType;
    }

    /**
     * 设置关联类型，0黑名单，1白名单
     *
     * @param relationType 关联类型，0黑名单，1白名单
     */
    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
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

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }
}