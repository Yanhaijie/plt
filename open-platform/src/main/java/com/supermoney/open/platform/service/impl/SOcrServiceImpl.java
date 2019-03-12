package com.supermoney.open.platform.service.impl;

import com.supermoney.open.platform.dao.SOcrMapper;
import com.supermoney.open.platform.entity.SOcr;
import com.supermoney.open.platform.service.SOcrService;
import com.supermoney.open.platform.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/10/22.
 */
@Service
@Transactional
public class SOcrServiceImpl extends AbstractService<SOcr> implements SOcrService {
    @Resource
    private SOcrMapper sOcrMapper;

    @Override
    public List<SOcr> selectOcrListByParam(Map<String, Object> param) {
        return sOcrMapper.selectOcrListByParam(param);
    }
}
