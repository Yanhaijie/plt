package com.wi.data.clear.service.impl;

import com.wi.data.clear.dao.CAppUserMapper;
import com.wi.data.clear.entity.CAppUser;
import com.wi.data.clear.service.CAppUserService;
import com.wi.data.clear.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
@Service
@Transactional
public class CAppUserServiceImpl extends AbstractService<CAppUser> implements CAppUserService {
    @Resource
    private CAppUserMapper cAppUserMapper;

}
