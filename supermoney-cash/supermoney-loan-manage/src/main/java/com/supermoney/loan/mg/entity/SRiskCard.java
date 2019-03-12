package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_risk_card")
public class SRiskCard {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评分卡名称
     */
    @Column(name = "card_name")
    private String cardName;

    /**
     * 评分卡状态 0启用 1不启用
     */
    @Column(name = "card_status")
    private Integer cardStatus;

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
     * 获取评分卡名称
     *
     * @return card_name - 评分卡名称
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * 设置评分卡名称
     *
     * @param cardName 评分卡名称
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * 获取评分卡状态 0启用 1不启用
     *
     * @return card_status - 评分卡状态 0启用 1不启用
     */
    public Integer getCardStatus() {
        return cardStatus;
    }

    /**
     * 设置评分卡状态 0启用 1不启用
     *
     * @param cardStatus 评分卡状态 0启用 1不启用
     */
    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
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
}