package com.supermoney.loan.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_bounty_record")
public class SBountyRecord {
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
     * 赏金任务编号
     */
    @Column(name = "bounty_id")
    private Integer bountyId;

    /**
     * 任务名称
     */
    @Column(name = "bounty_name")
    private String bountyName;

    /**
     * 任务赏金
     */
    @Column(name = "bounty_money")
    private BigDecimal bountyMoney;

    /**
     * 广告地址
     */
    @Column(name = "ads_id")
    private String adsId;

    /**
     * 记录状态:0非下线、1下线
     */
    @Column(name = "agency_down")
    private Integer agencyDown;

    /**
     * 点击id
     */
    @Column(name = "click_id")
    private String clickId;

    /**
     * 安卓设备ID
     */
    private String gaid;

    /**
     * IOS设备ID
     */
    private String idfa;

    /**
     * 任务完成IP
     */
    private String ip;

    /**
     * 回调事件名称
     */
    @Column(name = "cb_event_name")
    private String cbEventName;

    /**
     * 事件类型
     */
    @Column(name = "event_type")
    private Integer eventType;

    /**
     * 记录状态:0打开、1完成、2未完成 3已结算
     */
    @Column(name = "record_status")
    private Integer recordStatus;

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
     * 使用方式
     */
    @Column(name = "use_dsc")
    private String useDsc;

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
     * 获取赏金任务编号
     *
     * @return bounty_id - 赏金任务编号
     */
    public Integer getBountyId() {
        return bountyId;
    }

    /**
     * 设置赏金任务编号
     *
     * @param bountyId 赏金任务编号
     */
    public void setBountyId(Integer bountyId) {
        this.bountyId = bountyId;
    }

    /**
     * 获取广告地址
     *
     * @return ads_id - 广告地址
     */
    public String getAdsId() {
        return adsId;
    }

    /**
     * 设置广告地址
     *
     * @param adsId 广告地址
     */
    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }

    /**
     * 获取记录状态:0非下线、1下线
     *
     * @return agency_down - 记录状态:0非下线、1下线
     */
    public Integer getAgencyDown() {
        return agencyDown;
    }

    /**
     * 设置记录状态:0非下线、1下线
     *
     * @param agencyDown 记录状态:0非下线、1下线
     */
    public void setAgencyDown(Integer agencyDown) {
        this.agencyDown = agencyDown;
    }

    /**
     * 获取点击id
     *
     * @return click_id - 点击id
     */
    public String getClickId() {
        return clickId;
    }

    /**
     * 设置点击id
     *
     * @param clickId 点击id
     */
    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    /**
     * 获取安卓设备ID
     *
     * @return gaid - 安卓设备ID
     */
    public String getGaid() {
        return gaid;
    }

    /**
     * 设置安卓设备ID
     *
     * @param gaid 安卓设备ID
     */
    public void setGaid(String gaid) {
        this.gaid = gaid;
    }

    /**
     * 获取IOS设备ID
     *
     * @return idfa - IOS设备ID
     */
    public String getIdfa() {
        return idfa;
    }

    /**
     * 设置IOS设备ID
     *
     * @param idfa IOS设备ID
     */
    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    /**
     * 获取任务完成IP
     *
     * @return ip - 任务完成IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置任务完成IP
     *
     * @param ip 任务完成IP
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取回调事件名称
     *
     * @return cb_event_name - 回调事件名称
     */
    public String getCbEventName() {
        return cbEventName;
    }

    /**
     * 设置回调事件名称
     *
     * @param cbEventName 回调事件名称
     */
    public void setCbEventName(String cbEventName) {
        this.cbEventName = cbEventName;
    }

    /**
     * 获取事件类型
     *
     * @return event_type - 事件类型
     */
    public Integer getEventType() {
        return eventType;
    }

    /**
     * 设置事件类型
     *
     * @param eventType 事件类型
     */
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    /**
     * 获取记录状态:0打开、1完成、2未完成 3已结算
     *
     * @return record_status - 记录状态:0打开、1完成、2未完成 3已结算
     */
    public Integer getRecordStatus() {
        return recordStatus;
    }

    /**
     * 设置记录状态:0打开、1完成、2未完成 3已结算
     *
     * @param recordStatus 记录状态:0打开、1完成、2未完成 3已结算
     */
    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
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

    public String getBountyName() {
        return bountyName;
    }

    public void setBountyName(String bountyName) {
        this.bountyName = bountyName;
    }

    public BigDecimal getBountyMoney() {
        return bountyMoney;
    }

    public void setBountyMoney(BigDecimal bountyMoney) {
        this.bountyMoney = bountyMoney;
    }

    public String getUseDsc() {
        return useDsc;
    }

    public void setUseDsc(String useDsc) {
        this.useDsc = useDsc;
    }
}