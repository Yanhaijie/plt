package com.supers.p2p.assets.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supers.p2p.assets.dao.SRoleMapper;
import com.supers.p2p.assets.entity.SRole;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.entity.vo.DropVo;
import com.supers.p2p.assets.service.SRoleService;
import com.supers.p2p.assets.utils.AbstractService;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/11.
 */
@Service
@Transactional
public class SRoleServiceImpl extends AbstractService<SRole> implements SRoleService {
    @Resource
    private SRoleMapper sRoleMapper;

    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SRole> getList(Map<String,Object> param)
    {
        return  sRoleMapper.selectList(param);
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
        List<SRole> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取某公司下的角色信息
     * @param companId
     * @return
     */
    @Override
    public Result companyRole(Integer companId){
        SRole param=new SRole();
        param.setCompanyId(companId);
        List<SRole> list=sRoleMapper.select(param);
        List<DropVo> drops=new ArrayList<>();
        for (SRole role:list){
            DropVo item=new DropVo();
            item.setText(role.getRoleName());
            item.setValue(role.getId().toString());
            drops.add(item);
        }
        return ResultGenerator.genSuccessResult(drops);
    }

}
