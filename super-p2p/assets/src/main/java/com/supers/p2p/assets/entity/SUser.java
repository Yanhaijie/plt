package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_user")
public class SUser {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 用户类型:0资产方个人，1资产方企业，2资金方个人，3资金方企业
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 信用分数
     */
    @Column(name = "credit_score")
    private Integer creditScore;

    /**
     * 资金需求
     */
    @Column(name = "need_amount")
    private Integer needAmount;

    /**
     * 需求周长
     */
    @Column(name = "need_cycle")
    private Integer needCycle;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 真是姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 状态:0使用、1停止
     */
    @Column(name = "use_status")
    private Integer useStatus;

    /**
     * 父编号
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 所属公司
     */
    @Column(name = "company_id")
    private Integer companyId;

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
     * 获取账号名称
     *
     * @return user_name - 账号名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置账号名称
     *
     * @param userName 账号名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户密码
     *
     * @return user_password - 用户密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置用户密码
     *
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取用户类型:0资产方个人，1资产方企业，2资金方个人，3资金方企业
     *
     * @return user_type - 用户类型:0资产方个人，1资产方企业，2资金方个人，3资金方企业
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类型:0资产方个人，1资产方企业，2资金方个人，3资金方企业
     *
     * @param userType 用户类型:0资产方个人，1资产方企业，2资金方个人，3资金方企业
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取信用分数
     *
     * @return credit_score - 信用分数
     */
    public Integer getCreditScore() {
        return creditScore;
    }

    /**
     * 设置信用分数
     *
     * @param creditScore 信用分数
     */
    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * 获取资金需求
     *
     * @return need_amount - 资金需求
     */
    public Integer getNeedAmount() {
        return needAmount;
    }

    /**
     * 设置资金需求
     *
     * @param needAmount 资金需求
     */
    public void setNeedAmount(Integer needAmount) {
        this.needAmount = needAmount;
    }

    /**
     * 获取需求周长
     *
     * @return need_cycle - 需求周长
     */
    public Integer getNeedCycle() {
        return needCycle;
    }

    /**
     * 设置需求周长
     *
     * @param needCycle 需求周长
     */
    public void setNeedCycle(Integer needCycle) {
        this.needCycle = needCycle;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取电子邮件
     *
     * @return email - 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     *
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取真是姓名
     *
     * @return real_name - 真是姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真是姓名
     *
     * @param realName 真是姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取状态:0使用、1停止
     *
     * @return use_status - 状态:0使用、1停止
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**
     * 设置状态:0使用、1停止
     *
     * @param useStatus 状态:0使用、1停止
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
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
     * 获取所属公司
     *
     * @return company_id - 所属公司
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置所属公司
     *
     * @param companyId 所属公司
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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