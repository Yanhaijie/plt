package com.supermoney.loan.mg.service;

public interface GoogleapisService {
    /**
     * 推送消息
     * @param appName 应用名称
     * @param title 标题
     * @param body 内容
     * @param pushTo 推送对象: 设备ID 标签名称  标签表达式
     * @param pushType 推送类型:  0设备推送 1标签推送 2推送所有用户
     */
    public void sendMsgToDevice(String appName,String title,String body,String pushTo,Integer pushType);
}
