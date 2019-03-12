package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SAtLivingInfoMapper;
import com.supermoney.loan.api.entity.SAtLivingInfo;
import com.supermoney.loan.api.service.SAtLivingInfoService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/05/07.
 */
@Service
@Transactional
public class SAtLivingInfoServiceImpl extends AbstractService<SAtLivingInfo> implements SAtLivingInfoService {
    @Resource
    private SAtLivingInfoMapper sAtLivingInfoMapper;

}
