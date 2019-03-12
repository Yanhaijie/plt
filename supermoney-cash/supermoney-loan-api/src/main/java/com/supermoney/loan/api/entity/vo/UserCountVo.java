package com.supermoney.loan.api.entity.vo;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

public class UserCountVo implements Serializable {
    /**
     * 可用金额
     */
    private BigDecimal availableAmount;
    /**
     * 冻结金额
     */
    private  BigDecimal freezeAmount;
    /**
     * 已提现金额
     */
    private  BigDecimal cashAmount;
    /**
     * 所有金额=可用+冻结+已提现
     */
    private  BigDecimal allAmount;

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(BigDecimal allAmount) {
        this.allAmount = allAmount;
    }
}
