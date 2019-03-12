package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SActivityGiftMapper;
import com.supermoney.loan.api.entity.SActivityGift;
import com.supermoney.loan.api.service.SActivityGiftService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/22.
 */
@Service
@Transactional
public class SActivityGiftServiceImpl extends AbstractService<SActivityGift> implements SActivityGiftService {
    @Resource
    private SActivityGiftMapper sActivityGiftMapper;

    @Override
    public List<SActivityGift> selectCommonGiftByMap(Map<String, Object> param) {
        return sActivityGiftMapper.selectCommonGiftByMap(param);
    }

    @Override
    public List<Map<String, Object>> selectGiftListByMap(Map<String, Object> param) {
        return sActivityGiftMapper.selectGiftListByMap(param);
    }

    @Override
    public void subRemainCount(Integer id) {
        sActivityGiftMapper.subRemainCount(id);
    }

    @Override
    public void addRemainCount(Integer id) {
        sActivityGiftMapper.addRemainCount(id);
    }
}
