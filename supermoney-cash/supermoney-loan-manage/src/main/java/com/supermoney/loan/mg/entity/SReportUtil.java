package com.supermoney.loan.mg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_report_util")
public class SReportUtil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sqlStr")
    private String sqlstr;

    private String opt;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return sqlStr
     */
    public String getSqlstr() {
        return sqlstr;
    }

    /**
     * @param sqlstr
     */
    public void setSqlstr(String sqlstr) {
        this.sqlstr = sqlstr;
    }

    /**
     * @return opt
     */
    public String getOpt() {
        return opt;
    }

    /**
     * @param opt
     */
    public void setOpt(String opt) {
        this.opt = opt;
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
}