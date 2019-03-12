package com.supers.p2p.assets.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_item_pool")
public class SItemPool {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 资金方企业编号
     */
    @Column(name = "found_company_id")
    private Integer foundCompanyId;

    /**
     * 使用状态，0 使用中，1停用
     */
    @Column(name = "use_status")
    private Integer useStatus;

    /**
     * 交易状态，0 购物车状态（发表中），1满标
     */
    @Column(name = "transaction_status")
    private Integer transactionStatus;

    /**
     * 锁定时间，添加进购物车的时间
     */
    @Column(name = "lock_time")
    private Date lockTime;

    /**
     * 确认时间，满标时间
     */
    @Column(name = "comfirm_time")
    private Date comfirmTime;

    /**
     * 锁定时间小时单位
     */
    @Column(name = "lock_duration")
    private Integer lockDuration;

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
     * 获取项目id
     *
     * @return item_id - 项目id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 设置项目id
     *
     * @param itemId 项目id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取资金方企业编号
     *
     * @return found_company_id - 资金方企业编号
     */
    public Integer getFoundCompanyId() {
        return foundCompanyId;
    }

    /**
     * 设置资金方企业编号
     *
     * @param foundCompanyId 资金方企业编号
     */
    public void setFoundCompanyId(Integer foundCompanyId) {
        this.foundCompanyId = foundCompanyId;
    }

    /**
     * 获取使用状态，0 使用中，1停用
     *
     * @return use_status - 使用状态，0 使用中，1停用
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**
     * 设置使用状态，0 使用中，1停用
     *
     * @param useStatus 使用状态，0 使用中，1停用
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * 获取交易状态，0 购物车状态（发表中），1满标
     *
     * @return transaction_status - 交易状态，0 购物车状态（发表中），1满标
     */
    public Integer getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * 设置交易状态，0 购物车状态（发表中），1满标
     *
     * @param transactionStatus 交易状态，0 购物车状态（发表中），1满标
     */
    public void setTransactionStatus(Integer transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /**
     * 获取锁定时间，添加进购物车的时间
     *
     * @return lock_time - 锁定时间，添加进购物车的时间
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * 设置锁定时间，添加进购物车的时间
     *
     * @param lockTime 锁定时间，添加进购物车的时间
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 获取确认时间，满标时间
     *
     * @return comfirm_time - 确认时间，满标时间
     */
    public Date getComfirmTime() {
        return comfirmTime;
    }

    /**
     * 设置确认时间，满标时间
     *
     * @param comfirmTime 确认时间，满标时间
     */
    public void setComfirmTime(Date comfirmTime) {
        this.comfirmTime = comfirmTime;
    }

    /**
     * 获取锁定时间小时单位
     *
     * @return lock_duration - 锁定时间小时单位
     */
    public Integer getLockDuration() {
        return lockDuration;
    }

    /**
     * 设置锁定时间小时单位
     *
     * @param lockDuration 锁定时间小时单位
     */
    public void setLockDuration(Integer lockDuration) {
        this.lockDuration = lockDuration;
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
}