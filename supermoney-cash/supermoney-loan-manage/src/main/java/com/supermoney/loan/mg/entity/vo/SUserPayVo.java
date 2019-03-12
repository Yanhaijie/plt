package com.supermoney.loan.mg.entity.vo;

import com.supermoney.loan.mg.entity.SUserPay;

import java.io.Serializable;

/**
 * Created by wenyuhao on 2018-04-09.
 */
public class SUserPayVo extends SUserPay implements Serializable {

    private String userName;
    private String realName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
