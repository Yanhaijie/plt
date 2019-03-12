package com.supermoney.loan.api.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018-01-10.
 */
@Component
@ConfigurationProperties(prefix = "conf")
@PropertySource("classpath:application.properties")
public class AppProperties {

    /**
     * 运营国家
     */
    private String country;
    /**
     * 开启短信发送
     */
    private String openSmsSend;
    /**
     * 短信发送模板
     */
    private  String smsTmp;
    /**
     *
     */
    private String defaulSmsCode;
    /**
     * 上传存放目录
     */
    private  String apiUploadUrl;
    /**
     * 是否开发模式
     */
    private String dev;
    /**
     * 是否开启定时任务
     */
    private  String runtask;
    /**
     * 是否开启打款回调
     */
    private  String discallback;

    /**
     * xendit 支付公钥
     */
    private  String xenditPublicKey;
    /**
     * xendit  回调token校验
     * Use these tokens to verify that a callback API payload came from Xendit
     */
    private  String xenditValidationToken;
    /**
     * xendit 支付秘钥
     */
    private String xenditSecretKey;

    /**
     * geetest ID
     */
    private  String geetestId;
    /**
     * geetest Key
     */
    private  String geetestKey;
    /**
     * geetest failback
     */
    private  String geetestFailBack;


    /**
     *  赏金任务回调服务器地址
     */
    private  String bountyCallBackHost;
    /**
     * 渠道地址
     */
    private  String adsChId;
    /**
     * h5静态页面地址
     */
    private  String h5Sharesms;
    /**
     * pinjamrupiah 包
     */
    private  String pinjamrupiahKitApp;
    /**
     * kit pinjamrupiah ID
     */
    private  String pinjamrupiahKitId;
    /**
     * kit pinjamrupiah包KEY
     */
    private String pinjamrupiahKitKey;
    /**
     * kit rupiahpinjam包
     */
    private  String rupiahpinjamKitApp;
    /**
     * kit rupiahpinjam ID
     */
    private  String rupiahpinjamKitId;
    /**
     * kit rupiahpinjam包KEY
     */
    private  String rupiahpinjamKitKey;


    /**
     * kit  moneyshop 包
     */
    private  String moneyshopKitApp;
    /**
     * kit moneyshop ID
     */
    private  String moneyshopKitId;
    /**
     * kit moneyshop KEY
     */
    private  String moneyshopKitKey;

    private  String  danauangKitApp;

    private  String  danauangKitId;

    private  String  danauangKitKey;


    private  String  moneycashKitApp;

    private  String  moneycashKitId;

    private  String  moneycashKitKey;

    /**
     * OSS 域名
     */
    private String endpoint;

    /**
     * OSS key
     */
    private String accesskey;

    /**
     * OSS secret
     */
    private String accesskeysecret;

    /**
     * OSS 空间
     */
    private String bucketname;

    /**
     * OSS 访问路径
     */
    private String path;



    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getAccesskeysecret() {
        return accesskeysecret;
    }

    public void setAccesskeysecret(String accesskeysecret) {
        this.accesskeysecret = accesskeysecret;
    }

    public String getBucketname() {
        return bucketname;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }

    public String getDefaulSmsCode() {
        return defaulSmsCode;
    }

    public void setDefaulSmsCode(String defaulSmsCode) {
        this.defaulSmsCode = defaulSmsCode;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getOpenSmsSend() {
        return openSmsSend;
    }

    public void setOpenSmsSend(String openSmsSend) {
        this.openSmsSend = openSmsSend;
    }

    public String getApiUploadUrl() {
        return apiUploadUrl;
    }

    public void setApiUploadUrl(String apiUploadUrl) {
        this.apiUploadUrl = apiUploadUrl;
    }


    public String getXenditSecretKey() {
        return xenditSecretKey;
    }

    public void setXenditSecretKey(String xenditSecretKey) {
        this.xenditSecretKey = xenditSecretKey;
    }

    public String getBountyCallBackHost() {
        return bountyCallBackHost;
    }

    public void setBountyCallBackHost(String bountyCallBackHost) {
        this.bountyCallBackHost = bountyCallBackHost;
    }

    public String getAdsChId() {
        return adsChId;
    }

    public void setAdsChId(String adsChId) {
        this.adsChId = adsChId;
    }

    public String getH5Sharesms() {
        return h5Sharesms;
    }

    public void setH5Sharesms(String h5Sharesms) {
        this.h5Sharesms = h5Sharesms;
    }

    public String getSmsTmp() {
        return smsTmp;
    }

    public void setSmsTmp(String smsTmp) {
        this.smsTmp = smsTmp;
    }

    public String getXenditPublicKey() {
        return xenditPublicKey;
    }

    public void setXenditPublicKey(String xenditPublicKey) {
        this.xenditPublicKey = xenditPublicKey;
    }

    public String getXenditValidationToken() {
        return xenditValidationToken;
    }

    public void setXenditValidationToken(String xenditValidationToken) {
        this.xenditValidationToken = xenditValidationToken;
    }

    public String getGeetestId() {
        return geetestId;
    }

    public void setGeetestId(String geetestId) {
        this.geetestId = geetestId;
    }

    public String getGeetestKey() {
        return geetestKey;
    }

    public void setGeetestKey(String geetestKey) {
        this.geetestKey = geetestKey;
    }

    public String getGeetestFailBack() {
        return geetestFailBack;
    }

    public void setGeetestFailBack(String geetestFailBack) {
        this.geetestFailBack = geetestFailBack;
    }

    public String getPinjamrupiahKitId() {
        return pinjamrupiahKitId;
    }

    public void setPinjamrupiahKitId(String pinjamrupiahKitId) {
        this.pinjamrupiahKitId = pinjamrupiahKitId;
    }

    public String getPinjamrupiahKitKey() {
        return pinjamrupiahKitKey;
    }

    public void setPinjamrupiahKitKey(String pinjamrupiahKitKey) {
        this.pinjamrupiahKitKey = pinjamrupiahKitKey;
    }

    public String getRupiahpinjamKitId() {
        return rupiahpinjamKitId;
    }

    public void setRupiahpinjamKitId(String rupiahpinjamKitId) {
        this.rupiahpinjamKitId = rupiahpinjamKitId;
    }

    public String getRupiahpinjamKitKey() {
        return rupiahpinjamKitKey;
    }

    public void setRupiahpinjamKitKey(String rupiahpinjamKitKey) {
        this.rupiahpinjamKitKey = rupiahpinjamKitKey;
    }

    public String getPinjamrupiahKitApp() {
        return pinjamrupiahKitApp;
    }

    public void setPinjamrupiahKitApp(String pinjamrupiahKitApp) {
        this.pinjamrupiahKitApp = pinjamrupiahKitApp;
    }

    public String getRupiahpinjamKitApp() {
        return rupiahpinjamKitApp;
    }

    public void setRupiahpinjamKitApp(String rupiahpinjamKitApp) {
        this.rupiahpinjamKitApp = rupiahpinjamKitApp;
    }

    public String getMoneyshopKitApp() {
        return moneyshopKitApp;
    }

    public void setMoneyshopKitApp(String moneyshopKitApp) {
        this.moneyshopKitApp = moneyshopKitApp;
    }

    public String getMoneyshopKitId() {
        return moneyshopKitId;
    }

    public void setMoneyshopKitId(String moneyshopKitId) {
        this.moneyshopKitId = moneyshopKitId;
    }

    public String getMoneyshopKitKey() {
        return moneyshopKitKey;
    }

    public void setMoneyshopKitKey(String moneyshopKitKey) {
        this.moneyshopKitKey = moneyshopKitKey;
    }

    public String getRuntask() {
        return runtask;
    }

    public void setRuntask(String runtask) {
        this.runtask = runtask;
    }

    public String getDiscallback() {
        return discallback;
    }

    public void setDiscallback(String discallback) {
        this.discallback = discallback;
    }

    public String getDanauangKitApp() {
        return danauangKitApp;
    }

    public void setDanauangKitApp(String danauangKitApp) {
        this.danauangKitApp = danauangKitApp;
    }

    public String getDanauangKitId() {
        return danauangKitId;
    }

    public void setDanauangKitId(String danauangKitId) {
        this.danauangKitId = danauangKitId;
    }

    public String getDanauangKitKey() {
        return danauangKitKey;
    }

    public void setDanauangKitKey(String danauangKitKey) {
        this.danauangKitKey = danauangKitKey;
    }

    public String getMoneycashKitApp() {
        return moneycashKitApp;
    }

    public void setMoneycashKitApp(String moneycashKitApp) {
        this.moneycashKitApp = moneycashKitApp;
    }

    public String getMoneycashKitId() {
        return moneycashKitId;
    }

    public void setMoneycashKitId(String moneycashKitId) {
        this.moneycashKitId = moneycashKitId;
    }

    public String getMoneycashKitKey() {
        return moneycashKitKey;
    }

    public void setMoneycashKitKey(String moneycashKitKey) {
        this.moneycashKitKey = moneycashKitKey;
    }
}
