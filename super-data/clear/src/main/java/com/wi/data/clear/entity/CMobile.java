package com.wi.data.clear.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "c_mobile")
public class CMobile {
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
     * 联系人姓名
     */
    private String name;

    /**
     * 联系人号码
     */
    private String number;

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
     * 获取联系人姓名
     *
     * @return name - 联系人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置联系人姓名
     *
     * @param name 联系人姓名
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