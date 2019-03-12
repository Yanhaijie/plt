package com.supermoney.open.platform.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_param")
public class SParam {
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
     * 接口id
     */
    @Column(name = "interface_id")
    private Integer interfaceId;

    /**
     * 参数名
     */
    @Column(name = "param_name")
    private String paramName;

    /**
     * 参数属性(1 int、2 long、 3 string 、4 BigDecimal、5 double、6 array、7 map)
     */
    @Column(name = "param_attribute_type")
    private Integer paramAttributeType;

    /**
     * 参数描述
     */
    @Column(name = "param_desc")
    private String paramDesc;

    /**
     * 参数类型（1 请求参数、2 返回参数）
     */
    @Column(name = "param_type")
    private Integer paramType;

    /**
     * 是否必填（0 否 ，1 是）
     */
    @Column(name = "is_required")
    private Integer isRequired;

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
     * 获取接口id
     *
     * @return interface_id - 接口id
     */
    public Integer getInterfaceId() {
        return interfaceId;
    }

    /**
     * 设置接口id
     *
     * @param interfaceId 接口id
     */
    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    /**
     * 获取参数名
     *
     * @return param_name - 参数名
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置参数名
     *
     * @param paramName 参数名
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 获取参数属性(1 int、2 long、 3 string 、4 BigDecimal、5 double、6 array、7 map)
     *
     * @return param_attribute_type - 参数属性(1 int、2 long、 3 string 、4 BigDecimal、5 double、6 array、7 map)
     */
    public Integer getParamAttributeType() {
        return paramAttributeType;
    }

    /**
     * 设置参数属性(1 int、2 long、 3 string 、4 BigDecimal、5 double、6 array、7 map)
     *
     * @param paramAttributeType 参数属性(1 int、2 long、 3 string 、4 BigDecimal、5 double、6 array、7 map)
     */
    public void setParamAttributeType(Integer paramAttributeType) {
        this.paramAttributeType = paramAttributeType;
    }

    /**
     * 获取参数描述
     *
     * @return param_desc - 参数描述
     */
    public String getParamDesc() {
        return paramDesc;
    }

    /**
     * 设置参数描述
     *
     * @param paramDesc 参数描述
     */
    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    /**
     * 获取参数类型（1 请求参数、2 返回参数）
     *
     * @return param_type - 参数类型（1 请求参数、2 返回参数）
     */
    public Integer getParamType() {
        return paramType;
    }

    /**
     * 设置参数类型（1 请求参数、2 返回参数）
     *
     * @param paramType 参数类型（1 请求参数、2 返回参数）
     */
    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    /**
     * 获取是否必填（0 否 ，1 是）
     *
     * @return is_required - 是否必填（0 否 ，1 是）
     */
    public Integer getIsRequired() {
        return isRequired;
    }

    /**
     * 设置是否必填（0 否 ，1 是）
     *
     * @param isRequired 是否必填（0 否 ，1 是）
     */
    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }
}