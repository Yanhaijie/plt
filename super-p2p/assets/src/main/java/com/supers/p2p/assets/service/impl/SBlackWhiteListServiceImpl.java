package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SBlackWhiteListMapper;
import com.supers.p2p.assets.entity.SBlackWhiteList;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.vo.AssetFoundVo;
import com.supers.p2p.assets.service.SBlackWhiteListService;
import com.supers.p2p.assets.utils.AbstractService;
import com.supers.p2p.assets.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/08.
 */
@Service
@Transactional
public class SBlackWhiteListServiceImpl extends AbstractService<SBlackWhiteList> implements SBlackWhiteListService {
    @Resource
    private SBlackWhiteListMapper sBlackWhiteListMapper;

    /**
     * 查询应用
     * @return
     */
    public List<SBlackWhiteList> getList(SBlackWhiteList sBlackWhiteList)
    {
        return  sBlackWhiteListMapper.selectListByObj(sBlackWhiteList);
    }

    public void deleteByObj(SBlackWhiteList sBlackWhiteList){
        sBlackWhiteListMapper.deleteByObj(sBlackWhiteList);
    }

    public List<Map<String,Object>> getMyAssetCompanyList(Map<String,Object> param){
        return sBlackWhiteListMapper.getMyAssetCompanyList(param);
    }

    public List<Map<String,Object>> selectAssetFoundRelation(Map<String,Object> map){
        return sBlackWhiteListMapper.selectAssetFoundRelation(map);
    }

}
