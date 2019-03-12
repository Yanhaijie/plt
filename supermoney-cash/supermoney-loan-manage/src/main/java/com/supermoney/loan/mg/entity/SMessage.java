package com.supermoney.loan.mg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "s_message")
public class SMessage {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类别图标
     */
    private String img;

    /**
     * 链接
     */
    private String url;

    /**
     * 父级id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 消息类别id
     */
    @Column(name = "message_type_id")
    private Integer messageTypeId;

    /**
     * 状态：0使用、1停止
     */
    @Column(name = "use_status")
    private Integer useStatus;

    /**
     * 状态描述
     */
    @Column(name = "status_describe")
    private String statusDescribe;

    /**
     * 定时推送（0：定时性推送，1：周期性推送）
     */
    @Column(name = "push_schedule")
    private Integer pushSchedule;

    /**
     * 推送时间
     */
    @Column(name = "push_time")
    private Date pushTime;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 操作人
     */
    private String opt;

    /**
     * 内容
     */
    private String content;

    /**
     * 推送群体sql
     */
    @Column(name = "target_sql")
    private String targetSql;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取类别图标
     *
     * @return img - 类别图标
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置类别图标
     *
     * @param img 类别图标
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取链接
     *
     * @return url - 链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接
     *
     * @param url 链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取父级id
     *
     * @return parent_id - 父级id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级id
     *
     * @param parentId 父级id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取消息类别id
     *
     * @return message_type_id - 消息类别id
     */
    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    /**
     * 设置消息类别id
     *
     * @param messageTypeId 消息类别id
     */
    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    /**
     * 获取状态：0使用、1停止
     *
     * @return use_status - 状态：0使用、1停止
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**
     * 设置状态：0使用、1停止
     *
     * @param useStatus 状态：0使用、1停止
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * 获取状态描述
     *
     * @return status_describe - 状态描述
     */
    public String getStatusDescribe() {
        return statusDescribe;
    }

    /**
     * 设置状态描述
     *
     * @param statusDescribe 状态描述
     */
    public void setStatusDescribe(String statusDescribe) {
        this.statusDescribe = statusDescribe;
    }

    /**
     * 获取定时推送（0：定时性推送，1：周期性推送）
     *
     * @return push_schedule - 定时推送（0：定时性推送，1：周期性推送）
     */
    public Integer getPushSchedule() {
        return pushSchedule;
    }

    /**
     * 设置定时推送（0：定时性推送，1：周期性推送）
     *
     * @param pushSchedule 定时推送（0：定时性推送，1：周期性推送）
     */
    public void setPushSchedule(Integer pushSchedule) {
        this.pushSchedule = pushSchedule;
    }

    /**
     * 获取推送时间
     *
     * @return push_time - 推送时间
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * 设置推送时间
     *
     * @param pushTime 推送时间
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取推送群体sql
     *
     * @return target_sql - 推送群体sql
     */
    public String getTargetSql() {
        return targetSql;
    }

    /**
     * 设置推送群体sql
     *
     * @param targetSql 推送群体sql
     */
    public void setTargetSql(String targetSql) {
        this.targetSql = targetSql;
    }
}