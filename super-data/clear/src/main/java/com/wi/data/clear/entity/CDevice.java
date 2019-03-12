package com.wi.data.clear.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "c_device")
public class CDevice {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备唯一编码 androidID or iosID
     */
    @Column(name = "unique_id")
    private String uniqueId;

    /**
     * imei
     */
    private String imei;

    /**
     * imsi
     */
    private String imsi;

    /**
     * 手机型号
     */
    private String model;

    /**
     * 手机品牌
     */
    private String manufacturer;

    /**
     * 系统类型 ios android 
     */
    private String system;

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
     * 获取设备唯一编码 androidID or iosID
     *
     * @return unique_id - 设备唯一编码 androidID or iosID
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 设置设备唯一编码 androidID or iosID
     *
     * @param uniqueId 设备唯一编码 androidID or iosID
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * 获取imei
     *
     * @return imei - imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * 设置imei
     *
     * @param imei imei
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * 获取imsi
     *
     * @return imsi - imsi
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * 设置imsi
     *
     * @param imsi imsi
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * 获取手机型号
     *
     * @return model - 手机型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置手机型号
     *
     * @param model 手机型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取手机品牌
     *
     * @return manufacturer - 手机品牌
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 设置手机品牌
     *
     * @param manufacturer 手机品牌
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * 获取系统类型 ios android 
     *
     * @return system - 系统类型 ios android 
     */
    public String getSystem() {
        return system;
    }

    /**
     * 设置系统类型 ios android 
     *
     * @param system 系统类型 ios android 
     */
    public void setSystem(String system) {
        this.system = system;
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