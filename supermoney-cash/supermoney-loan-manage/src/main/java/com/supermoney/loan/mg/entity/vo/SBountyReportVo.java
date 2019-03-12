package com.supermoney.loan.mg.entity.vo;

import java.io.Serializable;

/**
 * 赏金总数据统计
 * Created by taoxiaojie on 2019/2/25.
 */
public class SBountyReportVo implements Serializable {
    private String bountyTotalAmount;//赏金总金额，单位美元
    private String selfBountyTotalAmount;//赏金总金额，单位美元
    private String secBountyTotalAmount;//赏金总提成，单位美元
    private String retailTotalAmount;//话费零售总金额，单位美元
    private String wholeSaleTotalAmount;//话费成本总金额，单位美元
    private String freezeBountyTotalAmount;//赏金冻结总金额，单位美元
    private String freezeOutBountyTotalAmount;//赏金提现成功总金额，单位美元
    private String freezeBackBountyTotalAmount;//赏金提现失败总金额，单位美元


    public String getBountyTotalAmount() {
        return bountyTotalAmount;
    }

    public void setBountyTotalAmount(String bountyTotalAmount) {
        this.bountyTotalAmount = bountyTotalAmount;
    }

    public String getSelfBountyTotalAmount() {
        return selfBountyTotalAmount;
    }

    public void setSelfBountyTotalAmount(String selfBountyTotalAmount) {
        this.selfBountyTotalAmount = selfBountyTotalAmount;
    }

    public String getSecBountyTotalAmount() {
        return secBountyTotalAmount;
    }

    public void setSecBountyTotalAmount(String secBountyTotalAmount) {
        this.secBountyTotalAmount = secBountyTotalAmount;
    }

    public String getRetailTotalAmount() {
        return retailTotalAmount;
    }

    public void setRetailTotalAmount(String retailTotalAmount) {
        this.retailTotalAmount = retailTotalAmount;
    }

    public String getWholeSaleTotalAmount() {
        return wholeSaleTotalAmount;
    }

    public void setWholeSaleTotalAmount(String wholeSaleTotalAmount) {
        this.wholeSaleTotalAmount = wholeSaleTotalAmount;
    }

    public String getFreezeBountyTotalAmount() {
        return freezeBountyTotalAmount;
    }

    public void setFreezeBountyTotalAmount(String freezeBountyTotalAmount) {
        if(freezeBountyTotalAmount.startsWith("-")){
            freezeBountyTotalAmount = freezeBountyTotalAmount.replace("-","");
        }
        this.freezeBountyTotalAmount = freezeBountyTotalAmount;
    }

    public String getFreezeOutBountyTotalAmount() {
        return freezeOutBountyTotalAmount;
    }

    public void setFreezeOutBountyTotalAmount(String freezeOutBountyTotalAmount) {
        if(freezeOutBountyTotalAmount.startsWith("-")){
            freezeOutBountyTotalAmount = freezeOutBountyTotalAmount.replace("-","");
        }
        this.freezeOutBountyTotalAmount = freezeOutBountyTotalAmount;
    }

    public String getFreezeBackBountyTotalAmount() {
        return freezeBackBountyTotalAmount;
    }

    public void setFreezeBackBountyTotalAmount(String freezeBackBountyTotalAmount) {
        if(freezeBackBountyTotalAmount.startsWith("-")){
            freezeBackBountyTotalAmount = freezeBackBountyTotalAmount.replace("-","");
        }
        this.freezeBackBountyTotalAmount = freezeBackBountyTotalAmount;
    }
}
