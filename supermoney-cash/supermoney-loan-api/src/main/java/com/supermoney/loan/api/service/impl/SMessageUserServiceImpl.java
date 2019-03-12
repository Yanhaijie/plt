package com.supermoney.loan.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.supermoney.loan.api.dao.SMessageUserMapper;
import com.supermoney.loan.api.entity.SMessageType;
import com.supermoney.loan.api.entity.SMessageUser;
import com.supermoney.loan.api.service.SMessageTypeService;
import com.supermoney.loan.api.service.SMessageUserService;
import com.supermoney.loan.api.utils.AbstractService;
import com.supermoney.loan.api.utils.ClassUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/10.
 */
@Service
@Transactional
public class SMessageUserServiceImpl extends AbstractService<SMessageUser> implements SMessageUserService {
    @Resource
    private SMessageUserMapper sMessageUserMapper;
    @Resource
    private SMessageTypeService sMessageTypeService;

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

    /**
     * 获取推送消息个数
     * @param sMessageUser
     * @return
     */
    public int getCount(SMessageUser sMessageUser)
    {
        return  sMessageUserMapper.getCount(sMessageUser);
    }

    /**
     * 获取推送消息个数详情
     * @param sMessageUser
     * @return
     */
    public Map<String,Object> getCountInfo(SMessageUser sMessageUser)throws Exception{
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("total",0);
        List<SMessageType> messageTypeList = sMessageTypeService.findAllOrder();
        resultMap.put("total",0);
        resultMap.put("messageTypeList",messageTypeList);
        //初始化，默认为0
        for(SMessageType messageType : messageTypeList){
            resultMap.put("messageTypeId-"+messageType.getId(),0);
        }
        List<SMessageUser> list =getList(ClassUtils.parseToMap(sMessageUser));
        if(list != null && list.size() > 0){
            int total = list.size();
            resultMap.put("total",total);
            for(SMessageUser obj : list){
                //TODO:待优化为值为个数，目前1表示有信息，0表示没信息
                if(obj.getReadStatus() == 0){
                    resultMap.put("messageTypeId-"+obj.getMessageTypeId(),1);
                }

            }
        }
        return resultMap;
    }




}
