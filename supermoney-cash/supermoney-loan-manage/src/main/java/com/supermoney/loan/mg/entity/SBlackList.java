package com.supermoney.loan.mg.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "s_black_list")
public class SBlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 来源，1短信，2手机应用
     */
    private Integer source;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "black_level")
    private Integer blackLevel;

    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    private String opt;

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
     * 获取来源，1短信，2手机应用
     *
     * @return source - 来源，1短信，2手机应用
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置来源，1短信，2手机应用
     *
     * @param source 来源，1短信，2手机应用
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return black_level
     */
    public Integer getBlackLevel() {
        return blackLevel;
    }

    /**
     * @param blackLevel
     */
    public void setBlackLevel(Integer blackLevel) {
        this.blackLevel = blackLevel;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return opt
     */
    public String getOpt() {
        return opt;
    }

    /**
     * @param opt
     */
    public void setOpt(String opt) {
        this.opt = opt;
    }
}