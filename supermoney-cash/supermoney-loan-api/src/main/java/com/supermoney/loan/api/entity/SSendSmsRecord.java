package com.supermoney.loan.api.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: SUserSendSmsRecord
 * @Author: yanhaijie
 * @CreateDate: 2019-02-26 20:50
 * @Version: 1.0
 */
@Table(name = "s_send_sms_record")

public class SSendSmsRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "type")
    private int type;

    @Column(name = "status")
    private int status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "sms_record")
    private String smsRecord;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "mobile")
    private String mobile;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSmsRecord() {
        return smsRecord;
    }

    public void setSmsRecord(String smsRecord) {
        this.smsRecord = smsRecord;
    }

    public SSendSmsRecord(int userId, int type, int status, Date createTime, Date sendTime, String smsRecord) {
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.createTime = createTime;
        this.sendTime = sendTime;
        this.smsRecord = smsRecord;
    }

    public SSendSmsRecord() {

    }

    @Override
    public String toString() {
        return "SUserSendSmsRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", statuc=" + status +
                ", createTime=" + createTime +
                ", sendTime=" + sendTime +
                ", SmsRecord='" + smsRecord + '\'' +
                '}';
    }

}
