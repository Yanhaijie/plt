package com.supers.p2p.assets.service;
import com.github.pagehelper.PageInfo;
import com.supers.p2p.assets.entity.SRight;
import com.supers.p2p.assets.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/11.
 */
public interface SRightService extends Service<SRight> {
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);
    /**
     * 查询
     * @param param
     * @return
     */
    public List<SRight> getList(Map<String,Object> param);
}
