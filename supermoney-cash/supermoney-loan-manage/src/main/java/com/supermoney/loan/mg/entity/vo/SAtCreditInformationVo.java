package com.supermoney.loan.mg.entity.vo;

import com.supermoney.loan.mg.entity.SAtCreditInformation;

import java.io.Serializable;

public class SAtCreditInformationVo extends SAtCreditInformation implements Serializable {

    private String userName;

    private String mobile;

    private  String imgFront;

    private String imgBack;

    private String imgHold;

    private Integer identityStatus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImgFront() {
        return imgFront;
    }

    public void setImgFront(String imgFront) {
        this.imgFront = imgFront;
    }

    public String getImgBack() {
        return imgBack;
    }

    public void setImgBack(String imgBack) {
        this.imgBack = imgBack;
    }

    public String getImgHold() {
        return imgHold;
    }

    public void setImgHold(String imgHold) {
        this.imgHold = imgHold;
    }

    public Integer getIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(Integer identityStatus) {
        this.identityStatus = identityStatus;
    }
}
