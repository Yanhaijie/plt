package com.wi.data.clear.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "c_device_msg")
public class CDeviceMsg {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备唯一编码
     */
    @Column(name = "unique_id")
    private String uniqueId;

    /**
     * 短信号码
     */
    private String number;

    /**
     * 消息内容
     */
    private String messaage;

    /**
     * 发送时间
     */
    private String time;

    /**
     * 是否看到过
     */
    private String seen;

    /**
     * 发送时间
     */
    @Column(name = "dateSent")
    private String datesent;

    /**
     * 是否已读
     */
    private String read;

    /**
     * 对话ID
     */
    private String person;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取设备唯一编码
     *
     * @return unique_id - 设备唯一编码
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 设置设备唯一编码
     *
     * @param uniqueId 设备唯一编码
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * 获取短信号码
     *
     * @return number - 短信号码
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置短信号码
     *
     * @param number 短信号码
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取消息内容
     *
     * @return messaage - 消息内容
     */
    public String getMessaage() {
        return messaage;
    }

    /**
     * 设置消息内容
     *
     * @param messaage 消息内容
     */
    public void setMessaage(String messaage) {
        this.messaage = messaage;
    }

    /**
     * 获取发送时间
     *
     * @return time - 发送时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置发送时间
     *
     * @param time 发送时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取是否看到过
     *
     * @return seen - 是否看到过
     */
    public String getSeen() {
        return seen;
    }

    /**
     * 设置是否看到过
     *
     * @param seen 是否看到过
     */
    public void setSeen(String seen) {
        this.seen = seen;
    }

    /**
     * 获取发送时间
     *
     * @return dateSent - 发送时间
     */
    public String getDatesent() {
        return datesent;
    }

    /**
     * 设置发送时间
     *
     * @param datesent 发送时间
     */
    public void setDatesent(String datesent) {
        this.datesent = datesent;
    }

    /**
     * 获取是否已读
     *
     * @return read - 是否已读
     */
    public String getRead() {
        return read;
    }

    /**
     * 设置是否已读
     *
     * @param read 是否已读
     */
    public void setRead(String read) {
        this.read = read;
    }

    /**
     * 获取对话ID
     *
     * @return person - 对话ID
     */
    public String getPerson() {
        return person;
    }

    /**
     * 设置对话ID
     *
     * @param person 对话ID
     */
    public void setPerson(String person) {
        this.person = person;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}