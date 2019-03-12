package com.supermoney.open.platform.service.impl;

import com.supermoney.open.platform.dao.SInterfaceMapper;
import com.supermoney.open.platform.entity.SInterface;
import com.supermoney.open.platform.service.SInterfaceService;
import com.supermoney.open.platform.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/10/10.
 */
@Service
@Transactional
public class SInterfaceServiceImpl extends AbstractService<SInterface> implements SInterfaceService {
    @Resource
    private SInterfaceMapper sInterfaceMapper;

    @Override
    public SInterface selectInterfaceByParam(Map<String, Object> param) {
        return sInterfaceMapper.selectInterfaceByParam(param);
    }

    @Override
    public List<SInterface> selectInterfaceListByParam(Map<String, Object> param) {
        return sInterfaceMapper.selectInterfaceListByParam(param);
    }

    @Override
    public SInterface getInterface(Integer interfaceType){
        Map<String, Object> param = new HashMap<>();
        param.put("interfaceType",interfaceType);
        param.put("useStatus",0);
        return selectInterfaceByParam(param);
    }
}
