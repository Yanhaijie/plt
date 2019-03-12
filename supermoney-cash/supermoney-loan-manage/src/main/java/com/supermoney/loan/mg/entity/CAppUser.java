package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "c_app_user")
public class CAppUser {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备唯一编码
     */
    @Column(name = "unique_id")
    private String uniqueId;

    /**
     * APP名称
     */
    private String username;

    /**
     * 注册渠道
     */
    private String channel;

    /**
     * 应用名称
     */
    @Column(name = "app_name")
    private String appName;

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
     * 获取设备唯一编码
     *
     * @return unique_id - 设备唯一编码
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 设置设备唯一编码
     *
     * @param uniqueId 设备唯一编码
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * 获取APP名称
     *
     * @return username - APP名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置APP名称
     *
     * @param username APP名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取注册渠道
     *
     * @return channel - 注册渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置注册渠道
     *
     * @param channel 注册渠道
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 获取应用名称
     *
     * @return app_name - 应用名称
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置应用名称
     *
     * @param appName 应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
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
}