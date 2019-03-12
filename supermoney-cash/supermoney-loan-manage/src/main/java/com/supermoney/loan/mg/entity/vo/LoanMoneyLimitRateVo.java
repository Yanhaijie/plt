package com.supermoney.loan.mg.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanMoneyLimitRateVo implements Serializable {
    private BigDecimal startVal;

    private BigDecimal endVal;

    private  BigDecimal rate;


    public BigDecimal getStartVal() {
        return startVal;
    }

    public void setStartVal(BigDecimal startVal) {
        this.startVal = startVal;
    }

    public BigDecimal getEndVal() {
        return endVal;
    }

    public void setEndVal(BigDecimal endVal) {
        this.endVal = endVal;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
