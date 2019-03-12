package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SItemPoolMapper;
import com.supers.p2p.assets.entity.SItemPool;
import com.supers.p2p.assets.service.SItemPoolService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/07.
 */
@Service
@Transactional
public class SItemPoolServiceImpl extends AbstractService<SItemPool> implements SItemPoolService {
    @Resource
    private SItemPoolMapper sItemPoolMapper;

}
