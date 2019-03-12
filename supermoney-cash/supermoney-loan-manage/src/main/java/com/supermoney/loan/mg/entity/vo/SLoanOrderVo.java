package com.supermoney.loan.mg.entity.vo;

import com.supermoney.loan.mg.entity.SLoanOrder;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

public class SLoanOrderVo extends SLoanOrder implements Serializable{

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private  String userName;
    /**
     * 任务产品名称
     */
    @Column(name = "bounty_name")
    private String bountyName;
    /**
     * 征信档案ID
     */
    @Column(name="creditId")
    private  Integer creditId;
    /**
     * 认证状态
     */
    @Column(name="identityStatus")
    private  Integer identityStatus;

    private BigDecimal needAmountArea;
    private  BigDecimal gotAmountArea;
    private  BigDecimal loanAmountArea;
    private  BigDecimal loanInterestArea;
    private  BigDecimal feeAmountArea;
    private  BigDecimal overdueAmountArea;
    private  BigDecimal platformAmountArea;
    private  BigDecimal allAmountArea;

    /**
     * 保险
     */
    @Column(name = "BPJS_img")
    private String BPJSImg;

    /**
     * 家庭卡
     */
    @Column(name = "KK_img")
    private String KKImg;

    /**
     * 信用卡
     */
    @Column(name = "credit_card_img")
    private String creditCardImg;

    /**
     * 工作卡
     */
    @Column(name = "work_card_img")
    private String workCardImg;

    /**
     * 工资证明
     */
    @Column(name = "salary_cert_img")
    private String salaryCertImg;

    /**
     * 公司给的宣誓书
     */
    @Column(name = "company_affidavit_img")
    private String companyAffidavitImg;

    /**
     * 银行账单
     */
    @Column(name = "bank_statement_img")
    private String bankStatementImg;

    /**
     * 其他支持你工作真相的证据
     */
    @Column(name = "other_work_img")
    private String otherWorkImg;

    /**
     * 帐户照片
     */
    @Column(name = "account_img")
    private String accountImg;

    /**
     * 每日历史照片
     */
    @Column(name = "day_history_img")
    private String dayHistoryImg;

    /**
     * 照片历史最近2周
     */
    @Column(name = "week_history_img")
    private String weekHistoryImg;

    /**
     * 照片历史最近1个月
     */
    @Column(name = "month_history_img")
    private String monthHistoryImg;

    /**
     * 备注
     */
    private String remarkText;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBountyName() {
        return bountyName;
    }

    public void setBountyName(String bountyName) {
        this.bountyName = bountyName;
    }

    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }


    public BigDecimal getNeedAmountArea() {
        return needAmountArea;
    }

    public void setNeedAmountArea(BigDecimal needAmountArea) {
        this.needAmountArea = needAmountArea;
    }

    public BigDecimal getGotAmountArea() {
        return gotAmountArea;
    }

    public void setGotAmountArea(BigDecimal gotAmountArea) {
        this.gotAmountArea = gotAmountArea;
    }

    public BigDecimal getLoanAmountArea() {
        return loanAmountArea;
    }

    public void setLoanAmountArea(BigDecimal loanAmountArea) {
        this.loanAmountArea = loanAmountArea;
    }

    public BigDecimal getLoanInterestArea() {
        return loanInterestArea;
    }

    public void setLoanInterestArea(BigDecimal loanInterestArea) {
        this.loanInterestArea = loanInterestArea;
    }

    public BigDecimal getFeeAmountArea() {
        return feeAmountArea;
    }

    public void setFeeAmountArea(BigDecimal feeAmountArea) {
        this.feeAmountArea = feeAmountArea;
    }

    public BigDecimal getOverdueAmountArea() {
        return overdueAmountArea;
    }

    public void setOverdueAmountArea(BigDecimal overdueAmountArea) {
        this.overdueAmountArea = overdueAmountArea;
    }

    public BigDecimal getPlatformAmountArea() {
        return platformAmountArea;
    }

    public void setPlatformAmountArea(BigDecimal platformAmountArea) {
        this.platformAmountArea = platformAmountArea;
    }

    public BigDecimal getAllAmountArea() {
        return allAmountArea;
    }

    public void setAllAmountArea(BigDecimal allAmountArea) {
        this.allAmountArea = allAmountArea;
    }

    public Integer getIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(Integer identityStatus) {
        this.identityStatus = identityStatus;
    }

    public String getBPJSImg() {
        return BPJSImg;
    }

    public void setBPJSImg(String BPJSImg) {
        this.BPJSImg = BPJSImg;
    }

    public String getKKImg() {
        return KKImg;
    }

    public void setKKImg(String KKImg) {
        this.KKImg = KKImg;
    }

    public String getCreditCardImg() {
        return creditCardImg;
    }

    public void setCreditCardImg(String creditCardImg) {
        this.creditCardImg = creditCardImg;
    }

    public String getWorkCardImg() {
        return workCardImg;
    }

    public void setWorkCardImg(String workCardImg) {
        this.workCardImg = workCardImg;
    }

    public String getSalaryCertImg() {
        return salaryCertImg;
    }

    public void setSalaryCertImg(String salaryCertImg) {
        this.salaryCertImg = salaryCertImg;
    }

    public String getCompanyAffidavitImg() {
        return companyAffidavitImg;
    }

    public void setCompanyAffidavitImg(String companyAffidavitImg) {
        this.companyAffidavitImg = companyAffidavitImg;
    }

    public String getBankStatementImg() {
        return bankStatementImg;
    }

    public void setBankStatementImg(String bankStatementImg) {
        this.bankStatementImg = bankStatementImg;
    }

    public String getOtherWorkImg() {
        return otherWorkImg;
    }

    public void setOtherWorkImg(String otherWorkImg) {
        this.otherWorkImg = otherWorkImg;
    }

    public String getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(String accountImg) {
        this.accountImg = accountImg;
    }

    public String getDayHistoryImg() {
        return dayHistoryImg;
    }

    public void setDayHistoryImg(String dayHistoryImg) {
        this.dayHistoryImg = dayHistoryImg;
    }

    public String getWeekHistoryImg() {
        return weekHistoryImg;
    }

    public void setWeekHistoryImg(String weekHistoryImg) {
        this.weekHistoryImg = weekHistoryImg;
    }

    public String getMonthHistoryImg() {
        return monthHistoryImg;
    }

    public void setMonthHistoryImg(String monthHistoryImg) {
        this.monthHistoryImg = monthHistoryImg;
    }

    public String getRemarkText() {
        return remarkText;
    }

    public void setRemarkText(String remarkText) {
        this.remarkText = remarkText;
    }
}
