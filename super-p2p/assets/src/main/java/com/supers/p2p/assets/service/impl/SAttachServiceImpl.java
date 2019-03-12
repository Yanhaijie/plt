package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SAttachMapper;
import com.supers.p2p.assets.entity.SAttach;
import com.supers.p2p.assets.service.SAttachService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/04.
 */
@Service
@Transactional
public class SAttachServiceImpl extends AbstractService<SAttach> implements SAttachService {
    @Resource
    private SAttachMapper sAttachMapper;

}
