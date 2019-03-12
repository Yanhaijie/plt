package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_risk_card_node_condition")
public class SRiskCardNodeCondition {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评分卡ID
     */
    @Column(name = "card_id")
    private Integer cardId;

    /**
     * 节点ID
     */
    @Column(name = "node_id")
    private Integer nodeId;

    /**
     * 对象值类型 0字符 1数值 2时间 3包含
     */
    @Column(name = "val_type")
    private Integer valType;

    /**
     * 对象值a
     */
    @Column(name = "val_first")
    private Integer valFirst;

    /**
     * 对象值b
     */
    @Column(name = "val_second")
    private Integer valSecond;

    /**
     * 条件
     */
    @Column(name = "val_condition")
    private String valCondition;

    /**
     * 条件状态 0启用 1不启用
     */
    @Column(name = "val_status")
    private Integer valStatus;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取评分卡ID
     *
     * @return card_id - 评分卡ID
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * 设置评分卡ID
     *
     * @param cardId 评分卡ID
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取节点ID
     *
     * @return node_id - 节点ID
     */
    public Integer getNodeId() {
        return nodeId;
    }

    /**
     * 设置节点ID
     *
     * @param nodeId 节点ID
     */
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 获取对象值类型 0字符 1数值 2时间 3包含
     *
     * @return val_type - 对象值类型 0字符 1数值 2时间 3包含
     */
    public Integer getValType() {
        return valType;
    }

    /**
     * 设置对象值类型 0字符 1数值 2时间 3包含
     *
     * @param valType 对象值类型 0字符 1数值 2时间 3包含
     */
    public void setValType(Integer valType) {
        this.valType = valType;
    }

    /**
     * 获取对象值a
     *
     * @return val_first - 对象值a
     */
    public Integer getValFirst() {
        return valFirst;
    }

    /**
     * 设置对象值a
     *
     * @param valFirst 对象值a
     */
    public void setValFirst(Integer valFirst) {
        this.valFirst = valFirst;
    }

    /**
     * 获取对象值b
     *
     * @return val_second - 对象值b
     */
    public Integer getValSecond() {
        return valSecond;
    }

    /**
     * 设置对象值b
     *
     * @param valSecond 对象值b
     */
    public void setValSecond(Integer valSecond) {
        this.valSecond = valSecond;
    }

    /**
     * 获取条件
     *
     * @return val_condition - 条件
     */
    public String getValCondition() {
        return valCondition;
    }

    /**
     * 设置条件
     *
     * @param valCondition 条件
     */
    public void setValCondition(String valCondition) {
        this.valCondition = valCondition;
    }

    /**
     * 获取条件状态 0启用 1不启用
     *
     * @return val_status - 条件状态 0启用 1不启用
     */
    public Integer getValStatus() {
        return valStatus;
    }

    /**
     * 设置条件状态 0启用 1不启用
     *
     * @param valStatus 条件状态 0启用 1不启用
     */
    public void setValStatus(Integer valStatus) {
        this.valStatus = valStatus;
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
}