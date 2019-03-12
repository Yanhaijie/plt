package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SActivityWhiteList;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/21.
 */
public interface SActivityWhiteListService extends Service<SActivityWhiteList> {
    public List<SActivityWhiteList> selectWhiteListByMap(Map<String, Object> param);
}
