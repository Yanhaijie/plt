package com.supers.p2p.assets.service;
import com.github.pagehelper.PageInfo;
import com.supers.p2p.assets.entity.SRole;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.Service;

import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/11.
 */
public interface SRoleService extends Service<SRole> {

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 获取某公司下的角色信息
     * @param companId
     * @return
     */
    public Result companyRole(Integer companId);
}
