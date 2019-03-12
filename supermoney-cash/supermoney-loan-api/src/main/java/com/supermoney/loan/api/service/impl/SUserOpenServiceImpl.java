package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SUserMapper;
import com.supermoney.loan.api.dao.SUserOpenMapper;
import com.supermoney.loan.api.entity.SUserOpen;
import com.supermoney.loan.api.service.SUserOpenService;
import com.supermoney.loan.api.utils.AbstractService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xionghuifeng on 2018/03/10.
 */
@Service
@Transactional
public class SUserOpenServiceImpl extends AbstractService<SUserOpen> implements SUserOpenService {
    @Resource
    private SUserOpenMapper sUserOpenMapper;
    @Resource
    private SUserMapper sUserMapper;

    /**
     * 获取Open
     * @param openId
     * @param platmfromName
     * @return
     */
    public  SUserOpen getOpen(String openId, String platmfromName){
        SUserOpen open=new SUserOpen();
        open.setOpenId(openId);
        open.setPlatformName(platmfromName);
        open=sUserOpenMapper.selectOne(open);
        return  open;
    }
    /**
     * open是否已存在
     * @param openId
     * @param platmfromName
     * @return
     */
    public boolean HasOpen(String openId, String platmfromName){
        if(StringUtils.isBlank(openId)||StringUtils.isBlank(platmfromName)){
            return  false;
        }
        SUserOpen open=getOpen(openId,platmfromName);
        return  open!=null;
    }

    /**
     * 添加Open信息
     * @param openId
     * @param platformName
     * @param nickName
     * @param sex
     * @return
     */
    public  boolean addOpen(String openId,String platformName,String nickName,String sex){
        SUserOpen userOpen=new SUserOpen();
        userOpen.setOpenId(openId);
        userOpen.setPlatformName(platformName);
        userOpen.setNickName(nickName);
        userOpen.setSex(sex);
       return sUserOpenMapper.insert(userOpen)>0;
    }

    /**
     * 用户绑定OpenID
     * @param openId
     * @param platformName
     * @param userId
     * @return
     */
    public boolean userBindOpen(String openId, String platformName, Integer userId){
        SUserOpen userOpen=getOpen(openId,platformName);
        if(userOpen==null){
            return  false;
        }
        userOpen.setUserId(userId);
        return sUserOpenMapper.updateByPrimaryKeySelective(userOpen)>0;
    }

}
