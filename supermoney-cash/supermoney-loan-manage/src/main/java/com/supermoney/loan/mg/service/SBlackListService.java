package com.supermoney.loan.mg.service;

import com.supermoney.loan.mg.entity.SBlackList;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/07/16.
 */
public interface SBlackListService extends Service<SBlackList> {
    public void saveOrUpdateBlackList(List<SBlackList> list);

    public List<SBlackList> selectList(Map<String,Object> param);

    public int blackListCheck1(String userName);
}
