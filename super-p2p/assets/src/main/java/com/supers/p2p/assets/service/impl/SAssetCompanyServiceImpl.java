package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SAssetCompanyMapper;
import com.supers.p2p.assets.entity.SAssetCompany;
import com.supers.p2p.assets.entity.vo.SAssetCompanyVo;
import com.supers.p2p.assets.service.SAssetCompanyService;
import com.supers.p2p.assets.utils.AbstractService;
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
public class SAssetCompanyServiceImpl extends AbstractService<SAssetCompany> implements SAssetCompanyService {
    @Resource
    private SAssetCompanyMapper sAssetCompanyMapper;

    public List<Map<String,Object>> getAssetCompanyPoolList(Map<String,Object> param){
        return sAssetCompanyMapper.getAssetCompanyPoolList(param);
    }

    public List<SAssetCompanyVo> findByParam(Map<String,Object> map){
        return sAssetCompanyMapper.findByParam(map);
    }
}
