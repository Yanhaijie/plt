package com.supermoney.loan.mg.entity;

import javax.persistence.*;

@Table(name = "s_repot_count")
public class SRepotCount {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 报表标识
     */
    @Column(name = "report_flag")
    private String reportFlag;

    /**
     * 报表名称
     */
    @Column(name = "report_name")
    private String reportName;

    /**
     * 语句
     */
    @Column(name = "report_sql")
    private String reportSql;

    /**
     * 类型: 0启用 1停用
     */
    @Column(name = "report_status")
    private Integer reportStatus;

    /**
     * 类型: 0 sql 1 es
     */
    @Column(name = "report_type")
    private Integer reportType;

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
     * 获取报表标识
     *
     * @return report_flag - 报表标识
     */
    public String getReportFlag() {
        return reportFlag;
    }

    /**
     * 设置报表标识
     *
     * @param reportFlag 报表标识
     */
    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    /**
     * 获取报表名称
     *
     * @return report_name - 报表名称
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * 设置报表名称
     *
     * @param reportName 报表名称
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * 获取语句
     *
     * @return report_sql - 语句
     */
    public String getReportSql() {
        return reportSql;
    }

    /**
     * 设置语句
     *
     * @param reportSql 语句
     */
    public void setReportSql(String reportSql) {
        this.reportSql = reportSql;
    }

    /**
     * 获取类型: 0启用 1停用
     *
     * @return report_status - 类型: 0启用 1停用
     */
    public Integer getReportStatus() {
        return reportStatus;
    }

    /**
     * 设置类型: 0启用 1停用
     *
     * @param reportStatus 类型: 0启用 1停用
     */
    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * 获取类型: 0 sql 1 es
     *
     * @return report_type - 类型: 0 sql 1 es
     */
    public Integer getReportType() {
        return reportType;
    }

    /**
     * 设置类型: 0 sql 1 es
     *
     * @param reportType 类型: 0 sql 1 es
     */
    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }
}