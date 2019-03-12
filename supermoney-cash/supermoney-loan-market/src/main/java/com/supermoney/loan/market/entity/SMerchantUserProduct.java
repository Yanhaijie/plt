package com.supermoney.loan.market.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_merchant_user_product")
public class SMerchantUserProduct {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商户ID-生成
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 产品编码
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 产品状态 0可用 1不可用 
     */
    @Column(name = "product_status")
    private Integer productStatus;

    /**
     * 剩余次数
     */
    @Column(name = "last_num")
    private Integer lastNum;

    /**
     * 已用次数
     */
    @Column(name = "use_num")
    private Integer useNum;

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
     * 获取商户ID-生成
     *
     * @return merchant_id - 商户ID-生成
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户ID-生成
     *
     * @param merchantId 商户ID-生成
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取产品编码
     *
     * @return product_code - 产品编码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编码
     *
     * @param productCode 产品编码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取产品状态 0可用 1不可用 
     *
     * @return product_status - 产品状态 0可用 1不可用 
     */
    public Integer getProductStatus() {
        return productStatus;
    }

    /**
     * 设置产品状态 0可用 1不可用 
     *
     * @param productStatus 产品状态 0可用 1不可用 
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 获取剩余次数
     *
     * @return last_num - 剩余次数
     */
    public Integer getLastNum() {
        return lastNum;
    }

    /**
     * 设置剩余次数
     *
     * @param lastNum 剩余次数
     */
    public void setLastNum(Integer lastNum) {
        this.lastNum = lastNum;
    }

    /**
     * 获取已用次数
     *
     * @return use_num - 已用次数
     */
    public Integer getUseNum() {
        return useNum;
    }

    /**
     * 设置已用次数
     *
     * @param useNum 已用次数
     */
    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
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
}