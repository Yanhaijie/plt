package com.supermoney.loan.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_exchange_rate")
public class SExchangeRate {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 兑换名称
     */
    @Column(name = "exchange_name")
    private String exchangeName;

    /**
     * 第一币种
     */
    @Column(name = "first_currency")
    private String firstCurrency;

    /**
     * 第二币种
     */
    @Column(name = "second_currency")
    private String secondCurrency;

    /**
     * 兑换比例 [第一币种] 兑换 [第二币种]
     */
    private BigDecimal proportion;

    /**
     * 1单位兑换值 例: 1美元等于 6.24 RMB
     */
    @Column(name = "exchange_val")
    private BigDecimal exchangeVal;

    /**
     * 状态：0使用、1停止
     */
    @Column(name = "exchange_status")
    private Integer exchangeStatus;

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
     * 货币符号
     */
    private  String symbol;

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
     * 获取兑换名称
     *
     * @return exchange_name - 兑换名称
     */
    public String getExchangeName() {
        return exchangeName;
    }

    /**
     * 设置兑换名称
     *
     * @param exchangeName 兑换名称
     */
    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    /**
     * 获取第一币种
     *
     * @return first_currency - 第一币种
     */
    public String getFirstCurrency() {
        return firstCurrency;
    }

    /**
     * 设置第一币种
     *
     * @param firstCurrency 第一币种
     */
    public void setFirstCurrency(String firstCurrency) {
        this.firstCurrency = firstCurrency;
    }

    /**
     * 获取第二币种
     *
     * @return second_currency - 第二币种
     */
    public String getSecondCurrency() {
        return secondCurrency;
    }

    /**
     * 设置第二币种
     *
     * @param secondCurrency 第二币种
     */
    public void setSecondCurrency(String secondCurrency) {
        this.secondCurrency = secondCurrency;
    }

    /**
     * 获取兑换比例 [第一币种] 兑换 [第二币种]
     *
     * @return proportion - 兑换比例 [第一币种] 兑换 [第二币种]
     */
    public BigDecimal getProportion() {
        return proportion;
    }

    /**
     * 设置兑换比例 [第一币种] 兑换 [第二币种]
     *
     * @param proportion 兑换比例 [第一币种] 兑换 [第二币种]
     */
    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    /**
     * 获取1单位兑换值 例: 1美元等于 6.24 RMB
     *
     * @return exchange_val - 1单位兑换值 例: 1美元等于 6.24 RMB
     */
    public BigDecimal getExchangeVal() {
        return exchangeVal;
    }

    /**
     * 设置1单位兑换值 例: 1美元等于 6.24 RMB
     *
     * @param exchangeVal 1单位兑换值 例: 1美元等于 6.24 RMB
     */
    public void setExchangeVal(BigDecimal exchangeVal) {
        this.exchangeVal = exchangeVal;
    }

    /**
     * 获取状态：0使用、1停止
     *
     * @return exchange_status - 状态：0使用、1停止
     */
    public Integer getExchangeStatus() {
        return exchangeStatus;
    }

    /**
     * 设置状态：0使用、1停止
     *
     * @param exchangeStatus 状态：0使用、1停止
     */
    public void setExchangeStatus(Integer exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}