package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_right")
public class SRight {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父编号
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 权限名称
     */
    @Column(name = "right_name")
    private String rightName;

    /**
     * 权限url
     */
    @Column(name = "right_url")
    private String rightUrl;

    /**
     * 权限类型，0一级菜单，1二级菜单，2按钮
     */
    @Column(name = "right_type")
    private String rightType;

    /**
     * 权限排序
     */
    @Column(name = "right_order")
    private String rightOrder;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 状态:0使用、1停止
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * 可见类型:0普通用户可见、1管理员可见
     */
    @Column(name = "visible_type")
    private Integer visibleType;

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
     * 获取父编号
     *
     * @return parent_id - 父编号
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父编号
     *
     * @param parentId 父编号
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取权限名称
     *
     * @return right_name - 权限名称
     */
    public String getRightName() {
        return rightName;
    }

    /**
     * 设置权限名称
     *
     * @param rightName 权限名称
     */
    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    /**
     * 获取权限url
     *
     * @return right_url - 权限url
     */
    public String getRightUrl() {
        return rightUrl;
    }

    /**
     * 设置权限url
     *
     * @param rightUrl 权限url
     */
    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    /**
     * 获取权限类型，0一级菜单，1二级菜单，2按钮
     *
     * @return right_type - 权限类型，0一级菜单，1二级菜单，2按钮
     */
    public String getRightType() {
        return rightType;
    }

    /**
     * 设置权限类型，0一级菜单，1二级菜单，2按钮
     *
     * @param rightType 权限类型，0一级菜单，1二级菜单，2按钮
     */
    public void setRightType(String rightType) {
        this.rightType = rightType;
    }

    /**
     * 获取权限排序
     *
     * @return right_order - 权限排序
     */
    public String getRightOrder() {
        return rightOrder;
    }

    /**
     * 设置权限排序
     *
     * @param rightOrder 权限排序
     */
    public void setRightOrder(String rightOrder) {
        this.rightOrder = rightOrder;
    }

    /**
     * 获取权限描述
     *
     * @return description - 权限描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置权限描述
     *
     * @param description 权限描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取状态:0使用、1停止
     *
     * @return user_status - 状态:0使用、1停止
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置状态:0使用、1停止
     *
     * @param userStatus 状态:0使用、1停止
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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

    public Integer getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(Integer visibleType) {
        this.visibleType = visibleType;
    }
}