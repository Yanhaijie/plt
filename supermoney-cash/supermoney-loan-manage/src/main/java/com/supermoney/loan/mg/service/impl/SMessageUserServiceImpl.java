package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SMessageUserMapper;
import com.supermoney.loan.mg.entity.SMessageType;
import com.supermoney.loan.mg.entity.SMessageUser;
import com.supermoney.loan.mg.service.SMessageUserService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/04.
 */
@Service
@Transactional
public class SMessageUserServiceImpl extends AbstractService<SMessageUser> implements SMessageUserService {
    @Resource
    private SMessageUserMapper sMessageUserMapper;

    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SMessageUser> getList(Map<String,Object> param)
    {
        return  sMessageUserMapper.selectList(param);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SMessageUser> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
