package com.supermoney.loan.api.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_buss_error")
public class SBussError {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 业务类型 0借款回款业务
     */
    @Column(name = "buss_type")
    private Integer bussType;

    /**
     * 相关ID
     */
    @Column(name = "relate_id")
    private String relateId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 错误参数记录
     */
    private String json;

    /**
     * 状态:0未处理 1已处理
     */
    @Column(name = "error_status")
    private Integer errorStatus;

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
     * 获取业务类型 0借款回款业务
     *
     * @return buss_type - 业务类型 0借款回款业务
     */
    public Integer getBussType() {
        return bussType;
    }

    /**
     * 设置业务类型 0借款回款业务
     *
     * @param bussType 业务类型 0借款回款业务
     */
    public void setBussType(Integer bussType) {
        this.bussType = bussType;
    }

    /**
     * 获取相关ID
     *
     * @return relate_id - 相关ID
     */
    public String getRelateId() {
        return relateId;
    }

    /**
     * 设置相关ID
     *
     * @param relateId 相关ID
     */
    public void setRelateId(String relateId) {
        this.relateId = relateId;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取错误参数记录
     *
     * @return json - 错误参数记录
     */
    public String getJson() {
        return json;
    }

    /**
     * 设置错误参数记录
     *
     * @param json 错误参数记录
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * 获取状态:0未处理 1已处理
     *
     * @return error_status - 状态:0未处理 1已处理
     */
    public Integer getErrorStatus() {
        return errorStatus;
    }

    /**
     * 设置状态:0未处理 1已处理
     *
     * @param errorStatus 状态:0未处理 1已处理
     */
    public void setErrorStatus(Integer errorStatus) {
        this.errorStatus = errorStatus;
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
}