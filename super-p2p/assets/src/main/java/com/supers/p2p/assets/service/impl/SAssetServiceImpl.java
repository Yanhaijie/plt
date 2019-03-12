package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SAssetMapper;
import com.supers.p2p.assets.entity.SAsset;
import com.supers.p2p.assets.service.SAssetService;
import com.supers.p2p.assets.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/03.
 */
@Service
@Transactional
public class SAssetServiceImpl extends AbstractService<SAsset> implements SAssetService {
    @Resource
    private SAssetMapper sAssetMapper;

}
