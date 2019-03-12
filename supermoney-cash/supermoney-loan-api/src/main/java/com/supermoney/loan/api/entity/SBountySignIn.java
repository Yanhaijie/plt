package com.supermoney.loan.api.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_bounty_sign_in")
public class SBountySignIn {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 登录状态 0：失败 1:成功
     */
    private Integer status;

    /**
     * 赏金任务id
     */
    @Column(name = "bounty_id")
    private Integer bountyId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

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
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取登录状态 0：失败 1:成功
     *
     * @return status - 登录状态 0：失败 1:成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置登录状态 0：失败 1:成功
     *
     * @param status 登录状态 0：失败 1:成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取赏金任务id
     *
     * @return bounty_id - 赏金任务id
     */
    public Integer getBountyId() {
        return bountyId;
    }

    /**
     * 设置赏金任务id
     *
     * @param bountyId 赏金任务id
     */
    public void setBountyId(Integer bountyId) {
        this.bountyId = bountyId;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}