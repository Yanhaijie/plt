package com.supermoney.loan.market.entity.respondVo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class DemandRespondVo {
    //需求单号
    private String need_id;

    //贷超产品名称
    private String merchant_product_code;

    //借款人
    private String person;

    //用户手机
    private String user_mobile;

    //下单时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    //借款额度
    private BigDecimal amount;

    //借款利率
    private BigDecimal rate;

    //服务费利率
    private BigDecimal fee_rate;

    //利率单位【0:日1:月2:年】
    private Integer rate_unit;

    //还款时长
    private Integer duration;

    //时长单位【0:日1:月2:年】
    private Integer duration_unit;

    //借款理由
    private String reason;

    //还款方式【0: 服务费利息前置 1：服务费前置利息后置】
    private Integer repay_method;

    //身份证号
    private String id_number;

    public String getNeed_id() {
        return need_id;
    }

    public void setNeed_id(String need_id) {
        this.need_id = need_id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getFee_rate() {
        return fee_rate;
    }

    public void setFee_rate(BigDecimal fee_rate) {
        this.fee_rate = fee_rate;
    }

    public Integer getRate_unit() {
        return rate_unit;
    }

    public void setRate_unit(Integer rate_unit) {
        this.rate_unit = rate_unit;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration_unit() {
        return duration_unit;
    }

    public void setDuration_unit(Integer duration_unit) {
        this.duration_unit = duration_unit;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getRepay_method() {
        return repay_method;
    }

    public void setRepay_method(Integer repay_method) {
        this.repay_method = repay_method;
    }

    public String getMerchant_product_code() {
        return merchant_product_code;
    }

    public void setMerchant_product_code(String merchant_product_code) {
        this.merchant_product_code = merchant_product_code;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }
}
