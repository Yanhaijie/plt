package com.supermoney.open.platform.service.impl;

import com.supermoney.open.platform.dao.SParamMapper;
import com.supermoney.open.platform.entity.SParam;
import com.supermoney.open.platform.service.SParamService;
import com.supermoney.open.platform.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by @author on 2018/10/10.
 */
@Service
@Transactional
public class SParamServiceImpl extends AbstractService<SParam> implements SParamService {
    @Resource
    private SParamMapper sParamMapper;

}
