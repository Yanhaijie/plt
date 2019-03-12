package com.supers.p2p.assets.service.impl;

import com.supers.p2p.assets.dao.SFoundCompanyMapper;
import com.supers.p2p.assets.entity.SFoundCompany;
import com.supers.p2p.assets.service.SFoundCompanyService;
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
public class SFoundCompanyServiceImpl extends AbstractService<SFoundCompany> implements SFoundCompanyService {
    @Resource
    private SFoundCompanyMapper sFoundCompanyMapper;

    @Override
    public Map<String, Object> selectFoundCompanyDetail(Map<String, Object> param) {
        return sFoundCompanyMapper.selectFoundCompanyDetail(param);
    }

    @Override
    public void saveFoundCompany(SFoundCompany sFoundCompany) {
        sFoundCompanyMapper.saveFoundCompany(sFoundCompany);
    }

    @Override
    public List<Map<String, Object>> selectFoundCompanyList(Map<String, Object> param) {
        return sFoundCompanyMapper.selectFoundCompanyList(param);
    }

    @Override
    public List<Map<String, Object>> selectCoopFoundCompanyList(Map<String, Object> param) {
        return sFoundCompanyMapper.selectCoopFoundCompanyList(param);
    }
}
