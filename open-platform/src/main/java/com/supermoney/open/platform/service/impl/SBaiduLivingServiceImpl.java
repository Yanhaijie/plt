package com.supermoney.open.platform.service.impl;

import com.supermoney.open.platform.dao.SBaiduLivingMapper;
import com.supermoney.open.platform.entity.SBaiduLiving;
import com.supermoney.open.platform.service.SBaiduLivingService;
import com.supermoney.open.platform.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by @author on 2018/10/22.
 */
@Service
@Transactional
public class SBaiduLivingServiceImpl extends AbstractService<SBaiduLiving> implements SBaiduLivingService {
    @Resource
    private SBaiduLivingMapper sBaiduLivingMapper;

}
