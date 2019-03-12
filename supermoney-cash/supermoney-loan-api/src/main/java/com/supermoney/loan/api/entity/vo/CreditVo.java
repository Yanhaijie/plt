package com.supermoney.loan.api.entity.vo;

import com.supermoney.loan.api.entity.SAtCreditInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(value = "CreditVo", description = "征信信息")
public class CreditVo  {
    //================other===========================
    /**
     * 资料ID
     */
    @ApiModelProperty(value = "资料ID")
    @Column(name = "id")
    private Integer id;

    /**
     * 身份认证ID
     */
    private  Integer identityId;

    /**
     * 资料提交步骤
     */
    @ApiModelProperty(value = "资料提交步骤")
    @Column(name = "cur_step")
    private Integer curStep;

    /**
     * 是否提交
     */
    @ApiModelProperty(value = "是否提交资料 提交:true")
    private  String  isComit;

    //=====================setp 1===========================
    /**
     * 姓名
     */
    @Column(name = "real_namne")
    @ApiModelProperty(value = "姓名-setp1")
    private String realNamne;
    /**
     * 身份证号码
     */
    @Column(name = "id_number-setp1")
    @ApiModelProperty(value = "身份证号码-setp1")
    private String idNumber;
    /**
     * 身份证正面照
     */
    @ApiModelProperty(value = "身份证正面照-setp1")
    @Column(name = "id_front_img")
    private String idFrontImg;
    /**
     * 手持身份证照
     */
    @ApiModelProperty(value = "手持身份证照-setp1")
    @Column(name = "id_hold_img")
    private  String idHoldImg;

    //=====================setp 2===========================
    /**
     * 出生年月
     */
    @ApiModelProperty(value = "出生年月-setp2")
    @NotBlank(message = "birthday is  not null")
    private String birthday;
    /**
     * 性別
     */
    @ApiModelProperty(value = "性別-setp2")
    @NotBlank(message = "gender is  not null")
    private String gender;
    /**
     * 婚姻状况
     */
    @ApiModelProperty(value = "婚姻状况-setp2")
    @NotBlank(message = "married is  not null")
    private String married;
    /**
     * 孩子数量
     */
    @ApiModelProperty(value = "孩子数量-setp2")
    private  String children;
    /**
     * 宗教
     */
    @ApiModelProperty(value = "宗教-setp2")
    @NotBlank(message = "religion is  not null")
    private String religion;
    /**
     * 学历-文化程度
     */
    @ApiModelProperty(value = "学历-文化程度-setp2")
    @NotBlank(message = "education is  not null")
    private String education;

    /**
     * 居住地址
     */
    @ApiModelProperty(value = "居住地址-setp2")
    @Column(name = "live_address")
    @NotBlank(message = "liveAddress is  not null")
    private String liveAddress;

    /**
     * 居住地址
     */
    @ApiModelProperty(value = "居住地址街道-setp2")
    @Column(name = "live_address_street")
    @NotBlank(message = "liveAddress is  not null")
    private String liveAddressStreet;

   //=====================setp 3===========================
    /**
     * 所属职业-new
     */
   @ApiModelProperty(value = "职业-setp3")
    private  Integer    profession;
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称-setp3")
    @Column(name = "company_name")
    private String companyName;

    /**
     * 所属行业-公司行业
     */
    @ApiModelProperty(value = "所属行业-公司行业-setp3")
    private String industry;
    /**
     * 职位
     */
    @ApiModelProperty(value = "职位-setp3")
    private String position;
    /**
     * 平均月收入
     */
    @ApiModelProperty(value = "平均月收入-setp3")
    @Column(name = "month_income")
    private String monthIncome;
    /**
     * 公司电话
     */
    @ApiModelProperty(value = "公司电话-setp3")
    @Column(name = "company_phone")
    private String companyPhone;
    /**
     * 公司地址[省份+城市]
     */
    @ApiModelProperty(value = "公司地址[省份+城市]-setp3")
    @Column(name = "company_address")
    private String companyAddress;

    /**
     * 公司地址[街道]
     */
    @ApiModelProperty(value = "公司地址[街道]-setp3")
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
    //=====================setp 4===========================
    /**
     * 亲属类型-new
     */
    @ApiModelProperty(value = "亲属类型-setp4")
    @NotBlank(message = "relativeType is  not null")
    private  String relativeType;
    /**
     * 亲属姓名-new
     */
    @ApiModelProperty(value = "亲属姓名-setp4")
    @NotBlank(message = "relativeName is  not null")
    private  String relativeName;

    /**
     * 亲属电话-new
     */
    @ApiModelProperty(value = "亲属电话-setp4")
    @NotBlank(message = "relativePhone is  not null")
    private  String relativePhone;

    /**
     * 朋友-联系人姓名
     */
    @ApiModelProperty(value = "朋友-联系人姓名-setp4")
    @Column(name = "urgent_name")
    @NotBlank(message = "urgentName is  not null")
    private String urgentName;
    /**
     * 朋友-联系人号码
     */
    @ApiModelProperty(value = "朋友-联系人号码-setp4")
    @Column(name = "urgent_phone")
    @NotBlank(message = "urgentPhone is  not null")
    private String urgentPhone;
    //================setp 5===========================
    /**
     * 持卡人姓名
     */
    @ApiModelProperty(value = "持卡人姓名-setp5")
    @Column(name = "card_name")
    @NotBlank(message = "cardName is  not null")
    private String cardName;
    /**
     * 持卡人身份证号码
     */
    @ApiModelProperty(value = "持卡人身份证号码-setp5")
    @Column(name = "card_id")
    @NotBlank(message = "cardId is  not null")
    private  String cardId;
    /**
     * 持卡人银行
     */
    @ApiModelProperty(value = "持卡人银行-setp5")
    @Column(name = "card_bank")
    @NotBlank(message = "cardBank is  not null")
    private String cardBank;
    /**
     * 持卡人账号
     */
    @ApiModelProperty(value = "持卡人账号-setp5")
    @Column(name = "card_account")
    @NotBlank(message = "cardAccount is  not null")
    private  String cardAccount;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getRealNamne() {
        return realNamne;
    }

    public void setRealNamne(String realNamne) {
        this.realNamne = realNamne;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }



    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }


    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }


    public String getUrgentName() {
        return urgentName;
    }

    public void setUrgentName(String urgentName) {
        this.urgentName = urgentName;
    }


    public String getUrgentPhone() {
        return urgentPhone;
    }

    public void setUrgentPhone(String urgentPhone) {
        this.urgentPhone = urgentPhone;
    }


    public Integer getCurStep() {
        return curStep;
    }

    public void setCurStep(Integer curStep) {
        this.curStep = curStep;
    }

    public Integer getProfession() {
        return profession;
    }

    public void setProfession(Integer profession) {
        this.profession = profession;
    }

    public String getRelativeType() {
        return relativeType;
    }

    public void setRelativeType(String relativeType) {
        this.relativeType = relativeType;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public String getRelativePhone() {
        return relativePhone;
    }

    public void setRelativePhone(String relativePhone) {
        this.relativePhone = relativePhone;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public String getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(String cardAccount) {
        this.cardAccount = cardAccount;
    }

    public String getIdFrontImg() {
        return idFrontImg;
    }

    public void setIdFrontImg(String idFrontImg) {
        this.idFrontImg = idFrontImg;
    }

    public String getIdHoldImg() {
        return idHoldImg;
    }

    public void setIdHoldImg(String idHoldImg) {
        this.idHoldImg = idHoldImg;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public String getCompanyPersons() {
        return companyPersons;
    }

    public void setCompanyPersons(String companyPersons) {
        this.companyPersons = companyPersons;
    }

    public String getNotJobTime() {
        return notJobTime;
    }

    public void setNotJobTime(String notJobTime) {
        this.notJobTime = notJobTime;
    }

    public String getLastIndustry() {
        return lastIndustry;
    }

    public void setLastIndustry(String lastIndustry) {
        this.lastIndustry = lastIndustry;
    }

    public String getLastIncome() {
        return lastIncome;
    }

    public void setLastIncome(String lastIncome) {
        this.lastIncome = lastIncome;
    }

    public String getNotJobSource() {
        return notJobSource;
    }

    public void setNotJobSource(String notJobSource) {
        this.notJobSource = notJobSource;
    }

    public String getNotJobIncome() {
        return notJobIncome;
    }

    public void setNotJobIncome(String notJobIncome) {
        this.notJobIncome = notJobIncome;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolCourse() {
        return schoolCourse;
    }

    public void setSchoolCourse(String schoolCourse) {
        this.schoolCourse = schoolCourse;
    }

    public String getSchoolIn() {
        return schoolIn;
    }

    public void setSchoolIn(String schoolIn) {
        this.schoolIn = schoolIn;
    }

    public String getSchoolOut() {
        return schoolOut;
    }

    public void setSchoolOut(String schoolOut) {
        this.schoolOut = schoolOut;
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

    public String getIsComit() {
        return isComit;
    }

    public void setIsComit(String isComit) {
        this.isComit = isComit;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}
