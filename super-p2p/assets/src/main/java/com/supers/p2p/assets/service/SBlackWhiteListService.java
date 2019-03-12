package com.supers.p2p.assets.service;
import com.supers.p2p.assets.entity.SBlackWhiteList;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.vo.AssetFoundVo;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/08.
 */
public interface SBlackWhiteListService extends Service<SBlackWhiteList> {

    public List<SBlackWhiteList> getList(SBlackWhiteList sBlackWhiteList);

    public void deleteByObj(SBlackWhiteList sBlackWhiteList);

    public List<Map<String,Object>> getMyAssetCompanyList(Map<String,Object> map);

    public List<Map<String,Object>> selectAssetFoundRelation(Map<String,Object> map);


}
