package com.supermoney.loan.api.entity.vo;

import com.supermoney.loan.api.entity.SMessageType;

import java.io.Serializable;

public class SMessageTypeVo extends SMessageType implements Serializable {
    private boolean hasUnReadStatus = false;
    private Integer unReadCount;

    public boolean isHasUnReadStatus() {
        return hasUnReadStatus;
    }

    public void setHasUnReadStatus(boolean hasUnReadStatus) {
        this.hasUnReadStatus = hasUnReadStatus;
    }

    public Integer getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }
}
