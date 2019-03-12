package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SUserOpen;
import com.supermoney.loan.api.utils.Service;


/**
 * Created by xionghuifeng on 2018/03/10.
 */
public interface SUserOpenService extends Service<SUserOpen> {
    /**
     * 获取Open
     * @param openId
     * @param platmfromName
     * @return
     */
    public  SUserOpen getOpen(String openId, String platmfromName);
    /**
     * 添加Open信息
     * @param openId
     * @param platformName
     * @param nickName
     * @param sex
     * @return
     */
    public  boolean addOpen(String openId,String platformName,String nickName,String sex);

    /**
     * 用户绑定OpenID
     * @param openId
     * @param platformName
     * @param userId
     * @return
     */
    public boolean userBindOpen(String openId, String platformName, Integer userId);
}
