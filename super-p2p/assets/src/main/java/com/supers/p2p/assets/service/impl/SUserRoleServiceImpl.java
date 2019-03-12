package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SUserRoleMapper;
import com.supers.p2p.assets.entity.SUserRole;
import com.supers.p2p.assets.service.SUserRoleService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/14.
 */
@Service
@Transactional
public class SUserRoleServiceImpl extends AbstractService<SUserRole> implements SUserRoleService {
    @Resource
    private SUserRoleMapper sUserRoleMapper;

}
