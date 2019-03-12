package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SBannerMapper;
import com.supermoney.loan.mg.entity.SBanner;
import com.supermoney.loan.mg.service.SBannerService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/03.
 */
@Service
@Transactional
public class SBannerServiceImpl extends AbstractService<SBanner> implements SBannerService {
    @Resource
    private SBannerMapper sBannerMapper;

    @Override
    public List<Map<String, Object>> selectBannerByMap(Map<String, Object> param) {
        return sBannerMapper.selectBannerByMap(param);
    }

    @Override
    public void updateBanner(SBanner banner) {
        sBannerMapper.updateBanner(banner);
    }
}
