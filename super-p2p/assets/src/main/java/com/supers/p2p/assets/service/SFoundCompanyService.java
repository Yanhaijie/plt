package com.supers.p2p.assets.service;
import com.supers.p2p.assets.entity.SFoundCompany;
import com.supers.p2p.assets.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/08.
 */
public interface SFoundCompanyService extends Service<SFoundCompany> {
    public Map<String, Object> selectFoundCompanyDetail(Map<String,Object> param);

    public void saveFoundCompany(SFoundCompany sFoundCompany);

    public List<Map<String, Object>> selectFoundCompanyList(Map<String ,Object> param);

    public List<Map<String, Object>> selectCoopFoundCompanyList(Map<String ,Object> param);
}
