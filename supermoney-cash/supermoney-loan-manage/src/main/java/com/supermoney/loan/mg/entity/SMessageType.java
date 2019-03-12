package com.supermoney.loan.mg.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "s_message_type")
public class SMessageType {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别图标
     */
    private String img;

    /**
     * 状态：0使用、1停止
     */
    @Column(name = "use_status")
    private Integer useStatus;

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
     * 操作人
     */
    private String opt;

    /**
     * 排序
     */
    private Integer sort;


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
     * 获取类别名称
     *
     * @return name - 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类别名称
     *
     * @param name 类别名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类别图标
     *
     * @return img - 类别图标
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置类别图标
     *
     * @param img 类别图标
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取状态：0使用、1停止
     *
     * @return use_status - 状态：0使用、1停止
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**
     * 设置状态：0使用、1停止
     *
     * @param useStatus 状态：0使用、1停止
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
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
     * 获取操作人
     *
     * @return opt - 操作人
     */
    public String getOpt() {
        return opt;
    }

    /**
     * 设置操作人
     *
     * @param opt 操作人
     */
    public void setOpt(String opt) {
        this.opt = opt;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}