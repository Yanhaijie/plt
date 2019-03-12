package com.supermoney.loan.api.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_cert_record")
public class SCertRecord {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0 身份认证, 1 活体认证 , 2 人证校验
     */
    @Column(name = "cert_type")
    private Byte certType;

    /**
     * 认证状态 0 失败   1 成功
     */
    @Column(name = "cert_status")
    private Byte certStatus;

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
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取0 身份认证, 1 活体认证 , 2 人证校验
     *
     * @return cert_type - 0 身份认证, 1 活体认证 , 2 人证校验
     */
    public Byte getCertType() {
        return certType;
    }

    /**
     * 设置0 身份认证, 1 活体认证 , 2 人证校验
     *
     * @param certType 0 身份认证, 1 活体认证 , 2 人证校验
     */
    public void setCertType(Byte certType) {
        this.certType = certType;
    }

    public Byte getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(Byte certStatus) {
        this.certStatus = certStatus;
    }
}