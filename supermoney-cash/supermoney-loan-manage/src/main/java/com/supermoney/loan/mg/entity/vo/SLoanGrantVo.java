package com.supermoney.loan.mg.entity.vo;

import com.supermoney.loan.mg.entity.SLoanGrant;

import javax.persistence.Column;
import java.io.Serializable;

public class SLoanGrantVo extends SLoanGrant implements Serializable {
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
