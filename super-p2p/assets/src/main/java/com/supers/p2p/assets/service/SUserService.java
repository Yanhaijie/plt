package com.supers.p2p.assets.service;
import com.github.pagehelper.PageInfo;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.entity.vo.SUserVo;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.Service;

import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/10.
 */
public interface SUserService extends Service<SUser> {
    /**
     * 新增用户
     * @param sUser
     * @return
     */
    public Result addUser(SUserVo sUser);

    /**
     * 密码登录
     * @param mobile
     * @param password
     * @return
     */
    public   Result login(String mobile, String password);

    /**
     * 获取菜单
     * @param userId
     * @return
     */
    public  Result menu(Integer userId);
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);
}
