package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SActivityWhiteListMapper;
import com.supermoney.loan.mg.entity.SActivityWhiteList;
import com.supermoney.loan.mg.service.SActivityWhiteListService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/21.
 */
@Service
@Transactional
public class SActivityWhiteListServiceImpl extends AbstractService<SActivityWhiteList> implements SActivityWhiteListService {
    @Resource
    private SActivityWhiteListMapper sActivityWhiteListMapper;


    @Override
    public List<SActivityWhiteList> selectWhiteListByMap(Map<String, Object> param) {
        return sActivityWhiteListMapper.selectWhiteListByMap(param);
    }
}
