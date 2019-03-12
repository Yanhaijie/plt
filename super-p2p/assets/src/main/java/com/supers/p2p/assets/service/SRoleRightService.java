package com.supers.p2p.assets.service;
import com.supers.p2p.assets.entity.SRoleRight;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.Service;


/**
 * Created by wenyuhao on 2018/05/11.
 */
public interface SRoleRightService extends Service<SRoleRight> {

    /**
     * 保存角色权限
     * @param roleId
     * @param ids
     * @return
     */
    public Result saveright(Integer roleId, String[] ids);

    /**
     * 获取用户角色
     * @param roleId
     * @return
     */
    public Result userright(Integer roleId);

}
