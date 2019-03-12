package com.supermoney.loan.api.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_at_living_info")
public class SAtLivingInfo {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 阀值
     */
    private String faceliveness;

    /**
     * 人脸图片
     */
    @Column(name = "face_img")
    private String faceImg;

    /**
     * 身份证图片
     */
    @Column(name = "id_img")
    private String idImg;

    /**
     * 是否通过(0 未通过， 1 通过)
     */
    @Column(name = "is_pass")
    private Byte isPass;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取阀值
     *
     * @return faceliveness - 阀值
     */
    public String getFaceliveness() {
        return faceliveness;
    }

    /**
     * 设置阀值
     *
     * @param faceliveness 阀值
     */
    public void setFaceliveness(String faceliveness) {
        this.faceliveness = faceliveness;
    }

    /**
     * 获取人脸图片
     *
     * @return face_img - 人脸图片
     */
    public String getFaceImg() {
        return faceImg;
    }

    /**
     * 设置人脸图片
     *
     * @param faceImg 人脸图片
     */
    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    /**
     * 获取是否通过(0 未通过， 1 通过)
     *
     * @return is_pass - 是否通过(0 未通过， 1 通过)
     */
    public Byte getIsPass() {
        return isPass;
    }

    /**
     * 设置是否通过(0 未通过， 1 通过)
     *
     * @param isPass 是否通过(0 未通过， 1 通过)
     */
    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
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

    public String getIdImg() {
        return idImg;
    }

    public void setIdImg(String idImg) {
        this.idImg = idImg;
    }
}