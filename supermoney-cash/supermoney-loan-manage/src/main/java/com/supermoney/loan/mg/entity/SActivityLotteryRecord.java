package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_activity_lottery_record")
public class SActivityLotteryRecord {
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
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 是否中奖（0 未中奖， 1 中奖）
     */
    @Column(name = "is_winning")
    private Byte isWinning;

    /**
     * 中奖礼物id
     */
    @Column(name = "gift_id")
    private Integer giftId;

    /**
     * 礼物名
     */
    @Column(name = "gift_name")
    private String giftName;

    /**
     * 中奖礼物数量
     */
    @Column(name = "gift_count")
    private Integer giftCount;

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
     * 获取是否中奖（0 未中奖， 1 中奖）
     *
     * @return is_winning - 是否中奖（0 未中奖， 1 中奖）
     */
    public Byte getIsWinning() {
        return isWinning;
    }

    /**
     * 设置是否中奖（0 未中奖， 1 中奖）
     *
     * @param isWinning 是否中奖（0 未中奖， 1 中奖）
     */
    public void setIsWinning(Byte isWinning) {
        this.isWinning = isWinning;
    }

    /**
     * 获取中奖礼物id
     *
     * @return gift_id - 中奖礼物id
     */
    public Integer getGiftId() {
        return giftId;
    }

    /**
     * 设置中奖礼物id
     *
     * @param giftId 中奖礼物id
     */
    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Integer getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(Integer giftCount) {
        this.giftCount = giftCount;
    }
}