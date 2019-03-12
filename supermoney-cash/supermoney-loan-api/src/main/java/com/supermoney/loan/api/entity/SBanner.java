package com.supermoney.loan.api.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_banner")
public class SBanner {
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
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String picture;

    /**
     * 优先级，值越小优先级越高
     */
    private Integer priority;

    /**
     * 上架状态（0 下架， 1 上架）
     */
    private Byte shelves;

    /**
     * 是否定时
     */
    @Column(name = "have_time_limit")
    private Byte haveTimeLimit;

    /**
     * 开始展示时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束展示时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 广告类型（0 链接跳转， 1 赏金任务 ）
     */
    @Column(name = "ads_type")
    private Byte adsType;

    /**
     * 最大点击量
     */
    @Column(name = "max_click_count")
    private Integer maxClickCount;

    /**
     * 链接
     */
    private String link;

    /**
     * 业务id
     */
    @Column(name = "buss_id")
    private Integer bussId;

    /**
     * 是否需要登录(0 不需要，1 需要)
     */
    @Column(name = "need_login")
    private Byte needLogin;

    /**
     * 投放位置（homepage 首页 ，）
     */
    @Column(name = "put_position")
    private String putPosition;

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
     * 获取图片
     *
     * @return picture - 图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置图片
     *
     * @param picture 图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取优先级，值越小优先级越高
     *
     * @return priority - 优先级，值越小优先级越高
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级，值越小优先级越高
     *
     * @param priority 优先级，值越小优先级越高
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取上架状态（0 下架， 1 上架）
     *
     * @return shelves - 上架状态（0 下架， 1 上架）
     */
    public Byte getShelves() {
        return shelves;
    }

    /**
     * 设置上架状态（0 下架， 1 上架）
     *
     * @param shelves 上架状态（0 下架， 1 上架）
     */
    public void setShelves(Byte shelves) {
        this.shelves = shelves;
    }

    /**
     * 获取是否定时
     *
     * @return have_time_limit - 是否定时
     */
    public Byte getHaveTimeLimit() {
        return haveTimeLimit;
    }

    /**
     * 设置是否定时
     *
     * @param haveTimeLimit 是否定时
     */
    public void setHaveTimeLimit(Byte haveTimeLimit) {
        this.haveTimeLimit = haveTimeLimit;
    }

    /**
     * 获取开始展示时间
     *
     * @return begin_time - 开始展示时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置开始展示时间
     *
     * @param beginTime 开始展示时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取结束展示时间
     *
     * @return end_time - 结束展示时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束展示时间
     *
     * @param endTime 结束展示时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取广告类型（0 链接跳转， 1 赏金任务 ）
     *
     * @return ads_type - 广告类型（0 链接跳转， 1 赏金任务 ）
     */
    public Byte getAdsType() {
        return adsType;
    }

    /**
     * 设置广告类型（0 链接跳转， 1 赏金任务 ）
     *
     * @param adsType 广告类型（0 链接跳转， 1 赏金任务 ）
     */
    public void setAdsType(Byte adsType) {
        this.adsType = adsType;
    }

    /**
     * 获取最大点击量
     *
     * @return max_click_count - 最大点击量
     */
    public Integer getMaxClickCount() {
        return maxClickCount;
    }

    /**
     * 设置最大点击量
     *
     * @param maxClickCount 最大点击量
     */
    public void setMaxClickCount(Integer maxClickCount) {
        this.maxClickCount = maxClickCount;
    }

    /**
     * 获取链接
     *
     * @return link - 链接
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置链接
     *
     * @param link 链接
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 获取业务id
     *
     * @return buss_id - 业务id
     */
    public Integer getBussId() {
        return bussId;
    }

    /**
     * 设置业务id
     *
     * @param bussId 业务id
     */
    public void setBussId(Integer bussId) {
        this.bussId = bussId;
    }

    /**
     * 获取是否需要登录(0 不需要，1 需要)
     *
     * @return need_login - 是否需要登录(0 不需要，1 需要)
     */
    public Byte getNeedLogin() {
        return needLogin;
    }

    /**
     * 设置是否需要登录(0 不需要，1 需要)
     *
     * @param needLogin 是否需要登录(0 不需要，1 需要)
     */
    public void setNeedLogin(Byte needLogin) {
        this.needLogin = needLogin;
    }

    /**
     * 获取投放位置（homepage 首页 ，）
     *
     * @return put_position - 投放位置（homepage 首页 ，）
     */
    public String getPutPosition() {
        return putPosition;
    }

    /**
     * 设置投放位置（homepage 首页 ，）
     *
     * @param putPosition 投放位置（homepage 首页 ，）
     */
    public void setPutPosition(String putPosition) {
        this.putPosition = putPosition;
    }
}