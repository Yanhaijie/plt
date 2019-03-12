package com.supermoney.loan.market.entity.requestVo;

import java.util.Date;

public class DemandRequestVo {

    private Date start_time;

    private Date end_time;

    private Integer start_amount;

    private Integer end_amount;

    private String merchantId;

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getStart_amount() {
        return start_amount;
    }

    public void setStart_amount(Integer start_amount) {
        this.start_amount = start_amount;
    }

    public Integer getEnd_amount() {
        return end_amount;
    }

    public void setEnd_amount(Integer end_amount) {
        this.end_amount = end_amount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
