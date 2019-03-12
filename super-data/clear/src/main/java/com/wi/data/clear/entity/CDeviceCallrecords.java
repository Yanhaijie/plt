package com.wi.data.clear.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "c_device_callrecords")
public class CDeviceCallrecords {
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
     * 联系人名称
     */
    private String name;

    /**
     * 联系人号码
     */
    private String number;

    /**
     * 联系时间
     */
    private String time;

    /**
     * 描述
     */
    private String duration;

    /**
     * 类型
     */
    private String type;

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
     * 获取联系人名称
     *
     * @return name - 联系人名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置联系人名称
     *
     * @param name 联系人名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取联系人号码
     *
     * @return number - 联系人号码
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置联系人号码
     *
     * @param number 联系人号码
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取联系时间
     *
     * @return time - 联系时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置联系时间
     *
     * @param time 联系时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取描述
     *
     * @return duration - 描述
     */
    public String getDuration() {
        return duration;
    }

    /**
     * 设置描述
     *
     * @param duration 描述
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
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