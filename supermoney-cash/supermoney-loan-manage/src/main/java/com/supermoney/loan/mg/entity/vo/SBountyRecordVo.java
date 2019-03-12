package com.supermoney.loan.mg.entity.vo;

import com.supermoney.loan.mg.entity.SBountyRecord;

import java.io.Serializable;

/**
 * Created by bear on 2018/1/6.
 */

public class SBountyRecordVo extends SBountyRecord implements Serializable {
    /**
     * 用户电话
     */
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
