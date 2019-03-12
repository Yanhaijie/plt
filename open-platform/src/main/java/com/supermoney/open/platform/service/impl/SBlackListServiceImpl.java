package com.supermoney.open.platform.service.impl;

import com.supermoney.open.platform.dao.SBlackListMapper;
import com.supermoney.open.platform.entity.SBlackList;
import com.supermoney.open.platform.service.SBlackListService;
import com.supermoney.open.platform.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/10/10.
 */
@Service
@Transactional
public class SBlackListServiceImpl extends AbstractService<SBlackList> implements SBlackListService {
    @Resource
    private SBlackListMapper sBlackListMapper;

    @Override
    public List<SBlackList> selectBlackListByParam(Map<String, Object> param) {
        return sBlackListMapper.selectBlackListByParam(param);
    }
}
