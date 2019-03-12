package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_activity_white_list")
public class SActivityWhiteList {
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
     * 操作人
     */
    private String opt;

    /**
     * 活动名
     */
    @Column(name = "activity_name")
    private String activityName;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 礼物id
     */
    @Column(name = "gift_id")
    private Integer giftId;

    /**
     * 抽奖次数
     */
    @Column(name = "lottery_count")
    private Integer lotteryCount;

    /**
     * 抽奖记录id
     */
    @Column(name = "lottery_record_id")
    private Integer lotteryRecordId;

    /**
     * 是否发放过（0  未发放，1 已发放）
     */
    @Column(name = "is_sent")
    private Byte isSent;

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
     * 获取礼物id
     *
     * @return gift_id - 礼物id
     */
    public Integer getGiftId() {
        return giftId;
    }

    /**
     * 设置礼物id
     *
     * @param giftId 礼物id
     */
    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    /**
     * 获取抽奖次数
     *
     * @return lottery_count - 抽奖次数
     */
    public Integer getLotteryCount() {
        return lotteryCount;
    }

    /**
     * 设置抽奖次数
     *
     * @param lotteryCount 抽奖次数
     */
    public void setLotteryCount(Integer lotteryCount) {
        this.lotteryCount = lotteryCount;
    }

    /**
     * 获取抽奖记录id
     *
     * @return lottery_record_id - 抽奖记录id
     */
    public Integer getLotteryRecordId() {
        return lotteryRecordId;
    }

    /**
     * 设置抽奖记录id
     *
     * @param lotteryRecordId 抽奖记录id
     */
    public void setLotteryRecordId(Integer lotteryRecordId) {
        this.lotteryRecordId = lotteryRecordId;
    }

    /**
     * 获取是否发放过（0  未发放，1 已发放）
     *
     * @return is_sent - 是否发放过（0  未发放，1 已发放）
     */
    public Byte getIsSent() {
        return isSent;
    }

    /**
     * 设置是否发放过（0  未发放，1 已发放）
     *
     * @param isSent 是否发放过（0  未发放，1 已发放）
     */
    public void setIsSent(Byte isSent) {
        this.isSent = isSent;
    }
}