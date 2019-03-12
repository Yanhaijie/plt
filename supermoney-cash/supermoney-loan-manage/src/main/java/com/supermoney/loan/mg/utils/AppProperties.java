package com.supermoney.loan.mg.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018-02-12.
 */
@Component
@ConfigurationProperties(prefix = "conf")
@PropertySource("classpath:application.properties")
public class AppProperties {

    private  String dev;



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

    /**
     * OSS 国家
     */
    private String country;

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

    public String getXenditSecretKey() {
        return xenditSecretKey;
    }

    public void setXenditSecretKey(String xenditSecretKey) {
        this.xenditSecretKey = xenditSecretKey;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

}
