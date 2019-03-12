package com.supermoney.open.platform.service;
import com.supermoney.open.platform.entity.SInterface;
import com.supermoney.open.platform.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/10/10.
 */
public interface SInterfaceService extends Service<SInterface> {
    public SInterface selectInterfaceByParam(Map<String, Object> param);
    public List<SInterface> selectInterfaceListByParam(Map<String, Object> param);
    public SInterface getInterface(Integer interfaceType);
}
