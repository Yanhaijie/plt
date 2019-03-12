package com.supermoney.loan.market.entity.requestVo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderRequestVo {
    //订单编号
    private  String need_id;

    //订单状态[1: 审核通过2: 审核失败3: 待还款4:放款失败5:预期 6:完成 100：已存在订单]
    private Integer status;

    //借款金额
    private BigDecimal loan_amount;

    //利息费
    private BigDecimal interest_amount;

    //逾期费
    private BigDecimal overdue_amount;

    //回款账号ID
    private String payback_account_id;

    //逾期天数
    private Integer overdue_day;

    //服务费
    private BigDecimal fee_amount;

    //到账金额
    private BigDecimal got_amount;

    //待还总额
    private BigDecimal wait_repay_amount;

    //已还总额
    private BigDecimal repaymented_amount;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(BigDecimal loan_amount) {
        this.loan_amount = loan_amount;
    }

    public BigDecimal getInterest_amount() {
        return interest_amount;
    }

    public void setInterest_amount(BigDecimal interest_amount) {
        this.interest_amount = interest_amount;
    }

    public BigDecimal getOverdue_amount() {
        return overdue_amount;
    }

    public void setOverdue_amount(BigDecimal overdue_amount) {
        this.overdue_amount = overdue_amount;
    }

    public Integer getOverdue_day() {
        return overdue_day;
    }

    public void setOverdue_day(Integer overdue_day) {
        this.overdue_day = overdue_day;
    }

    public BigDecimal getFee_amount() {
        return fee_amount;
    }

    public void setFee_amount(BigDecimal fee_amount) {
        this.fee_amount = fee_amount;
    }

    public BigDecimal getGot_amount() {
        return got_amount;
    }

    public void setGot_amount(BigDecimal got_amount) {
        this.got_amount = got_amount;
    }

    public BigDecimal getWait_repay_amount() {
        return wait_repay_amount;
    }

    public void setWait_repay_amount(BigDecimal wait_repay_amount) {
        this.wait_repay_amount = wait_repay_amount;
    }

    public BigDecimal getRepaymented_amount() {
        return repaymented_amount;
    }

    public void setRepaymented_amount(BigDecimal repaymented_amount) {
        this.repaymented_amount = repaymented_amount;
    }

    public String getPayback_account_id() {
        return payback_account_id;
    }

    public void setPayback_account_id(String payback_account_id) {
        this.payback_account_id = payback_account_id;
    }

    public String getNeed_id() {
        return need_id;
    }

    public void setNeed_id(String need_id) {
        this.need_id = need_id;
    }
}
