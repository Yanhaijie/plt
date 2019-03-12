package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SRoleRightMapper;
import com.supers.p2p.assets.entity.SRoleRight;
import com.supers.p2p.assets.service.SRoleRightService;
import com.supers.p2p.assets.utils.AbstractService;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/11.
 */
@Service
@Transactional
public class SRoleRightServiceImpl extends AbstractService<SRoleRight> implements SRoleRightService {
    @Resource
    private SRoleRightMapper sRoleRightMapper;
    /**
     * 获取用户角色
     * @param roleId
     * @return
     */
    public Result userright(Integer roleId){
        SRoleRight param=new SRoleRight();
        param.setRoleId(roleId);
        List<SRoleRight> list= sRoleRightMapper.select(param);
        List<Integer> ids=new ArrayList<>();
        list.stream().forEach(s-> ids.add(s.getRightId()));
        return  ResultGenerator.genSuccessResult(ids);
    }
    /**
     * 保存角色权限
     * @param roleId
     * @param ids
     * @return
     */
    public Result saveright(Integer roleId, String[] ids){
        //删除旧权限
        Map<String,Object> param=new HashMap();
        param.put("sql","delete from s_role_right where role_id=#{roleId}");
        param.put("roleId",roleId);
        sRoleRightMapper.executeSql(param);
        //增加新权限
        for(String id:ids){
            SRoleRight item=new SRoleRight();
            item.setRoleId(roleId);
            item.setRightId(Integer.valueOf(id));
            sRoleRightMapper.insert(item);
        }
        return ResultGenerator.genSuccessResult();
    }

}
