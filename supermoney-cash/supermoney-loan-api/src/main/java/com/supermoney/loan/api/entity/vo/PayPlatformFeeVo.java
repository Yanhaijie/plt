package com.supermoney.loan.api.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付平台费率
 */
public class PayPlatformFeeVo implements Serializable {
    /**
     * 充值费用
     */
    private BigDecimal payMoney;
    /**
     * 充值费率
     */
   private  BigDecimal payRate;
    /**
     * 打款费用
     */
    private  BigDecimal disburseMoney;
    /**
     * 打款费率
     */
    private  BigDecimal disburseRate;

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getPayRate() {
        return payRate;
    }

    public void setPayRate(BigDecimal payRate) {
        this.payRate = payRate;
    }

    public BigDecimal getDisburseMoney() {
        return disburseMoney;
    }

    public void setDisburseMoney(BigDecimal disburseMoney) {
        this.disburseMoney = disburseMoney;
    }

    public BigDecimal getDisburseRate() {
        return disburseRate;
    }

    public void setDisburseRate(BigDecimal disburseRate) {
        this.disburseRate = disburseRate;
    }
}
