package com.supermoney.loan.api.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_at_credit_information")
public class SAtCreditInformation {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer userId;

    /**
     * 证件表编号
     */
    @Column(name = "identity_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer identityId;

    /**
     * 人脸表编号
     */
    @Column(name = "living_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer livingId;

    /**
     * 身份证号码
     */
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证正面照
     */
    @Column(name = "id_front_img")
    private String idFrontImg;

    /**
     * 手持身份证照
     */
    @Column(name = "id_hold_img")
    private String idHoldImg;

    /**
     * 出生年月
     */
    private String birthday;

    /**
     * 性別
     */
    private String gender;

    /**
     * 婚姻状况
     */
    private String married;

    /**
     * 宗教
     */
    private String religion;

    /**
     * 文化程度
     */
    private String education;

    /**
     * 居住地址
     */
    @Column(name = "live_address")
    private String liveAddress;

    /**
     * 居住地址
     */
    @Column(name = "live_address_street")
    private String liveAddressStreet;

    /**
     * 职业:0未填写 1白领、2商人、3无业、4学生
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer profession;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 公司行业
     */
    private String industry;

    /**
     * 职位
     */
    private String position;

    /**
     * 平均月收入
     */
    @Column(name = "month_income")
    private String monthIncome;

    /**
     * 公司电话
     */
    @Column(name = "company_phone")
    private String companyPhone;

    /**
     * 公司地址[省份+城市+街道]
     */
    @Column(name = "company_address")
    private String companyAddress;

    /**
     * 公司地址[省份+城市+街道]
     */
    @Column(name = "company_address_street")
    private String companyAddressStreet;

    /**
     * 公司人数
     */
    @Column(name = "company_persons")
    private String companyPersons;

    /**
     * 无业工作时长
     */
    @Column(name = "not_job_time")
    private String notJobTime;

    /**
     * 上一份工作所属行业
     */
    @Column(name = "last_industry")
    private String lastIndustry;

    /**
     * 上一份工作平均月收入
     */
    @Column(name = "last_income")
    private String lastIncome;

    /**
     * 无业期间月收入来源
     */
    @Column(name = "not_job_source")
    private String notJobSource;

    /**
     * 无业期间月收入
     */
    @Column(name = "not_job_income")
    private String notJobIncome;

    /**
     * 学校名称
     */
    @Column(name = "school_name")
    private String schoolName;

    /**
     * 学校专业
     */
    @Column(name = "school_course")
    private String schoolCourse;

    /**
     * 入学时间
     */
    @Column(name = "school_in")
    private String schoolIn;

    /**
     * 毕业时间
     */
    @Column(name = "school_out")
    private String schoolOut;

    /**
     * 亲属类型
     */
    @Column(name = "relative_type")
    private String relativeType;

    /**
     * 亲属姓名
     */
    @Column(name = "relative_name")
    private String relativeName;

    /**
     * 亲属电话
     */
    @Column(name = "relative_phone")
    private String relativePhone;

    /**
     * 联系人姓名
     */
    @Column(name = "urgent_name")
    private String urgentName;

    /**
     * 联系人号码
     */
    @Column(name = "urgent_phone")
    private String urgentPhone;

    /**
     * 持卡人姓名
     */
    @Column(name = "card_name")
    private String cardName;

    /**
     * 持卡人身份证号
     */
    @Column(name = "card_id")
    private String cardId;

    /**
     * 持卡人银行
     */
    @Column(name = "card_bank")
    private String cardBank;

    /**
     * 持卡人账户
     */
    @Column(name = "card_account")
    private String cardAccount;

    /**
     * 资料提交步骤
     */
    @Column(name = "cur_step")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer curStep;

    /**
     * 状态:0暂存 1待审核 2 审核通过 3审核失败
     */
    @Column(name = "credit_status")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer creditStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonSerialize(using = ToStringSerializer.class)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    @JsonSerialize(using = ToStringSerializer.class)
    private Date updateTime;

    /**
     * 认证认证状态
     */
    @Transient
    private Integer checkStatus = 0;

    /**
     * 保险
     */
    @Column(name = "BPJS_img")
    private String BPJSImg;

    /**
     * 家庭卡
     */
    @Column(name = "KK_img")
    private String KKImg;

    /**
     * 信用卡
     */
    @Column(name = "credit_card_img")
    private String creditCardImg;

    /**
     * 工作卡
     */
    @Column(name = "work_card_img")
    private String workCardImg;

    /**
     * 工资证明
     */
    @Column(name = "salary_cert_img")
    private String salaryCertImg;

    /**
     * 公司给的宣誓书
     */
    @Column(name = "company_affidavit_img")
    private String companyAffidavitImg;

    /**
     * 银行账单
     */
    @Column(name = "bank_statement_img")
    private String bankStatementImg;

    /**
     * 其他支持你工作真相的证据
     */
    @Column(name = "other_work_img")
    private String otherWorkImg;

    /**
     * 帐户照片
     */
    @Column(name = "account_img")
    private String accountImg;

    /**
     * 每日历史照片
     */
    @Column(name = "day_history_img")
    private String dayHistoryImg;

    /**
     * 照片历史最近2周
     */
    @Column(name = "week_history_img")
    private String weekHistoryImg;

    /**
     * 照片历史最近1个月
     */
    @Column(name = "month_history_img")
    private String monthHistoryImg;
    /**
     * 孩子数量
     */
    @Column(name = "children")
    private  String children;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取证件表编号
     *
     * @return identity_id - 证件表编号
     */
    public Integer getIdentityId() {
        return identityId;
    }

    /**
     * 设置证件表编号
     *
     * @param identityId 证件表编号
     */
    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    /**
     * 获取人脸表编号
     *
     * @return living_id - 人脸表编号
     */
    public Integer getLivingId() {
        return livingId;
    }

    /**
     * 设置人脸表编号
     *
     * @param livingId 人脸表编号
     */
    public void setLivingId(Integer livingId) {
        this.livingId = livingId;
    }

    /**
     * 获取身份证号码
     *
     * @return id_number - 身份证号码
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证号码
     *
     * @param idNumber 身份证号码
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取姓名
     *
     * @return real_name - 姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置姓名
     *
     * @param realName 姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取身份证正面照
     *
     * @return id_front_img - 身份证正面照
     */
    public String getIdFrontImg() {
        return idFrontImg;
    }

    /**
     * 设置身份证正面照
     *
     * @param idFrontImg 身份证正面照
     */
    public void setIdFrontImg(String idFrontImg) {
        this.idFrontImg = idFrontImg;
    }

    /**
     * 获取手持身份证照
     *
     * @return id_hold_img - 手持身份证照
     */
    public String getIdHoldImg() {
        return idHoldImg;
    }

    /**
     * 设置手持身份证照
     *
     * @param idHoldImg 手持身份证照
     */
    public void setIdHoldImg(String idHoldImg) {
        this.idHoldImg = idHoldImg;
    }

    /**
     * 获取出生年月
     *
     * @return birthday - 出生年月
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置出生年月
     *
     * @param birthday 出生年月
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性別
     *
     * @return gender - 性別
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性別
     *
     * @param gender 性別
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取婚姻状况
     *
     * @return married - 婚姻状况
     */
    public String getMarried() {
        return married;
    }

    /**
     * 设置婚姻状况
     *
     * @param married 婚姻状况
     */
    public void setMarried(String married) {
        this.married = married;
    }

    /**
     * 获取宗教
     *
     * @return religion - 宗教
     */
    public String getReligion() {
        return religion;
    }

    /**
     * 设置宗教
     *
     * @param religion 宗教
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * 获取文化程度
     *
     * @return education - 文化程度
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置文化程度
     *
     * @param education 文化程度
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取居住地址
     *
     * @return live_address - 居住地址
     */
    public String getLiveAddress() {
        return liveAddress;
    }

    /**
     * 设置居住地址
     *
     * @param liveAddress 居住地址
     */
    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    /**
     * 获取职业:0未填写 1白领、2商人、3无业、4学生
     *
     * @return profession - 职业:0未填写 1白领、2商人、3无业、4学生
     */
    public Integer getProfession() {
        return profession;
    }

    /**
     * 设置职业:0未填写 1白领、2商人、3无业、4学生
     *
     * @param profession 职业:0未填写 1白领、2商人、3无业、4学生
     */
    public void setProfession(Integer profession) {
        this.profession = profession;
    }

    /**
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取公司行业
     *
     * @return industry - 公司行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置公司行业
     *
     * @param industry 公司行业
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取职位
     *
     * @return position - 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职位
     *
     * @param position 职位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取平均月收入
     *
     * @return month_income - 平均月收入
     */
    public String getMonthIncome() {
        return monthIncome;
    }

    /**
     * 设置平均月收入
     *
     * @param monthIncome 平均月收入
     */
    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }

    /**
     * 获取公司电话
     *
     * @return company_phone - 公司电话
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * 设置公司电话
     *
     * @param companyPhone 公司电话
     */
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    /**
     * 获取公司地址[省份+城市+街道]
     *
     * @return company_address - 公司地址[省份+城市+街道]
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 设置公司地址[省份+城市+街道]
     *
     * @param companyAddress 公司地址[省份+城市+街道]
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * 获取公司人数
     *
     * @return company_persons - 公司人数
     */
    public String getCompanyPersons() {
        return companyPersons;
    }

    /**
     * 设置公司人数
     *
     * @param companyPersons 公司人数
     */
    public void setCompanyPersons(String companyPersons) {
        this.companyPersons = companyPersons;
    }

    /**
     * 获取无业工作时长
     *
     * @return not_job_time - 无业工作时长
     */
    public String getNotJobTime() {
        return notJobTime;
    }

    /**
     * 设置无业工作时长
     *
     * @param notJobTime 无业工作时长
     */
    public void setNotJobTime(String notJobTime) {
        this.notJobTime = notJobTime;
    }

    /**
     * 获取上一份工作所属行业
     *
     * @return last_industry - 上一份工作所属行业
     */
    public String getLastIndustry() {
        return lastIndustry;
    }

    /**
     * 设置上一份工作所属行业
     *
     * @param lastIndustry 上一份工作所属行业
     */
    public void setLastIndustry(String lastIndustry) {
        this.lastIndustry = lastIndustry;
    }

    /**
     * 获取上一份工作平均月收入
     *
     * @return last_income - 上一份工作平均月收入
     */
    public String getLastIncome() {
        return lastIncome;
    }

    /**
     * 设置上一份工作平均月收入
     *
     * @param lastIncome 上一份工作平均月收入
     */
    public void setLastIncome(String lastIncome) {
        this.lastIncome = lastIncome;
    }

    /**
     * 获取无业期间月收入来源
     *
     * @return not_job_source - 无业期间月收入来源
     */
    public String getNotJobSource() {
        return notJobSource;
    }

    /**
     * 设置无业期间月收入来源
     *
     * @param notJobSource 无业期间月收入来源
     */
    public void setNotJobSource(String notJobSource) {
        this.notJobSource = notJobSource;
    }

    /**
     * 获取无业期间月收入
     *
     * @return not_job_income - 无业期间月收入
     */
    public String getNotJobIncome() {
        return notJobIncome;
    }

    /**
     * 设置无业期间月收入
     *
     * @param notJobIncome 无业期间月收入
     */
    public void setNotJobIncome(String notJobIncome) {
        this.notJobIncome = notJobIncome;
    }

    /**
     * 获取学校名称
     *
     * @return school_name - 学校名称
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 设置学校名称
     *
     * @param schoolName 学校名称
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 获取学校专业
     *
     * @return school_course - 学校专业
     */
    public String getSchoolCourse() {
        return schoolCourse;
    }

    /**
     * 设置学校专业
     *
     * @param schoolCourse 学校专业
     */
    public void setSchoolCourse(String schoolCourse) {
        this.schoolCourse = schoolCourse;
    }

    /**
     * 获取入学时间
     *
     * @return school_in - 入学时间
     */
    public String getSchoolIn() {
        return schoolIn;
    }

    /**
     * 设置入学时间
     *
     * @param schoolIn 入学时间
     */
    public void setSchoolIn(String schoolIn) {
        this.schoolIn = schoolIn;
    }

    /**
     * 获取毕业时间
     *
     * @return school_out - 毕业时间
     */
    public String getSchoolOut() {
        return schoolOut;
    }

    /**
     * 设置毕业时间
     *
     * @param schoolOut 毕业时间
     */
    public void setSchoolOut(String schoolOut) {
        this.schoolOut = schoolOut;
    }

    /**
     * 获取亲属类型
     *
     * @return relative_type - 亲属类型
     */
    public String getRelativeType() {
        return relativeType;
    }

    /**
     * 设置亲属类型
     *
     * @param relativeType 亲属类型
     */
    public void setRelativeType(String relativeType) {
        this.relativeType = relativeType;
    }

    /**
     * 获取亲属姓名
     *
     * @return relative_name - 亲属姓名
     */
    public String getRelativeName() {
        return relativeName;
    }

    /**
     * 设置亲属姓名
     *
     * @param relativeName 亲属姓名
     */
    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    /**
     * 获取亲属电话
     *
     * @return relative_phone - 亲属电话
     */
    public String getRelativePhone() {
        return relativePhone;
    }

    /**
     * 设置亲属电话
     *
     * @param relativePhone 亲属电话
     */
    public void setRelativePhone(String relativePhone) {
        this.relativePhone = relativePhone;
    }

    /**
     * 获取联系人姓名
     *
     * @return urgent_name - 联系人姓名
     */
    public String getUrgentName() {
        return urgentName;
    }

    /**
     * 设置联系人姓名
     *
     * @param urgentName 联系人姓名
     */
    public void setUrgentName(String urgentName) {
        this.urgentName = urgentName;
    }

    /**
     * 获取联系人号码
     *
     * @return urgent_phone - 联系人号码
     */
    public String getUrgentPhone() {
        return urgentPhone;
    }

    /**
     * 设置联系人号码
     *
     * @param urgentPhone 联系人号码
     */
    public void setUrgentPhone(String urgentPhone) {
        this.urgentPhone = urgentPhone;
    }

    /**
     * 获取持卡人姓名
     *
     * @return card_name - 持卡人姓名
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * 设置持卡人姓名
     *
     * @param cardName 持卡人姓名
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * 获取持卡人身份证号
     *
     * @return card_id - 持卡人身份证号
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设置持卡人身份证号
     *
     * @param cardId 持卡人身份证号
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取持卡人银行
     *
     * @return card_bank - 持卡人银行
     */
    public String getCardBank() {
        return cardBank;
    }

    /**
     * 设置持卡人银行
     *
     * @param cardBank 持卡人银行
     */
    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    /**
     * 获取持卡人账户
     *
     * @return card_account - 持卡人账户
     */
    public String getCardAccount() {
        return cardAccount;
    }

    /**
     * 设置持卡人账户
     *
     * @param cardAccount 持卡人账户
     */
    public void setCardAccount(String cardAccount) {
        this.cardAccount = cardAccount;
    }

    /**
     * 获取资料提交步骤
     *
     * @return cur_step - 资料提交步骤
     */
    public Integer getCurStep() {
        return curStep;
    }

    /**
     * 设置资料提交步骤
     *
     * @param curStep 资料提交步骤
     */
    public void setCurStep(Integer curStep) {
        this.curStep = curStep;
    }

    /**
     * 获取状态:0暂存 1待审核 2 审核通过 3审核失败
     *
     * @return credit_status - 状态:0暂存 1待审核 2 审核通过 3审核失败
     */
    public Integer getCreditStatus() {
        return creditStatus;
    }

    /**
     * 设置状态:0暂存 1待审核 2 审核通过 3审核失败
     *
     * @param creditStatus 状态:0暂存 1待审核 2 审核通过 3审核失败
     */
    public void setCreditStatus(Integer creditStatus) {
        this.creditStatus = creditStatus;
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

    public String getLiveAddressStreet() {
        return liveAddressStreet;
    }

    public void setLiveAddressStreet(String liveAddressStreet) {
        this.liveAddressStreet = liveAddressStreet;
    }

    public String getCompanyAddressStreet() {
        return companyAddressStreet;
    }

    public void setCompanyAddressStreet(String companyAddressStreet) {
        this.companyAddressStreet = companyAddressStreet;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getMonthHistoryImg() {
        return monthHistoryImg;
    }

    public void setMonthHistoryImg(String monthHistoryImg) {
        this.monthHistoryImg = monthHistoryImg;
    }

    public String getWeekHistoryImg() {
        return weekHistoryImg;
    }

    public void setWeekHistoryImg(String weekHistoryImg) {
        this.weekHistoryImg = weekHistoryImg;
    }

    public String getDayHistoryImg() {
        return dayHistoryImg;
    }

    public void setDayHistoryImg(String dayHistoryImg) {
        this.dayHistoryImg = dayHistoryImg;
    }

    public String getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(String accountImg) {
        this.accountImg = accountImg;
    }

    public String getOtherWorkImg() {
        return otherWorkImg;
    }

    public void setOtherWorkImg(String otherWorkImg) {
        this.otherWorkImg = otherWorkImg;
    }

    public String getBankStatementImg() {
        return bankStatementImg;
    }

    public void setBankStatementImg(String bankStatementImg) {
        this.bankStatementImg = bankStatementImg;
    }

    public String getCompanyAffidavitImg() {
        return companyAffidavitImg;
    }

    public void setCompanyAffidavitImg(String companyAffidavitImg) {
        this.companyAffidavitImg = companyAffidavitImg;
    }

    public String getSalaryCertImg() {
        return salaryCertImg;
    }

    public void setSalaryCertImg(String salaryCertImg) {
        this.salaryCertImg = salaryCertImg;
    }

    public String getWorkCardImg() {
        return workCardImg;
    }

    public void setWorkCardImg(String workCardImg) {
        this.workCardImg = workCardImg;
    }

    public String getCreditCardImg() {
        return creditCardImg;
    }

    public void setCreditCardImg(String creditCardImg) {
        this.creditCardImg = creditCardImg;
    }

    public String getKKImg() {
        return KKImg;
    }

    public void setKKImg(String KKImg) {
        this.KKImg = KKImg;
    }

    public String getBPJSImg() {
        return BPJSImg;
    }

    public void setBPJSImg(String BPJSImg) {
        this.BPJSImg = BPJSImg;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}