package com.supermoney.loan.api.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_at_identity_info")
public class SAtIdentityInfo {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 身份编号
     */
    @Column(name = "identity_id")
    private Integer identityId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 证件标识
     */
    @Column(name = "identity_tag")
    private String identityTag;

    /**
     * 证件号
     */
    private String nik;

    /**
     * 姓名
     */
    private String namne;

    /**
     * 血型
     */
    @Column(name = "blood_type")
    private String bloodType;

    /**
     * 宗教
     */
    private String religion;

    /**
     * 性別
     */
    private String gender;

    /**
     * 出生地点和日期
     */
    @Column(name = "place_date_birth")
    private String placeDateBirth;

    /**
     * RT/RW
     */
    @Column(name = "rt_rw")
    private String rtRw;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 省
     */
    @Column(name = "Province")
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String region;

    /**
     * 村
     */
    private String village;

    /**
     * 到期日期
     */
    @Column(name = "expiry_date")
    private String expiryDate;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 国籍
     */
    @Column(name = "nationality")
    private String nationality;

    /**
     * 婚姻状况
     */
    @Column(name = "marital_status")
    private String maritalStatus;

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
     * 状态:0有效完成 1 无效
     */
    @Column(name = "identity_info_status")
    private Integer identityInfoStatus;

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
     * 获取身份编号
     *
     * @return identity_id - 身份编号
     */
    public Integer getIdentityId() {
        return identityId;
    }

    /**
     * 设置身份编号
     *
     * @param identityId 身份编号
     */
    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
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
     * 获取证件标识
     *
     * @return identity_tag - 证件标识
     */
    public String getIdentityTag() {
        return identityTag;
    }

    /**
     * 设置证件标识
     *
     * @param identityTag 证件标识
     */
    public void setIdentityTag(String identityTag) {
        this.identityTag = identityTag;
    }

    /**
     * 获取证件号
     *
     * @return nik - 证件号
     */
    public String getNik() {
        return nik;
    }

    /**
     * 设置证件号
     *
     * @param nik 证件号
     */
    public void setNik(String nik) {
        this.nik = nik;
    }

    /**
     * 获取姓名
     *
     * @return namne - 姓名
     */
    public String getNamne() {
        return namne;
    }

    /**
     * 设置姓名
     *
     * @param namne 姓名
     */
    public void setNamne(String namne) {
        this.namne = namne;
    }

    /**
     * 获取血型
     *
     * @return blood_type - 血型
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * 设置血型
     *
     * @param bloodType 血型
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
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
     * 获取出生地点和日期
     *
     * @return place_date_birth - 出生地点和日期
     */
    public String getPlaceDateBirth() {
        return placeDateBirth;
    }

    /**
     * 设置出生地点和日期
     *
     * @param placeDateBirth 出生地点和日期
     */
    public void setPlaceDateBirth(String placeDateBirth) {
        this.placeDateBirth = placeDateBirth;
    }

    /**
     * 获取RT/RW
     *
     * @return rt_rw - RT/RW
     */
    public String getRtRw() {
        return rtRw;
    }

    /**
     * 设置RT/RW
     *
     * @param rtRw RT/RW
     */
    public void setRtRw(String rtRw) {
        this.rtRw = rtRw;
    }

    /**
     * 获取职业
     *
     * @return occupation - 职业
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * 设置职业
     *
     * @param occupation 职业
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * 获取省
     *
     * @return Province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区域
     *
     * @return region - 区域
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置区域
     *
     * @param region 区域
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 获取村
     *
     * @return village - 村
     */
    public String getVillage() {
        return village;
    }

    /**
     * 设置村
     *
     * @param village 村
     */
    public void setVillage(String village) {
        this.village = village;
    }

    /**
     * 获取到期日期
     *
     * @return expiry_date - 到期日期
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * 设置到期日期
     *
     * @param expiryDate 到期日期
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 获取婚姻状况
     *
     * @return marital_status - 婚姻状况
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 设置婚姻状况
     *
     * @param maritalStatus 婚姻状况
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
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
     * 获取状态:0有效完成 1 无效
     *
     * @return identity_info_status - 状态:0有效完成 1 无效
     */
    public Integer getIdentityInfoStatus() {
        return identityInfoStatus;
    }

    /**
     * 设置状态:0有效完成 1 无效
     *
     * @param identityInfoStatus 状态:0有效完成 1 无效
     */
    public void setIdentityInfoStatus(Integer identityInfoStatus) {
        this.identityInfoStatus = identityInfoStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}