package com.supermoney.loan.api.service;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.api.entity.SMessageUser;
import com.supermoney.loan.api.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/10.
 */
public interface SMessageUserService extends Service<SMessageUser> {

    /**
     * 查询
     * @param param
     * @return
     */
    public List<SMessageUser> getList(Map<String,Object> param);
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 获取推送消息个数
     * @param sMessageUser
     * @return
     */
    public int getCount(SMessageUser sMessageUser);

    /**
     * 获取推送消息个数详情
     * @param sMessageUser
     * @return
     */
    public Map<String,Object> getCountInfo(SMessageUser sMessageUser) throws Exception;

}
