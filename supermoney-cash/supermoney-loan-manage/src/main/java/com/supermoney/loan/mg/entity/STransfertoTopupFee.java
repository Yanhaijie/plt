package com.supermoney.loan.mg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_transferto_topup_fee")
public class STransfertoTopupFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 当地币种
     */
    private Integer product;

    /**
     * 当地币种
     */
    @Column(name = "transaction_price")
    private Integer transactionPrice;

    /**
     * 美元
     */
    private String fee;

    /**
     * 美元
     */
    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    /**
     * 美元
     */
    @Column(name = "wholesale_price")
    private BigDecimal wholesalePrice;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 1:印尼
     */
    @Column(name = "phone_number_type")
    private Integer phoneNumberType;

    private String symbol;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取当地币种
     *
     * @return product - 当地币种
     */
    public Integer getProduct() {
        return product;
    }

    /**
     * 设置当地币种
     *
     * @param product 当地币种
     */
    public void setProduct(Integer product) {
        this.product = product;
    }

    /**
     * 获取当地币种
     *
     * @return transaction_price - 当地币种
     */
    public Integer getTransactionPrice() {
        return transactionPrice;
    }

    /**
     * 设置当地币种
     *
     * @param transactionPrice 当地币种
     */
    public void setTransactionPrice(Integer transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    /**
     * 获取美元
     *
     * @return fee - 美元
     */
    public String getFee() {
        return fee;
    }

    /**
     * 设置美元
     *
     * @param fee 美元
     */
    public void setFee(String fee) {
        this.fee = fee;
    }

    /**
     * 获取美元
     *
     * @return retail_price - 美元
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 设置美元
     *
     * @param retailPrice 美元
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 获取美元
     *
     * @return wholesale_price - 美元
     */
    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    /**
     * 设置美元
     *
     * @param wholesalePrice 美元
     */
    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取1:印尼
     *
     * @return phone_number_type - 1:印尼
     */
    public Integer getPhoneNumberType() {
        return phoneNumberType;
    }

    /**
     * 设置1:印尼
     *
     * @param phoneNumberType 1:印尼
     */
    public void setPhoneNumberType(Integer phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    /**
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}