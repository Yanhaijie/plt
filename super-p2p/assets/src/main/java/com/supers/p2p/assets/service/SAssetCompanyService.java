package com.supers.p2p.assets.service;
import com.supers.p2p.assets.entity.SAssetCompany;
import com.supers.p2p.assets.entity.vo.SAssetCompanyVo;
import com.supers.p2p.assets.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/08.
 */
public interface SAssetCompanyService extends Service<SAssetCompany> {

    public List<Map<String,Object>> getAssetCompanyPoolList(Map<String,Object> map);

    public List<SAssetCompanyVo> findByParam(Map<String,Object> map);


}
