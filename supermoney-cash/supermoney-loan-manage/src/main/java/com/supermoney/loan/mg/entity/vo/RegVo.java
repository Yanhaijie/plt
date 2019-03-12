package com.supermoney.loan.mg.entity.vo;

import java.io.Serializable;

/**
 * Created by wangLiJun on 2019/3/2.
 */
public class RegVo implements Serializable {
    private String strPackage;
    private String version;
    private String channel;
    private String countryId;
    private String share;
    private String regCount;

    public String getStrPackage() {
        return strPackage;
    }

    public void setStrPackage(String strPackage) {
        this.strPackage = strPackage;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getRegCount() {
        return regCount;
    }

    public void setRegCount(String regCount) {
        this.regCount = regCount;
    }
}
