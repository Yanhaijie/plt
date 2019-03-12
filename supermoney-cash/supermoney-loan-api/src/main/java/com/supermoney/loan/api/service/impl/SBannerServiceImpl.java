package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SBannerMapper;
import com.supermoney.loan.api.entity.SBanner;
import com.supermoney.loan.api.service.SBannerService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/04.
 */
@Service
@Transactional
public class SBannerServiceImpl extends AbstractService<SBanner> implements SBannerService {
    @Resource
    private SBannerMapper sBannerMapper;

    @Override
    public List<Map<String, Object>> selectBannerByParam(Map<String, Object> param) {
        return sBannerMapper.selectBannerByParam(param);
    }
}
