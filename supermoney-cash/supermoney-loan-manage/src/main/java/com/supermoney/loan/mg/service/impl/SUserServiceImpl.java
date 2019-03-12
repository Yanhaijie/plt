package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SUserMapper;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.entity.SVoucherUserRecord;
import com.supermoney.loan.mg.service.SUserService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/13.
 */
@Service
@Transactional
public class SUserServiceImpl extends AbstractService<SUser> implements SUserService {
    @Resource
    private SUserMapper sUserMapper;


    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SUser> getList(Map<String,Object> param)
    {
        return  sUserMapper.selectList(param);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    @Override
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SUser> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void updateCreditInfo(Map<String, Object> param ) {
        sUserMapper.updateCreditInfo(param);
    }

    @Override
    public  SUser getById(Integer userId){
        return  sUserMapper.selectByPrimaryKey(userId);
    }

}
