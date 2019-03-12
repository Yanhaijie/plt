package com.supermoney.open.platform.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_nik_info")
public class SNikInfo {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * NIK
     */
    private String nik;

    /**
     * 地址
     */
    private String address;

    /**
     * 出生日期
     */
    private String bod;

    /**
     * 名字
     */
    private String name;

    /**
     * 三方创建时间
     */
    @Column(name = "created_at")
    private String createdAt;

    /**
     * 三方最后修改时间
     */
    @Column(name = "updated_at")
    private String updatedAt;

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

    /**
     * 获取最后修改时间
     *
     * @return update_time - 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取NIK
     *
     * @return nik - NIK
     */
    public String getNik() {
        return nik;
    }

    /**
     * 设置NIK
     *
     * @param nik NIK
     */
    public void setNik(String nik) {
        this.nik = nik;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取出生日期
     *
     * @return bod - 出生日期
     */
    public String getBod() {
        return bod;
    }

    /**
     * 设置出生日期
     *
     * @param bod 出生日期
     */
    public void setBod(String bod) {
        this.bod = bod;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取三方创建时间
     *
     * @return created_at - 三方创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置三方创建时间
     *
     * @param createdAt 三方创建时间
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取三方最后修改时间
     *
     * @return updated_at - 三方最后修改时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置三方最后修改时间
     *
     * @param updatedAt 三方最后修改时间
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}