package com.supermoney.open.platform.service.impl;

import com.supermoney.open.platform.dao.SNikInfoMapper;
import com.supermoney.open.platform.entity.SNikInfo;
import com.supermoney.open.platform.service.SNikInfoService;
import com.supermoney.open.platform.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by @author on 2018/10/25.
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
