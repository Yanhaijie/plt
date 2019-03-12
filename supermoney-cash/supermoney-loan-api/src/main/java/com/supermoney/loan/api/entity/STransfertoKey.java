package com.supermoney.loan.api.entity;

import javax.persistence.*;

@Table(name = "s_transferto_key")
public class STransfertoKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 请求类型
     */
    private String type;

    /**
     * 有效值起始值
     */
    @Column(name = "start_value")
    private Integer startValue;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取请求类型
     *
     * @return type - 请求类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置请求类型
     *
     * @param type 请求类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取有效值起始值
     *
     * @return start_value - 有效值起始值
     */
    public Integer getStartValue() {
        return startValue;
    }

    /**
     * 设置有效值起始值
     *
     * @param startValue 有效值起始值
     */
    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }
}