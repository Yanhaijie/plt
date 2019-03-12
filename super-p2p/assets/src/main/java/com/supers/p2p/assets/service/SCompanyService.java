package com.supers.p2p.assets.service;
import com.github.pagehelper.PageInfo;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/08.
 */
public interface SCompanyService extends Service<SCompany> {

    public List<SCompany> getList(Map<String,Object> param);

    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    public List<Map<String,Object>> selectCompanyExeclModel();

    public List<Map<String,Object>> selectAssetCompanyExeclModel();

    public List<Map<String,Object>> selectFoundCompanyExeclModel();

    public void importCompany(List<List<Object>> list,int type) throws Exception;

}
