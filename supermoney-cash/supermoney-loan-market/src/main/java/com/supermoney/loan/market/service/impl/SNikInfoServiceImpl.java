package com.supermoney.loan.market.service.impl;

import com.supermoney.loan.market.dao.SNikInfoMapper;
import com.supermoney.loan.market.entity.SNikInfo;
import com.supermoney.loan.market.service.SNikInfoService;
import com.supermoney.loan.market.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by bear on 2018/10/18.
 */
@Service
@Transactional
public class SNikInfoServiceImpl extends AbstractService<SNikInfo> implements SNikInfoService {
    @Resource
    private SNikInfoMapper sNikInfoMapper;

    @Override
    public SNikInfo selectNikInfoByNik(String nik) {
        return sNikInfoMapper.selectNikInfoByNik(nik);
    }


}
