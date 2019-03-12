package com.supermoney.loan.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_activity_gift")
public class SActivityGift {
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
     * 礼物名字
     */
    @Column(name = "gift_name")
    private String giftName;

    /**
     * 使用方式（0 移动端抽奖，1  后台抽奖，2 下架）
     */
    @Column(name = "use_way")
    private Byte useWay;

    /**
     * 类型（0 金币 ， 1 话费 ， 3 实体礼物）
     */
    @Column(name = "gift_type")
    private Byte giftType;

    /**
     * 礼物数量
     */
    @Column(name = "gift_count")
    private Integer giftCount;

    /**
     * 礼物图片
     */
    @Column(name = "gift_img")
    private String giftImg;

    /**
     * 礼物描述
     */
    @Column(name = "gift_desc")
    private String giftDesc;

    /**
     * 概率
     */
    private BigDecimal probability;

    /**
     * daily_count
     */
    @Column(name = "daily_count")
    private Integer dailyCount;

    /**
     * remain_count
     */
    @Column(name = "remain_count")
    private Integer remainCount;

    /**
     * 最大数量
     */
    @Column(name = "max_count")
    private Integer maxCount;

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
     * 获取礼物名字
     *
     * @return gift_name - 礼物名字
     */
    public String getGiftName() {
        return giftName;
    }

    /**
     * 设置礼物名字
     *
     * @param giftName 礼物名字
     */
    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    /**
     * 获取使用方式（0 移动端抽奖，1  后台抽奖，2 下架）
     *
     * @return use_way - 使用方式（0 移动端抽奖，1  后台抽奖，2 下架）
     */
    public Byte getUseWay() {
        return useWay;
    }

    /**
     * 设置使用方式（0 移动端抽奖，1  后台抽奖，2 下架）
     *
     * @param useWay 使用方式（0 移动端抽奖，1  后台抽奖，2 下架）
     */
    public void setUseWay(Byte useWay) {
        this.useWay = useWay;
    }

    /**
     * 获取类型（0 金币 ， 1 话费 ， 3 实体礼物）
     *
     * @return gift_type - 类型（0 金币 ， 1 话费 ， 3 实体礼物）
     */
    public Byte getGiftType() {
        return giftType;
    }

    /**
     * 设置类型（0 金币 ， 1 话费 ， 3 实体礼物）
     *
     * @param giftType 类型（0 金币 ， 1 话费 ， 3 实体礼物）
     */
    public void setGiftType(Byte giftType) {
        this.giftType = giftType;
    }

    /**
     * 获取礼物数量
     *
     * @return gift_count - 礼物数量
     */
    public Integer getGiftCount() {
        return giftCount;
    }

    /**
     * 设置礼物数量
     *
     * @param giftCount 礼物数量
     */
    public void setGiftCount(Integer giftCount) {
        this.giftCount = giftCount;
    }

    /**
     * 获取礼物图片
     *
     * @return gift_img - 礼物图片
     */
    public String getGiftImg() {
        return giftImg;
    }

    /**
     * 设置礼物图片
     *
     * @param giftImg 礼物图片
     */
    public void setGiftImg(String giftImg) {
        this.giftImg = giftImg;
    }

    /**
     * 获取礼物描述
     *
     * @return gift_desc - 礼物描述
     */
    public String getGiftDesc() {
        return giftDesc;
    }

    /**
     * 设置礼物描述
     *
     * @param giftDesc 礼物描述
     */
    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    /**
     * 获取概率
     *
     * @return probability - 概率
     */
    public BigDecimal getProbability() {
        return probability;
    }

    /**
     * 设置概率
     *
     * @param probability 概率
     */
    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    /**
     * 获取daily_count
     *
     * @return daily_count - daily_count
     */
    public Integer getDailyCount() {
        return dailyCount;
    }

    /**
     * 设置daily_count
     *
     * @param dailyCount daily_count
     */
    public void setDailyCount(Integer dailyCount) {
        this.dailyCount = dailyCount;
    }

    /**
     * 获取remain_count
     *
     * @return remain_count - remain_count
     */
    public Integer getRemainCount() {
        return remainCount;
    }

    /**
     * 设置remain_count
     *
     * @param remainCount remain_count
     */
    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
    }

    /**
     * 获取最大数量
     *
     * @return max_count - 最大数量
     */
    public Integer getMaxCount() {
        return maxCount;
    }

    /**
     * 设置最大数量
     *
     * @param maxCount 最大数量
     */
    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }
}