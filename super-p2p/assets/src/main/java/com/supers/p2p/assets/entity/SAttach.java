package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_attach")
public class SAttach {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 附加链接
     */
    @Column(name = "attach_url")
    private String attachUrl;

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
     * 附件信息
     */
    @Column(name = "attach_info")
    private String attachInfo;

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
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取附加链接
     *
     * @return attach_url - 附加链接
     */
    public String getAttachUrl() {
        return attachUrl;
    }

    /**
     * 设置附加链接
     *
     * @param attachUrl 附加链接
     */
    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
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

    /**
     * 获取附件信息
     *
     * @return attach_info - 附件信息
     */
    public String getAttachInfo() {
        return attachInfo;
    }

    /**
     * 设置附件信息
     *
     * @param attachInfo 附件信息
     */
    public void setAttachInfo(String attachInfo) {
        this.attachInfo = attachInfo;
    }
}