package com.supermoney.loan.api.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by admin on 2018-02-26.
 */
public class CalculationVo implements Serializable {
    /**
     * 还款金额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal repayMoney;
    /**
     * 利息
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private  BigDecimal interest;
    /**
     * 服务费
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private  BigDecimal  fee;
    /**
     * 到帐金额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private  BigDecimal gotMoney;
    /**
     * 本金
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private  BigDecimal principal;

    public BigDecimal getRepayMoney() {
        return repayMoney;
    }

    public void setRepayMoney(BigDecimal repayMoney) {
        this.repayMoney = repayMoney;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getGotMoney() {
        return gotMoney;
    }

    public void setGotMoney(BigDecimal gotMoney) {
        this.gotMoney = gotMoney;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }
}
