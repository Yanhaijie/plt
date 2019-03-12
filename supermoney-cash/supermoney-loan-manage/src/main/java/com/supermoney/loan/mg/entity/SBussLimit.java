package com.supermoney.loan.mg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_buss_limit")
public class SBussLimit {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 区间类型 0借款服务费利率区间
     */
    @Column(name = "limit_type")
    private Integer limitType;

    /**
     * 关联业务ID
     */
    @Column(name = "relate_id")
    private Integer relateId;

    /**
     * 起始值
     */
    @Column(name = "startVal")
    private BigDecimal startval;

    /**
     * 结束值
     */
    @Column(name = "endVal")
    private BigDecimal endval;

    /**
     * 区间指定值
     */
    @Column(name = "termVal")
    private BigDecimal termval;

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
     * 获取区间类型 0借款服务费利率区间
     *
     * @return limit_type - 区间类型 0借款服务费利率区间
     */
    public Integer getLimitType() {
        return limitType;
    }

    /**
     * 设置区间类型 0借款服务费利率区间
     *
     * @param limitType 区间类型 0借款服务费利率区间
     */
    public void setLimitType(Integer limitType) {
        this.limitType = limitType;
    }

    /**
     * 获取关联业务ID
     *
     * @return relate_id - 关联业务ID
     */
    public Integer getRelateId() {
        return relateId;
    }

    /**
     * 设置关联业务ID
     *
     * @param relateId 关联业务ID
     */
    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public BigDecimal getStartval() {
        return startval;
    }

    public void setStartval(BigDecimal startval) {
        this.startval = startval;
    }

    public BigDecimal getEndval() {
        return endval;
    }

    public void setEndval(BigDecimal endval) {
        this.endval = endval;
    }

    public BigDecimal getTermval() {
        return termval;
    }

    public void setTermval(BigDecimal termval) {
        this.termval = termval;
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
}