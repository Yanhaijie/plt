package com.supermoney.open.platform.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_baidu_living")
public class SBaiduLiving {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 来源（1 advance , 2 百度, 3 科大讯飞）
     */
    private Integer source;

    /**
     * 活体分数值
     */
    @Column(name = "face_liveness")
    private BigDecimal faceLiveness;

    /**
     * 万分之一误拒率的阈值
     */
    @Column(name = "frr_1e_2")
    private BigDecimal frr1e2;

    /**
     * 千分之一误拒率的阈值
     */
    @Column(name = "frr_1e_3")
    private BigDecimal frr1e3;

    /**
     * 百分之一误拒率的阈值
     */
    @Column(name = "frr_1e_4")
    private BigDecimal frr1e4;

    /**
     * 	人脸图片的唯一标识
     */
    @Column(name = "face_token")
    private String faceToken;

    /**
     * 	人脸区域离左边界的距离
     */
    @Column(name = "face_left")
    private String faceLeft;

    /**
     * 人脸区域离上边界的距离
     */
    @Column(name = "face_top")
    private String faceTop;

    /**
     * 	人脸区域的宽度
     */
    @Column(name = "face_width")
    private String faceWidth;

    /**
     * 	人脸区域的高度
     */
    @Column(name = "face_height")
    private String faceHeight;

    /**
     * 人脸框相对于竖直方向的顺时针旋转角，[-180,180]
     */
    private String rotation;

    /**
     * 人脸置信度，范围【0~1】，代表这是一张人脸的概率，0最小、1最大。
     */
    @Column(name = "face_probability")
    private String faceProbability;

    /**
     * 三维旋转之左右旋转角[-90(左), 90(右)]
     */
    @Column(name = "face_yaw")
    private String faceYaw;

    /**
     * 三维旋转之俯仰角度[-90(上), 90(下)]
     */
    @Column(name = "face_pitch")
    private String facePitch;

    /**
     * 平面内旋转角[-180(逆时针), 180(顺时针)]
     */
    @Column(name = "face_roll")
    private String faceRoll;

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
     * 获取来源（1 advance , 2 百度, 3 科大讯飞）
     *
     * @return source - 来源（1 advance , 2 百度, 3 科大讯飞）
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置来源（1 advance , 2 百度, 3 科大讯飞）
     *
     * @param source 来源（1 advance , 2 百度, 3 科大讯飞）
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取活体分数值
     *
     * @return face_liveness - 活体分数值
     */
    public BigDecimal getFaceLiveness() {
        return faceLiveness;
    }

    /**
     * 设置活体分数值
     *
     * @param faceLiveness 活体分数值
     */
    public void setFaceLiveness(BigDecimal faceLiveness) {
        this.faceLiveness = faceLiveness;
    }

    /**
     * 获取万分之一误拒率的阈值
     *
     * @return frr_1e_2 - 万分之一误拒率的阈值
     */
    public BigDecimal getFrr1e2() {
        return frr1e2;
    }

    /**
     * 设置万分之一误拒率的阈值
     *
     * @param frr1e2 万分之一误拒率的阈值
     */
    public void setFrr1e2(BigDecimal frr1e2) {
        this.frr1e2 = frr1e2;
    }

    /**
     * 获取千分之一误拒率的阈值
     *
     * @return frr_1e_3 - 千分之一误拒率的阈值
     */
    public BigDecimal getFrr1e3() {
        return frr1e3;
    }

    /**
     * 设置千分之一误拒率的阈值
     *
     * @param frr1e3 千分之一误拒率的阈值
     */
    public void setFrr1e3(BigDecimal frr1e3) {
        this.frr1e3 = frr1e3;
    }

    /**
     * 获取百分之一误拒率的阈值
     *
     * @return frr_1e_4 - 百分之一误拒率的阈值
     */
    public BigDecimal getFrr1e4() {
        return frr1e4;
    }

    /**
     * 设置百分之一误拒率的阈值
     *
     * @param frr1e4 百分之一误拒率的阈值
     */
    public void setFrr1e4(BigDecimal frr1e4) {
        this.frr1e4 = frr1e4;
    }

    /**
     * 获取	人脸图片的唯一标识
     *
     * @return face_token - 	人脸图片的唯一标识
     */
    public String getFaceToken() {
        return faceToken;
    }

    /**
     * 设置	人脸图片的唯一标识
     *
     * @param faceToken 	人脸图片的唯一标识
     */
    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    /**
     * 获取	人脸区域离左边界的距离
     *
     * @return face_left - 	人脸区域离左边界的距离
     */
    public String getFaceLeft() {
        return faceLeft;
    }

    /**
     * 设置	人脸区域离左边界的距离
     *
     * @param faceLeft 	人脸区域离左边界的距离
     */
    public void setFaceLeft(String faceLeft) {
        this.faceLeft = faceLeft;
    }

    /**
     * 获取人脸区域离上边界的距离
     *
     * @return face_top - 人脸区域离上边界的距离
     */
    public String getFaceTop() {
        return faceTop;
    }

    /**
     * 设置人脸区域离上边界的距离
     *
     * @param faceTop 人脸区域离上边界的距离
     */
    public void setFaceTop(String faceTop) {
        this.faceTop = faceTop;
    }

    /**
     * 获取	人脸区域的宽度
     *
     * @return face_width - 	人脸区域的宽度
     */
    public String getFaceWidth() {
        return faceWidth;
    }

    /**
     * 设置	人脸区域的宽度
     *
     * @param faceWidth 	人脸区域的宽度
     */
    public void setFaceWidth(String faceWidth) {
        this.faceWidth = faceWidth;
    }

    /**
     * 获取	人脸区域的高度
     *
     * @return face_height - 	人脸区域的高度
     */
    public String getFaceHeight() {
        return faceHeight;
    }

    /**
     * 设置	人脸区域的高度
     *
     * @param faceHeight 	人脸区域的高度
     */
    public void setFaceHeight(String faceHeight) {
        this.faceHeight = faceHeight;
    }

    /**
     * 获取人脸框相对于竖直方向的顺时针旋转角，[-180,180]
     *
     * @return rotation - 人脸框相对于竖直方向的顺时针旋转角，[-180,180]
     */
    public String getRotation() {
        return rotation;
    }

    /**
     * 设置人脸框相对于竖直方向的顺时针旋转角，[-180,180]
     *
     * @param rotation 人脸框相对于竖直方向的顺时针旋转角，[-180,180]
     */
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    /**
     * 获取人脸置信度，范围【0~1】，代表这是一张人脸的概率，0最小、1最大。
     *
     * @return face_probability - 人脸置信度，范围【0~1】，代表这是一张人脸的概率，0最小、1最大。
     */
    public String getFaceProbability() {
        return faceProbability;
    }

    /**
     * 设置人脸置信度，范围【0~1】，代表这是一张人脸的概率，0最小、1最大。
     *
     * @param faceProbability 人脸置信度，范围【0~1】，代表这是一张人脸的概率，0最小、1最大。
     */
    public void setFaceProbability(String faceProbability) {
        this.faceProbability = faceProbability;
    }

    /**
     * 获取三维旋转之左右旋转角[-90(左), 90(右)]
     *
     * @return face_yaw - 三维旋转之左右旋转角[-90(左), 90(右)]
     */
    public String getFaceYaw() {
        return faceYaw;
    }

    /**
     * 设置三维旋转之左右旋转角[-90(左), 90(右)]
     *
     * @param faceYaw 三维旋转之左右旋转角[-90(左), 90(右)]
     */
    public void setFaceYaw(String faceYaw) {
        this.faceYaw = faceYaw;
    }

    /**
     * 获取三维旋转之俯仰角度[-90(上), 90(下)]
     *
     * @return face_pitch - 三维旋转之俯仰角度[-90(上), 90(下)]
     */
    public String getFacePitch() {
        return facePitch;
    }

    /**
     * 设置三维旋转之俯仰角度[-90(上), 90(下)]
     *
     * @param facePitch 三维旋转之俯仰角度[-90(上), 90(下)]
     */
    public void setFacePitch(String facePitch) {
        this.facePitch = facePitch;
    }

    /**
     * 获取平面内旋转角[-180(逆时针), 180(顺时针)]
     *
     * @return face_roll - 平面内旋转角[-180(逆时针), 180(顺时针)]
     */
    public String getFaceRoll() {
        return faceRoll;
    }

    /**
     * 设置平面内旋转角[-180(逆时针), 180(顺时针)]
     *
     * @param faceRoll 平面内旋转角[-180(逆时针), 180(顺时针)]
     */
    public void setFaceRoll(String faceRoll) {
        this.faceRoll = faceRoll;
    }
}