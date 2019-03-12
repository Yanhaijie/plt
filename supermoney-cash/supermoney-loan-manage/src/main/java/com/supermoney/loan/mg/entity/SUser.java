package com.supermoney.loan.mg.entity;


import javax.persistence.*;
import java.util.Date;

@Table(name = "s_user")
public class SUser {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户唯一编码
     */
    @Column(name = "user_code")
    private String userCode;
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
     * 分组编号
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 用户级别:0普通、1会员、2钻石会员
     */
    @Column(name = "user_level")
    private Integer userLevel;

    /**
     * 信用分数
     */
    @Column(name = "credit_score")
    private Integer creditScore;

    /**
     * 信用等级（0 F (默认),1 E, 2 D , 3 C , 4 B , 5 A）
     */
    @Column(name = "credit_grade")
    private Integer creditGrade;


    /**
     * 信用资料修改（0 否 ， 1 是）
     */
    @Column(name = "credit_modify")
    private Integer creditModify;

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
     * 联系人一:名称+电话
     */
    @Column(name = "person_one")
    private String personOne;

    /**
     * 联系人二:名称+电话
     */
    @Column(name = "person_two")
    private String personTwo;

    /**
     * 联系人三:名称+电话
     */
    @Column(name = "person_three")
    private String personThree;

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
     * 国家
     */
    private String country;

    /**
     * 国家地区
     */
    @Column(name = "country_area")
    private String countryArea;

    /**
     * 注册来源
     */
    @Column(name = "reg_source")
    private String regSource;

    /**
     * 注册ip
     */
    @Column(name = "reg_ip")
    private String regIp;

    /**
     * 状态:0使用、1停止
     */
    @Column(name = "user_status")
    private Integer userStatus;

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
     * 上级ID,代理关系
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 国家地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;

    /**
     * 抽奖次数
     */
    @Column(name = "lottery_count")
    private Integer lotteryCount;


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
     * 获取分组编号
     *
     * @return group_id - 分组编号
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置分组编号
     *
     * @param groupId 分组编号
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取用户级别:0普通、1会员、2钻石会员
     *
     * @return user_level - 用户级别:0普通、1会员、2钻石会员
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     * 设置用户级别:0普通、1会员、2钻石会员
     *
     * @param userLevel 用户级别:0普通、1会员、2钻石会员
     */
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
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
     * 获取联系人一:名称+电话
     *
     * @return person_one - 联系人一:名称+电话
     */
    public String getPersonOne() {
        return personOne;
    }

    /**
     * 设置联系人一:名称+电话
     *
     * @param personOne 联系人一:名称+电话
     */
    public void setPersonOne(String personOne) {
        this.personOne = personOne;
    }

    /**
     * 获取联系人二:名称+电话
     *
     * @return person_two - 联系人二:名称+电话
     */
    public String getPersonTwo() {
        return personTwo;
    }

    /**
     * 设置联系人二:名称+电话
     *
     * @param personTwo 联系人二:名称+电话
     */
    public void setPersonTwo(String personTwo) {
        this.personTwo = personTwo;
    }

    /**
     * 获取联系人三:名称+电话
     *
     * @return person_three - 联系人三:名称+电话
     */
    public String getPersonThree() {
        return personThree;
    }

    /**
     * 设置联系人三:名称+电话
     *
     * @param personThree 联系人三:名称+电话
     */
    public void setPersonThree(String personThree) {
        this.personThree = personThree;
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
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取国家地区
     *
     * @return country_area - 国家地区
     */
    public String getCountryArea() {
        return countryArea;
    }

    /**
     * 设置国家地区
     *
     * @param countryArea 国家地区
     */
    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    /**
     * 获取注册来源
     *
     * @return reg_source - 注册来源
     */
    public String getRegSource() {
        return regSource;
    }

    /**
     * 设置注册来源
     *
     * @param regSource 注册来源
     */
    public void setRegSource(String regSource) {
        this.regSource = regSource;
    }

    /**
     * 获取注册ip
     *
     * @return reg_ip - 注册ip
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * 设置注册ip
     *
     * @param regIp 注册ip
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
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

    /**
     * 获取上级ID,代理关系
     *
     * @return parent_id - 上级ID,代理关系
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置上级ID,代理关系
     *
     * @param parentId 上级ID,代理关系
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getLotteryCount() {
        return lotteryCount;
    }

    public void setLotteryCount(Integer lotteryCount) {
        this.lotteryCount = lotteryCount;
    }

    public Integer getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(Integer creditGrade) {
        this.creditGrade = creditGrade;
    }

    public Integer getCreditModify() {
        return creditModify;
    }

    public void setCreditModify(Integer creditModify) {
        this.creditModify = creditModify;
    }
}