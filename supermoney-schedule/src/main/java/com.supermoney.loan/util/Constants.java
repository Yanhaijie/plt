package com.supermoney.loan.util;

/**
 * Created by admin on 2018-01-09.
 */
public class Constants {

    //用户超过X天未登录
    public  static final String USER_NO_LOGIN_TEMPLATE="未登录模板";

    /**
     * 短信状态
     */
    //发送中
    public  static final int SENDING=1;

    //已发送
    public  static final int SENDED=2;

    //发送失败
    public  static final int SENDED_fail=3;

    public  static  String REDIS_LASTLOGINTIMEKEY="lastLoginTime";
    /**
     * 短信类型 短息类型
     * 1：超7天未登录
     * 2：账户带提现余额超过X
     * 3:每获取多少赏金，提醒其登录看看谁已经成为他的代理
     * 4：每获取XX金额分成，提醒赚了多少钱
    */
    //七天未登录
    public static final int SEVEN_DAYS_NO_LOGIN=1;

    //账户带提现余额超过N
    //public static final int
}
