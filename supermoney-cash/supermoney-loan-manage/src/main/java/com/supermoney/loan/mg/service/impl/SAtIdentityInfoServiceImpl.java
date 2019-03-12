package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SAtIdentityInfoMapper;
import com.supermoney.loan.mg.entity.SAtIdentityInfo;
import com.supermoney.loan.mg.service.SAtIdentityInfoService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xionghuifeng on 2018/04/10.
 */
@Service
@Transactional
public class SAtIdentityInfoServiceImpl extends AbstractService<SAtIdentityInfo> implements SAtIdentityInfoService {
    @Resource
    private SAtIdentityInfoMapper sAtIdentityInfoMapper;

}
