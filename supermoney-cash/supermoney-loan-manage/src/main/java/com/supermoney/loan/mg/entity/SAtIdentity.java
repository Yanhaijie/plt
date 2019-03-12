package com.supermoney.loan.mg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "s_at_identity")
public class SAtIdentity {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 真实名称
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 证件号码
     */
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 证件正面图片
     */
    @Column(name = "img_front")
    private String imgFront;

    /**
     * 证件反面图片
     */
    @Column(name = "img_back")
    private String imgBack;

    /**
     * 手持证件照
     */
    @Column(name = "img_hold")
    private String imgHold;

    /**
     * 评分
     */
    @Column(name = "identity_score")
    private Integer identityScore;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 备注
     */
    private String remark;

    /**
     * 资料来源
     */
    @Column(name = "info_source")
    private String infoSource;

    /**
     * 状态:0待审核1审核通过2审核失败
     */
    @Column(name = "identity_status")
    private Integer identityStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 操作人
     */
    private String opt;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 国家地区编码
     */
    @Column(name = "area_code")
    private Integer areaCode;

    /**
     * 信息编号
     */
    @Column(name = "identity_info_id")
    private Integer identityInfoId;

    /**
     * 校验状态:0 未校验 ，1 名字和ID号成功  ，2 名字对比失败  ，3 ID号对比失败， 4  ID 和 名字都对比失败 , 5  三方验证失败 ，6 证件照获取失败
     */
    @Column(name = "check_status")
    private Integer checkStatus;

    /**
     * 认证类型（0 身份认证（旧数据）， 1 贷款认证）
     */
    @Column(name = "cert_type")
    private Integer certType;

    /**
     * 是否在黑名单中（0 否， 1 是）
     */
    @Column(name = "in_blacklist")
    private Integer inBlacklist;

    /**
     * 身份证 官网审核（0 未审核 ， 1 通过 ， 2 未通过）
     */
    @Column(name = "id_check")
    private Integer idCheck;

    /**
     * 是否OCR检测过 （0 未审核 ， 1 通过 ， 2 未通过）
     */
    @Column(name = "ocr_check")
    private Integer ocrCheck;


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
     * 获取真实名称
     *
     * @return real_name - 真实名称
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实名称
     *
     * @param realName 真实名称
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取证件号码
     *
     * @return id_number - 证件号码
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置证件号码
     *
     * @param idNumber 证件号码
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取证件正面图片
     *
     * @return img_front - 证件正面图片
     */
    public String getImgFront() {
        return imgFront;
    }

    /**
     * 设置证件正面图片
     *
     * @param imgFront 证件正面图片
     */
    public void setImgFront(String imgFront) {
        this.imgFront = imgFront;
    }

    /**
     * 获取证件反面图片
     *
     * @return img_back - 证件反面图片
     */
    public String getImgBack() {
        return imgBack;
    }

    /**
     * 设置证件反面图片
     *
     * @param imgBack 证件反面图片
     */
    public void setImgBack(String imgBack) {
        this.imgBack = imgBack;
    }

    /**
     * 获取评分
     *
     * @return identity_score - 评分
     */
    public Integer getIdentityScore() {
        return identityScore;
    }

    /**
     * 设置评分
     *
     * @param identityScore 评分
     */
    public void setIdentityScore(Integer identityScore) {
        this.identityScore = identityScore;
    }

    /**
     * 获取联系电话
     *
     * @return mobile - 联系电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置联系电话
     *
     * @param mobile 联系电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取资料来源
     *
     * @return info_source - 资料来源
     */
    public String getInfoSource() {
        return infoSource;
    }

    /**
     * 设置资料来源
     *
     * @param infoSource 资料来源
     */
    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    /**
     * 获取状态:0待审核1审核通过2审核失败
     *
     * @return identity_status - 状态:0待审核1审核通过2审核失败
     */
    public Integer getIdentityStatus() {
        return identityStatus;
    }

    /**
     * 设置状态:0待审核1审核通过2审核失败
     *
     * @param identityStatus 状态:0待审核1审核通过2审核失败
     */
    public void setIdentityStatus(Integer identityStatus) {
        this.identityStatus = identityStatus;
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

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getIdentityInfoId() {
        return identityInfoId;
    }

    public void setIdentityInfoId(Integer identityInfoId) {
        this.identityInfoId = identityInfoId;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getImgHold() {
        return imgHold;
    }

    public void setImgHold(String imgHold) {
        this.imgHold = imgHold;
    }

    public Integer getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(Integer idCheck) {
        this.idCheck = idCheck;
    }

    public Integer getInBlacklist() {
        return inBlacklist;
    }

    public void setInBlacklist(Integer inBlacklist) {
        this.inBlacklist = inBlacklist;
    }

    public Integer getOcrCheck() {
        return ocrCheck;
    }

    public void setOcrCheck(Integer ocrCheck) {
        this.ocrCheck = ocrCheck;
    }
}