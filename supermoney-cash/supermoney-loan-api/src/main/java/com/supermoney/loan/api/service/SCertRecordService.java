package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SCertRecord;
import com.supermoney.loan.api.utils.Service;

import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/17.
 */
public interface SCertRecordService extends Service<SCertRecord> {
    /**
     * 保存身份认证成功记录
     * @param userId
     */
    public void safeIdentityCert(Integer userId,Integer cerStatus);
    /**
     * 获取身份认证状态
     * @param userId
     */
    public Integer getIdentityCertStatus(Integer userId);



    /**
     * 保存活体认证成功记录
     * @param userId
     */
    public void safeLivingCert(Integer userId,Integer cerStatus);
    /**
     * 获取活体认证状态
     * @param userId
     */
    public Integer getLivingCertStatus(Integer userId);



    /**
     * 保存人证对比认证成功记录
     * @param userId
     */
    public void safeIdPeopleCompareCert(Integer userId,Integer cerStatus);
    /**
     * 获取人证对比状态
     * @param userId
     */
    public Integer getPeopleCompareCertStatus(Integer userId);


    /**
     * 获取个人认证状态（所有认证）
     * @param userId
     * @return
     */
    public Map<String, Integer> getUserCertStatus(Integer userId);
}
