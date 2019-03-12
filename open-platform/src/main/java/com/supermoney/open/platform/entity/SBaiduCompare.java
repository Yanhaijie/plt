package com.supermoney.open.platform.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_baidu_compare")
public class SBaiduCompare {
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
     * 	人脸相似度得分，推荐阈值80分
     */
    @Column(name = "compare_score")
    private String compareScore;

    /**
     * 	人脸的唯一标志1
     */
    @Column(name = "face_token1")
    private String faceToken1;

    /**
     * 	人脸的唯一标志2
     */
    @Column(name = "face_token2")
    private String faceToken2;

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
     * 获取	人脸相似度得分，推荐阈值80分
     *
     * @return compare_score - 	人脸相似度得分，推荐阈值80分
     */
    public String getCompareScore() {
        return compareScore;
    }

    /**
     * 设置	人脸相似度得分，推荐阈值80分
     *
     * @param compareScore 	人脸相似度得分，推荐阈值80分
     */
    public void setCompareScore(String compareScore) {
        this.compareScore = compareScore;
    }

    /**
     * 获取	人脸的唯一标志1
     *
     * @return face_token1 - 	人脸的唯一标志1
     */
    public String getFaceToken1() {
        return faceToken1;
    }

    /**
     * 设置	人脸的唯一标志1
     *
     * @param faceToken1 	人脸的唯一标志1
     */
    public void setFaceToken1(String faceToken1) {
        this.faceToken1 = faceToken1;
    }

    /**
     * 获取	人脸的唯一标志2
     *
     * @return face_token2 - 	人脸的唯一标志2
     */
    public String getFaceToken2() {
        return faceToken2;
    }

    /**
     * 设置	人脸的唯一标志2
     *
     * @param faceToken2 	人脸的唯一标志2
     */
    public void setFaceToken2(String faceToken2) {
        this.faceToken2 = faceToken2;
    }
}