package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_activity_issue_record")
public class SActivityIssueRecord {
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
     * 活动名
     */
    @Column(name = "activity_name")
    private String activityName;

    /**
     * 领取类型（0 每日领取， 1 分享领取）
     */
    @Column(name = "issue_type")
    private Byte issueType;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取活动名
     *
     * @return activity_name - 活动名
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 设置活动名
     *
     * @param activityName 活动名
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * 获取领取类型（0 每日领取， 1 分享领取）
     *
     * @return issue_type - 领取类型（0 每日领取， 1 分享领取）
     */
    public Byte getIssueType() {
        return issueType;
    }

    /**
     * 设置领取类型（0 每日领取， 1 分享领取）
     *
     * @param issueType 领取类型（0 每日领取， 1 分享领取）
     */
    public void setIssueType(Byte issueType) {
        this.issueType = issueType;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}