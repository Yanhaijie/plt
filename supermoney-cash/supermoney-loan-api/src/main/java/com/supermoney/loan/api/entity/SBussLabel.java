package com.supermoney.loan.api.entity;

import javax.persistence.*;

@Table(name = "s_buss_label")
public class SBussLabel {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 业务名称
     */
    @Column(name = "buss_name")
    private String bussName;

    /**
     * 标签值
     */
    @Column(name = "buss_val")
    private String bussVal;

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
     * 获取业务名称
     *
     * @return buss_name - 业务名称
     */
    public String getBussName() {
        return bussName;
    }

    /**
     * 设置业务名称
     *
     * @param bussName 业务名称
     */
    public void setBussName(String bussName) {
        this.bussName = bussName;
    }

    /**
     * 获取标签值
     *
     * @return buss_val - 标签值
     */
    public String getBussVal() {
        return bussVal;
    }

    /**
     * 设置标签值
     *
     * @param bussVal 标签值
     */
    public void setBussVal(String bussVal) {
        this.bussVal = bussVal;
    }
}