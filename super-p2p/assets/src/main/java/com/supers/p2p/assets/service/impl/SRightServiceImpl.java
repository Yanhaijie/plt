package com.supers.p2p.assets.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supers.p2p.assets.dao.SRightMapper;
import com.supers.p2p.assets.entity.SRight;
import com.supers.p2p.assets.entity.SRole;
import com.supers.p2p.assets.service.SRightService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/11.
 */
@Service
@Transactional
public class SRightServiceImpl extends AbstractService<SRight> implements SRightService {
    @Resource
    private SRightMapper sRightMapper;

    /**
     * 查询
     * @param param
     * @return
     */
    public List<SRight> getList(Map<String,Object> param)
    {
        return  sRightMapper.selectList(param);
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
        List<SRight> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
