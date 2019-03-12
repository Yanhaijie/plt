package com.wi.data.clear.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "c_device_app")
public class CDeviceApp {
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
     * APP名称
     */
    private String label;

    /**
     * APP包名
     */
    @Column(name = "packageName")
    private String packagename;

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
     * 获取APP名称
     *
     * @return label - APP名称
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置APP名称
     *
     * @param label APP名称
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取APP包名
     *
     * @return packageName - APP包名
     */
    public String getPackagename() {
        return packagename;
    }

    /**
     * 设置APP包名
     *
     * @param packagename APP包名
     */
    public void setPackagename(String packagename) {
        this.packagename = packagename;
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