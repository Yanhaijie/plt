package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SBanner;
import com.supermoney.loan.api.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/04.
 */
public interface SBannerService extends Service<SBanner> {
    public List<Map<String ,Object>> selectBannerByParam(Map<String, Object> param);
}
