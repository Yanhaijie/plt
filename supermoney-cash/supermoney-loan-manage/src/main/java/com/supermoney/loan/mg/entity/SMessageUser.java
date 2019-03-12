package com.supermoney.loan.mg.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "s_message_user")
public class SMessageUser {
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
     * 消息编号
     */
    @Column(name = "message_id")
    private Integer messageId;

    /**
     * 消息类别编号
     */
    @Column(name = "message_type_id")
    private Integer messageTypeId;

    /**
     * 阅读状态：（0：未读、1：已读）
     */
    @Column(name = "read_status")
    private Integer readStatus;

    /**
     * 删除状态：（0：未删除、1：已删除）
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

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
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 消息名称
     */
    @Column(name = "message_title")
    private String messageTitle;

    @Column(name = "message_content")
    private String messageContent;


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
     * 获取消息编号
     *
     * @return message_id - 消息编号
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * 设置消息编号
     *
     * @param messageId 消息编号
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取消息类别编号
     *
     * @return message_type_id - 消息类别编号
     */
    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    /**
     * 设置消息类别编号
     *
     * @param messageTypeId 消息类别编号
     */
    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    /**
     * 获取阅读状态：（0：未读、1：已读）
     *
     * @return read_status - 阅读状态：（0：未读、1：已读）
     */
    public Integer getReadStatus() {
        return readStatus;
    }

    /**
     * 设置阅读状态：（0：未读、1：已读）
     *
     * @param readStatus 阅读状态：（0：未读、1：已读）
     */
    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    /**
     * 获取删除状态：（0：未删除、1：已删除）
     *
     * @return delete_status - 删除状态：（0：未删除、1：已删除）
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置删除状态：（0：未删除、1：已删除）
     *
     * @param deleteStatus 删除状态：（0：未删除、1：已删除）
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}