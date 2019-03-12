package com.wi.data.clear.service.impl;

import com.wi.data.clear.dao.CMobileMapper;
import com.wi.data.clear.entity.CMobile;
import com.wi.data.clear.service.CMobileService;
import com.wi.data.clear.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
@Service
@Transactional
public class CMobileServiceImpl extends AbstractService<CMobile> implements CMobileService {
    @Resource
    private CMobileMapper cMobileMapper;

}
