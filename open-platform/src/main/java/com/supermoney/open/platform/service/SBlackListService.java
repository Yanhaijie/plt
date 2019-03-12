package com.supermoney.open.platform.service;
import com.supermoney.open.platform.entity.SBlackList;
import com.supermoney.open.platform.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/10/10.
 */
public interface SBlackListService extends Service<SBlackList> {

    public List<SBlackList> selectBlackListByParam(Map<String, Object> param);

}
