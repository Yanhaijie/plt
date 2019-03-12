package com.supermoney.loan.api.service;

import com.supermoney.loan.api.entity.SAtIdentity;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import java.io.InputStream;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/18.
 */
public interface SAtIdentityService extends Service<SAtIdentity> {
    /**
     *提交身份认证信息
     * @param userId
     * @param name
     * @param identity
     * @param imgFront
     * @param imgBack
     * @return
     */
    public Result filingIdentity(Integer userId, String name, String identity, String imgFront, String imgBack);
    /**
     * 统计绑卡状态的数量
     * @param userId
     * @param status
     * @return
     */
    public  Integer countByStatus(Integer userId,Integer status);


    /**
     * 获取用户实名认证状态
     * @return
     */
    public SAtIdentity selectAtIdentityByUserId(Map<String, Object> param);
    /**
     * 获取审核成功的实名认真
     * @param userId
     * @return
     */
    public  SAtIdentity getSuccessIdentity(Integer userId);

    /**
     * 征信身份认证
     * @param userId
     * @param name
     * @param identity
     * @param idFrontImg
     * @param peopleImg
     * @param hold
     * @param front
     * @return
     */
    public  Result  creditIdentity(Integer userId, String name, String identity, String idFrontImg, String peopleImg, InputStream hold, InputStream front);
    /**
     * 获取认证信息
     * @param userId
     * @return
     */
    public  Result getIdentityInfo(Integer userId);
}
