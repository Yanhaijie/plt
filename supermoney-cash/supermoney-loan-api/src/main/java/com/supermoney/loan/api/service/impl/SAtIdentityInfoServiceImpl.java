package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SAtIdentityInfoMapper;
import com.supermoney.loan.api.entity.SAtIdentityInfo;
import com.supermoney.loan.api.service.SAtIdentityInfoService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xionghuifeng on 2018/03/12.
 */
@Service
@Transactional
public class SAtIdentityInfoServiceImpl extends AbstractService<SAtIdentityInfo> implements SAtIdentityInfoService {
    @Resource
    private SAtIdentityInfoMapper sAtIdentityInfoMapper;

}
