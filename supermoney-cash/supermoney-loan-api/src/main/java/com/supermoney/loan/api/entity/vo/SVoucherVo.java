package com.supermoney.loan.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * 抵用券信息
 * Created by bear on 2018/1/14.
 */
@ApiModel(value = "SVoucherVo", description = "抵用券信息")
public class SVoucherVo   implements Serializable {

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 奖券编号
     */
    @ApiModelProperty(value = "奖券编号")
    @Column(name = "voucher_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer voucherId;

    /**
     * 奖券名称
     */
    @ApiModelProperty(value = "奖券名称")
    @Column(name = "voucher_name")
    private String voucherName;

    /**
     * 奖券介绍
     */
    @ApiModelProperty(value = "奖券介绍")
    @Column(name = "voucher_dsc")
    private String voucherDsc;
    /**
     * 类型 0单次翻倍 1项目翻倍 2期间翻倍 3随机红包
     */
    @ApiModelProperty(value = "类型 0单次翻倍 1项目翻倍 2期间翻倍 3随机红包")
    @Column(name="voucher_type")
    @JsonSerialize(using = ToStringSerializer.class)
    private  Integer voucherType;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称")
    @Column(name="voucher_type_name")
    private  String voucherTypeName;

    /**
     * 有效期-开始
     */
    @ApiModelProperty(value = "有效期-开始")
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 有效期-结束
     */
    @ApiModelProperty(value = "有效期-结束")
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /**
     *可使用券的任务ID集合
     */
    @ApiModelProperty(value = "可使用券的任务ID集合")
    @Column(name = "bounty_ids")
    private  String bountyIds;
    /**
     *
     */
    @ApiModelProperty(value = "是否已查阅")
    @Column(name="viewed")
    private  Integer viewed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getVoucherDsc() {
        return voucherDsc;
    }

    public void setVoucherDsc(String voucherDsc) {
        this.voucherDsc = voucherDsc;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Integer voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherTypeName() {
        return voucherTypeName;
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName;
    }

    public String getBountyIds() {
        return bountyIds;
    }

    public void setBountyIds(String bountyIds) {
        this.bountyIds = bountyIds;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }
}

