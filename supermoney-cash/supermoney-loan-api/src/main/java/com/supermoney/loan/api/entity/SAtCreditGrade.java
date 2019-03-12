package com.supermoney.loan.api.entity;

import javax.persistence.*;

@Table(name = "s_at_credit_grade")
public class SAtCreditGrade {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 信用等级名称
     */
    @Column(name = "grade_name")
    private String gradeName;

    /**
     * 最低信用分
     */
    @Column(name = "min_credit_score")
    private Integer minCreditScore;

    /**
     * 额度
     */
    @Column(name = "limit_amout")
    private Integer limitAmout;

    /**
     * 地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;

    /**
     * 排序（F = 1, 依次增大）
     */
    @Column(name = "rank")
    private Integer rank;

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
     * 获取信用等级名称
     *
     * @return grade_name - 信用等级名称
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * 设置信用等级名称
     *
     * @param gradeName 信用等级名称
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * 获取最低信用分
     *
     * @return min_credit_score - 最低信用分
     */
    public Integer getMinCreditScore() {
        return minCreditScore;
    }

    /**
     * 设置最低信用分
     *
     * @param minCreditScore 最低信用分
     */
    public void setMinCreditScore(Integer minCreditScore) {
        this.minCreditScore = minCreditScore;
    }

    /**
     * 获取额度
     *
     * @return limit_amout - 额度
     */
    public Integer getLimitAmout() {
        return limitAmout;
    }

    /**
     * 设置额度
     *
     * @param limitAmout 额度
     */
    public void setLimitAmout(Integer limitAmout) {
        this.limitAmout = limitAmout;
    }

    /**
     * 获取地区编码
     *
     * @return area_code - 地区编码
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**
     * 设置地区编码
     *
     * @param areaCode 地区编码
     */
    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}