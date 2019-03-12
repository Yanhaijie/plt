package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_risk_source")
public class SRiskSource {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 数据源名称
     */
    @Column(name = "source_name")
    private String sourceName;

    /**
     * 获取方式: 0SQL查询,1全文索引，2API接口,3函数
     */
    @Column(name = "got_way")
    private Integer gotWay;

    /**
     * 获取来源: 0SQL语句,1全文索引语句，2API接口地址,3函数地址
     */
    @Column(name = "got_from")
    private String gotFrom;

    /**
     * 调用参数 格式[age,money]
     */
    @Column(name = "transfer_param")
    private String transferParam;

    /**
     * 数据返回对象格式,JSON对象
     */
    @Column(name = "return_object")
    private String returnObject;

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
     * 获取数据源名称
     *
     * @return source_name - 数据源名称
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * 设置数据源名称
     *
     * @param sourceName 数据源名称
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * 获取获取方式: 0SQL查询,1全文索引，2API接口,3函数
     *
     * @return got_way - 获取方式: 0SQL查询,1全文索引，2API接口,3函数
     */
    public Integer getGotWay() {
        return gotWay;
    }

    /**
     * 设置获取方式: 0SQL查询,1全文索引，2API接口,3函数
     *
     * @param gotWay 获取方式: 0SQL查询,1全文索引，2API接口,3函数
     */
    public void setGotWay(Integer gotWay) {
        this.gotWay = gotWay;
    }

    /**
     * 获取获取来源: 0SQL语句,1全文索引语句，2API接口地址,3函数地址
     *
     * @return got_from - 获取来源: 0SQL语句,1全文索引语句，2API接口地址,3函数地址
     */
    public String getGotFrom() {
        return gotFrom;
    }

    /**
     * 设置获取来源: 0SQL语句,1全文索引语句，2API接口地址,3函数地址
     *
     * @param gotFrom 获取来源: 0SQL语句,1全文索引语句，2API接口地址,3函数地址
     */
    public void setGotFrom(String gotFrom) {
        this.gotFrom = gotFrom;
    }

    /**
     * 获取调用参数 格式[age,money]
     *
     * @return transfer_param - 调用参数 格式[age,money]
     */
    public String getTransferParam() {
        return transferParam;
    }

    /**
     * 设置调用参数 格式[age,money]
     *
     * @param transferParam 调用参数 格式[age,money]
     */
    public void setTransferParam(String transferParam) {
        this.transferParam = transferParam;
    }

    /**
     * 获取数据返回对象格式,JSON对象
     *
     * @return return_object - 数据返回对象格式,JSON对象
     */
    public String getReturnObject() {
        return returnObject;
    }

    /**
     * 设置数据返回对象格式,JSON对象
     *
     * @param returnObject 数据返回对象格式,JSON对象
     */
    public void setReturnObject(String returnObject) {
        this.returnObject = returnObject;
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