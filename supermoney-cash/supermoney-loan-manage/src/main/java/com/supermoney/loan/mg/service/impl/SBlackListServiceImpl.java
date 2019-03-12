package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SBlackListMapper;
import com.supermoney.loan.mg.entity.SBlackList;
import com.supermoney.loan.mg.service.SBlackListService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/07/16.
 */
@Service
@Transactional
public class SBlackListServiceImpl extends AbstractService<SBlackList> implements SBlackListService {
    @Resource
    private SBlackListMapper sBlackListMapper;


    public void saveOrUpdateBlackList(List<SBlackList> list){
        sBlackListMapper.saveOrUpdateBlackList(list);
    }

    public List<SBlackList> selectList(Map<String,Object> param){
        return sBlackListMapper.selectList(param);
    }

    @Override
    public int blackListCheck1(String userName){
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        List<SBlackList> lists = selectList(map);
        return lists != null && lists.size() > 0 ? 1: 0;
    }
}
