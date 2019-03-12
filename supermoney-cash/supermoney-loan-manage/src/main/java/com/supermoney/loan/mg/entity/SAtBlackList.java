package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_at_black_list")
public class SAtBlackList {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 姓名
     */
    private String name;

    /**
     * 身份证号（NIK）
     */
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 电话号码
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 命中结果（PASS 未命中， NEEDS_VERIFICATION 电话命中，REJECT 其他命中 ）
     */
    private String recommendation;

    /**
     * 被列入黑名单的时间
     */
    @Column(name = "event_time")
    private String eventTime;

    /**
     * 哪些资料被列入黑名单
     */
    @Column(name = "hit_reason")
    private String hitReason;

    /**
     * 数据源的类型（CASH_LOAN 贷款，CONSUMPTION_LOAN 分期付款）
     */
    @Column(name = "product_type")
    private String productType;

    /**
     * 被列入黑名单的原因（OVERDUE_DAYS_GREATER_THAN_90 逾期3个月，）
     */
    @Column(name = "reason_code")
    private String reasonCode;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取身份证号（NIK）
     *
     * @return id_number - 身份证号（NIK）
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证号（NIK）
     *
     * @param idNumber 身份证号（NIK）
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取电话号码
     *
     * @return phone_number - 电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置电话号码
     *
     * @param phoneNumber 电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取命中结果（PASS 未命中， NEEDS_VERIFICATION 电话命中，REJECT 其他命中 ）
     *
     * @return recommendation - 命中结果（PASS 未命中， NEEDS_VERIFICATION 电话命中，REJECT 其他命中 ）
     */
    public String getRecommendation() {
        return recommendation;
    }

    /**
     * 设置命中结果（PASS 未命中， NEEDS_VERIFICATION 电话命中，REJECT 其他命中 ）
     *
     * @param recommendation 命中结果（PASS 未命中， NEEDS_VERIFICATION 电话命中，REJECT 其他命中 ）
     */
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    /**
     * 获取被列入黑名单的时间
     *
     * @return event_time - 被列入黑名单的时间
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * 设置被列入黑名单的时间
     *
     * @param eventTime 被列入黑名单的时间
     */
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * 获取哪些资料被列入黑名单
     *
     * @return hit_reason - 哪些资料被列入黑名单
     */
    public String getHitReason() {
        return hitReason;
    }

    /**
     * 设置哪些资料被列入黑名单
     *
     * @param hitReason 哪些资料被列入黑名单
     */
    public void setHitReason(String hitReason) {
        this.hitReason = hitReason;
    }

    /**
     * 获取数据源的类型（CASH_LOAN 贷款，CONSUMPTION_LOAN 分期付款）
     *
     * @return product_type - 数据源的类型（CASH_LOAN 贷款，CONSUMPTION_LOAN 分期付款）
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置数据源的类型（CASH_LOAN 贷款，CONSUMPTION_LOAN 分期付款）
     *
     * @param productType 数据源的类型（CASH_LOAN 贷款，CONSUMPTION_LOAN 分期付款）
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * 获取被列入黑名单的原因（OVERDUE_DAYS_GREATER_THAN_90 逾期3个月，）
     *
     * @return reason_code - 被列入黑名单的原因（OVERDUE_DAYS_GREATER_THAN_90 逾期3个月，）
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * 设置被列入黑名单的原因（OVERDUE_DAYS_GREATER_THAN_90 逾期3个月，）
     *
     * @param reasonCode 被列入黑名单的原因（OVERDUE_DAYS_GREATER_THAN_90 逾期3个月，）
     */
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
}