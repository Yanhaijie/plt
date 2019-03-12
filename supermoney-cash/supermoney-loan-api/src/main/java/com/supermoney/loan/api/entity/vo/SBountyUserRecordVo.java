package com.supermoney.loan.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bear on 2018/1/14.
 */
public class SBountyUserRecordVo  implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer userId;

    /**
     * 赏金任务编号
     */
    @Column(name = "bounty_id")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal bountyMoney;

    /**
     * 记录状态:0非下线、1下线
     */
    @Column(name = "agency_down")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer agencyDown;

    /**
     * 记录状态:0打开、1完成、2未完成 3已结算
     */
    @Column(name = "record_status")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer recordStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(name = "ads_ico")
    private  String adsIco;

    @Column(name = "ads_pic")
    private  String adsPic;

    /**
     * 回调事件名称
     */
    @Column(name = "cb_event_name")
    private String cbEventName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBountyId() {
        return bountyId;
    }

    public void setBountyId(Integer bountyId) {
        this.bountyId = bountyId;
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

    public Integer getAgencyDown() {
        return agencyDown;
    }

    public void setAgencyDown(Integer agencyDown) {
        this.agencyDown = agencyDown;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCbEventName() {
        return cbEventName;
    }

    public void setCbEventName(String cbEventName) {
        this.cbEventName = cbEventName;
    }

    public String getAdsIco() {
        return adsIco;
    }

    public void setAdsIco(String adsIco) {
        this.adsIco = adsIco;
    }

    public String getAdsPic() {
        return adsPic;
    }

    public void setAdsPic(String adsPic) {
        this.adsPic = adsPic;
    }
}
