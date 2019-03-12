package com.supermoney.loan.api.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "s_app_log")
public class SAppLog {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 真实名称
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 证件号码
     */
    @Column(name = "log_content")
    private String logContent;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取真实名称
     *
     * @return log_type - 真实名称
     */
    public String getLogType() {
        return logType;
    }

    /**
     * 设置真实名称
     *
     * @param logType 真实名称
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * 获取证件号码
     *
     * @return log_content - 证件号码
     */
    public String getLogContent() {
        return logContent;
    }

    /**
     * 设置证件号码
     *
     * @param logContent 证件号码
     */
    public void setLogContent(String logContent) {
        this.logContent = logContent;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}