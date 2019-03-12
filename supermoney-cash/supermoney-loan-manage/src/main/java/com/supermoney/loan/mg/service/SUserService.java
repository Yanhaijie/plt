package com.supermoney.loan.mg.service;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/13.
 */
public interface SUserService extends Service<SUser> {

    public List<SUser> getList(Map<String,Object> param);

    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 更新用户征信等级和分数
     * @return
     */
    public void updateCreditInfo(Map<String, Object> param );

    /**
     *
     * @param userId
     * @return
     */
    public  SUser getById(Integer userId);

}
