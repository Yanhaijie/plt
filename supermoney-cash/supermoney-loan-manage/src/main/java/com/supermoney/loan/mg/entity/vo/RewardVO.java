package com.supermoney.loan.mg.entity.vo;

import java.io.Serializable;

/**
 * Created by wanglijun on 2019/2/23.
 */
public class RewardVO implements Serializable{
    private String userId;//用户id
    private String mobile; //手机号
    private String bountyAmount;//赏金总金额，单位美元
    private String selfBountyAmount;//自身赏金总金额，单位美元
    private String secBountyAmount;//赏金总提成，单位美元
    private String retailAmount;//话费零售价格，单位美元
    private String wholeSaleAmount;//话费成本金额，单位美元
    private String freezeOutBountyAmount;//赏金提现成功金额，单位美元
    private String freezeBountyAmount;//赏金提现冻结，单位美元
    private String freezeBackBountyAmount;//赏金提现失败金额，单位美元


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBountyAmount() {
        return bountyAmount;
    }

    public void setBountyAmount(String bountyAmount) {
        this.bountyAmount = bountyAmount;
    }

    public String getRetailAmount() {
        return retailAmount;
    }

    public void setRetailAmount(String retailAmount) {
        this.retailAmount = retailAmount;
    }

    public String getWholeSaleAmount() {
        return wholeSaleAmount;
    }

    public void setWholeSaleAmount(String wholeSaleAmount) {
        this.wholeSaleAmount = wholeSaleAmount;
    }

    public String getSelfBountyAmount() {
        return selfBountyAmount;
    }

    public void setSelfBountyAmount(String selfBountyAmount) {
        this.selfBountyAmount = selfBountyAmount;
    }

    public String getSecBountyAmount() {
        return secBountyAmount;
    }

    public void setSecBountyAmount(String secBountyAmount) {
        this.secBountyAmount = secBountyAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFreezeOutBountyAmount() {
        return freezeOutBountyAmount;
    }

    public void setFreezeOutBountyAmount(String freezeOutBountyAmount) {
        if(freezeOutBountyAmount.startsWith("-")){
            freezeOutBountyAmount = freezeOutBountyAmount.replace("-","");
        }
        this.freezeOutBountyAmount = freezeOutBountyAmount;
    }

    public String getFreezeBountyAmount() {
        return freezeBountyAmount;
    }

    public void setFreezeBountyAmount(String freezeBountyAmount) {
        if(freezeBountyAmount.startsWith("-")){
            freezeBountyAmount = freezeBountyAmount.replace("-","");
        }
        this.freezeBountyAmount = freezeBountyAmount;
    }

    public String getFreezeBackBountyAmount() {
        return freezeBackBountyAmount;
    }

    public void setFreezeBackBountyAmount(String freezeBackBountyAmount) {
        if(freezeBackBountyAmount.startsWith("-")){
            freezeBackBountyAmount = freezeBackBountyAmount.replace("-","");
        }
        this.freezeBackBountyAmount = freezeBackBountyAmount;
    }
}
