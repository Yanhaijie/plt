package com.supers.p2p.assets.entity.vo;

import com.supers.p2p.assets.entity.SAssetCompany;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by wenyuhao on 2018-05-10.
 */
public class SAssetCompanyVo extends SAssetCompany implements Serializable {

    private String fullName;

    private String abbreviationName;

    private Integer companyNo;

    private Integer auditStatus;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

    public Integer getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(Integer companyNo) {
        this.companyNo = companyNo;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public SAssetCompanyVo toVoEntity(){
        //元转为亿
        BigDecimal assetSize = this.getAssetSize();
        //元转为亿
        BigDecimal monthSize = this.getMonthSize();
        //元转为亿
        BigDecimal everySum = this.getEverySum();
        //元转为亿
        BigDecimal inventory = this.getInventory();
        //元转为万
        BigDecimal startingAmount = this.getStartingAmount();
        //小数转为百分比
        BigDecimal acceptFound = this.getAcceptFound();

        if(assetSize != null){
            this.setAssetSize(assetSize.divide(new BigDecimal("100000000")));
        }
        if(monthSize != null){
            this.setMonthSize(monthSize.divide(new BigDecimal("100000000")));
        }
        if(inventory != null){
            this.setInventory(inventory.divide(new BigDecimal("100000000")));
        }
        if(startingAmount != null){
            this.setStartingAmount(startingAmount.divide(new BigDecimal("10000")));
        }
        if(acceptFound != null){
            this.setAcceptFound(acceptFound.multiply(new BigDecimal("100")));
        }
        return this;
    }
}
