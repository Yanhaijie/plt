package com.supermoney.loan.vo;

import java.util.Date;

/**
 * Created by yanhaijie on 2019/3/1.
 */
public class WaitkeOutBalanceVo {

    private int userId;

    private String mobile;

    private int userName;

    private Date sendTime;

    private int status;

    private String smsSendNumber;

    public String getSmsSendNumber() {
        return smsSendNumber;
    }

    public void setSmsSendNumber(String smsSendNumber) {
        this.smsSendNumber = smsSendNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public int getUserName() {
        return userName;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }
}
