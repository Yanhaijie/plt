package com.supermoney.loan.mg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_risk_card_node")
public class SRiskCardNode {
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
     * 父ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 节点名称
     */
    @Column(name = "node_name")
    private String nodeName;

    /**
     * 节点类型
     */
    @Column(name = "node_type")
    private Integer nodeType;

    /**
     * 节点状态 0启用 1不启用
     */
    @Column(name = "node_status")
    private Integer nodeStatus;

    /**
     * 数据源ID
     */
    @Column(name = "source_id")
    private Integer sourceId;

    /**
     * 评分比例
     */
    private BigDecimal proportion;

    /**
     * 分数值
     */
    @Column(name = "score_val")
    private BigDecimal scoreVal;

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
     * 获取父ID
     *
     * @return parent_id - 父ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     *
     * @param parentId 父ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取节点名称
     *
     * @return node_name - 节点名称
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 设置节点名称
     *
     * @param nodeName 节点名称
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * 获取节点类型
     *
     * @return node_type - 节点类型
     */
    public Integer getNodeType() {
        return nodeType;
    }

    /**
     * 设置节点类型
     *
     * @param nodeType 节点类型
     */
    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 获取节点状态 0启用 1不启用
     *
     * @return node_status - 节点状态 0启用 1不启用
     */
    public Integer getNodeStatus() {
        return nodeStatus;
    }

    /**
     * 设置节点状态 0启用 1不启用
     *
     * @param nodeStatus 节点状态 0启用 1不启用
     */
    public void setNodeStatus(Integer nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    /**
     * 获取数据源ID
     *
     * @return source_id - 数据源ID
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * 设置数据源ID
     *
     * @param sourceId 数据源ID
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取评分比例
     *
     * @return proportion - 评分比例
     */
    public BigDecimal getProportion() {
        return proportion;
    }

    /**
     * 设置评分比例
     *
     * @param proportion 评分比例
     */
    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    /**
     * 获取分数值
     *
     * @return score_val - 分数值
     */
    public BigDecimal getScoreVal() {
        return scoreVal;
    }

    /**
     * 设置分数值
     *
     * @param scoreVal 分数值
     */
    public void setScoreVal(BigDecimal scoreVal) {
        this.scoreVal = scoreVal;
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