package com.supermoney.loan.mg.entity.vo;

import com.supermoney.loan.mg.entity.SLoanRepay;

import javax.persistence.Column;
import java.io.Serializable;

public class SLoanRepayVo extends SLoanRepay implements Serializable {
    /**
     * 用户名
     */
    @Column(name = "user_name")
    private  String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
