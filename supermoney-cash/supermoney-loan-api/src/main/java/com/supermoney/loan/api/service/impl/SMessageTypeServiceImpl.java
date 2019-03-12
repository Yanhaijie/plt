package com.supermoney.loan.api.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.supermoney.loan.api.dao.SMessageTypeMapper;
import com.supermoney.loan.api.entity.SMessageType;
import com.supermoney.loan.api.entity.SMessageUser;
import com.supermoney.loan.api.entity.vo.SMessageTypeVo;
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
public class SMessageTypeServiceImpl extends AbstractService<SMessageType> implements SMessageTypeService {
    @Resource
    private SMessageTypeMapper sMessageTypeMapper;
    @Resource
    private SMessageUserService sMessageUserService;

    public List<SMessageType> findAllOrder(){
        return sMessageTypeMapper.findAllOrder();
    }

    public List<SMessageTypeVo> findMessageType(SMessageUser sMessageUser)throws Exception{
        List<SMessageTypeVo> resultList = Lists.newArrayList();
        Map<String,Object> resultMap = Maps.newHashMap();
        //将消息列表转为Map
        List<SMessageType> messageTypeList = this.findAllOrder();
        for(int i=0;i<messageTypeList.size();i++){
            SMessageType sMessageType = messageTypeList.get(i);
            SMessageTypeVo sMessageTypeVo = new SMessageTypeVo();
            sMessageTypeVo.setId(sMessageType.getId());
            sMessageTypeVo.setName(sMessageType.getName());
            sMessageTypeVo.setImg(sMessageType.getImg());
            sMessageTypeVo.setSort(sMessageType.getSort());
            sMessageTypeVo.setCreateTime(sMessageType.getCreateTime());
            sMessageTypeVo.setUpdateTime(sMessageType.getUpdateTime());
            sMessageTypeVo.setUseStatus(sMessageType.getUseStatus());
            resultList.add(sMessageTypeVo);
            resultMap.put("messageTypeId-"+sMessageType.getId().toString(),sMessageTypeVo);
        }
        //获取用户的所有未读消息且未删除的消息
        sMessageUser.setReadStatus(0);
        sMessageUser.setDeleteStatus(0);
        List<SMessageUser> list =sMessageUserService.getList(ClassUtils.parseToMap(sMessageUser));
        if(list != null && list.size() > 0){
            for(SMessageUser obj : list){
                String messageTypeId = obj.getMessageTypeId().toString();
                SMessageTypeVo SMessageTypeVo = (SMessageTypeVo)resultMap.get("messageTypeId-"+messageTypeId);
                SMessageTypeVo.setHasUnReadStatus(true);
            }
        }
        return resultList;
    }

}
