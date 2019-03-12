package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SActivityWhiteListMapper;
import com.supermoney.loan.api.entity.SActivityWhiteList;
import com.supermoney.loan.api.service.SActivityWhiteListService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/22.
 */
@Service
@Transactional
public class SActivityWhiteListServiceImpl extends AbstractService<SActivityWhiteList> implements SActivityWhiteListService {
    @Resource
    private SActivityWhiteListMapper sActivityWhiteListMapper;

    @Override
    public List<SActivityWhiteList> selectWhiteListByParam(Map<String, Object> param) {
        return sActivityWhiteListMapper.selectWhiteListByParam(param);
    }
}
