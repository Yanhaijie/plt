package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SBanner;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/03.
 */
public interface SBannerService extends Service<SBanner> {

    public List<Map<String, Object>> selectBannerByMap(Map<String, Object> param);

    void updateBanner(SBanner banner);

}
