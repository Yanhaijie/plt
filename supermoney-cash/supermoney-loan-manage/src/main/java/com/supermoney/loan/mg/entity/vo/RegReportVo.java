package com.supermoney.loan.mg.entity.vo;

import java.io.Serializable;

/**
 * Created by taoxiaojie on 2019/3/1.
 */
public class RegReportVo implements Serializable{
    private String regSource;
    private int regCount;

    public String getRegSource() {
        return regSource;
    }

    public void setRegSource(String regSource) {
        this.regSource = regSource;
    }

    public int getRegCount() {
        return regCount;
    }

    public void setRegCount(int regCount) {
        this.regCount = regCount;
    }
}
