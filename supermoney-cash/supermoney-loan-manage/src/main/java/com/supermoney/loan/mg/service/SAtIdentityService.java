package com.supermoney.loan.mg.service;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SAtIdentity;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/22.
 */
public interface SAtIdentityService extends Service<SAtIdentity> {

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 更新信息
     * @param sAtIdentity
     * @return
     */
    public Result  updateInfo(SAtIdentity sAtIdentity);


    /**
     * 获取未OCR认证的个人身份验证信息
     * @return
     */
    public List<SAtIdentity> selectUncheckIdentity();


    /**
     * 获取未审核通过的个人身份验证信息
     * @return
     */
    public List<SAtIdentity> selectUncheck();
    /**
     * 批量审核
     * @param status
     * @param ids
     * @return
     */
    public  Result audits(Integer status,String[] ids);

    public List<SAtIdentity> getList(Map<String,Object> param);


    public List<SAtIdentity> selectHaveOrderIdentity();

    public  SAtIdentity getSuccessIdentity(Integer userId);

}
