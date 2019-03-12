package com.supermoney.open.platform.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_call_record")
public class SCallRecord {
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
     * 商户id
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 参数json
     */
    @Column(name = "param_json")
    private String paramJson;

    /**
     * 返回json
     */
    @Column(name = "respond_json")
    private String respondJson;

    /**
     * 返回关联id
     */
    @Column(name = "result_id")
    private Integer resultId;

    /**
     * 请求i状态（0 成功 、 1 失败）
     */
    @Column(name = "request_status")
    private Integer requestStatus;

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
     * 获取商户id
     *
     * @return merchant_id - 商户id
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户id
     *
     * @param merchantId 商户id
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取参数json
     *
     * @return param_json - 参数json
     */
    public String getParamJson() {
        return paramJson;
    }

    /**
     * 设置参数json
     *
     * @param paramJson 参数json
     */
    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }

    /**
     * 获取返回json
     *
     * @return respond_json - 返回json
     */
    public String getRespondJson() {
        return respondJson;
    }

    /**
     * 设置返回json
     *
     * @param respondJson 返回json
     */
    public void setRespondJson(String respondJson) {
        this.respondJson = respondJson;
    }

    /**
     * 获取返回关联id
     *
     * @return result_id - 返回关联id
     */
    public Integer getResultId() {
        return resultId;
    }

    /**
     * 设置返回关联id
     *
     * @param resultId 返回关联id
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    /**
     * 获取请求i状态（0 成功 、 1 失败）
     *
     * @return request_status - 请求i状态（0 成功 、 1 失败）
     */
    public Integer getRequestStatus() {
        return requestStatus;
    }

    /**
     * 设置请求i状态（0 成功 、 1 失败）
     *
     * @param requestStatus 请求i状态（0 成功 、 1 失败）
     */
    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }
}