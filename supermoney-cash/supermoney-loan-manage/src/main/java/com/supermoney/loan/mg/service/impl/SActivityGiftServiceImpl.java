package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SActivityGiftMapper;
import com.supermoney.loan.mg.entity.SActivityGift;
import com.supermoney.loan.mg.service.SActivityGiftService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/21.
 */
@Service
@Transactional
public class SActivityGiftServiceImpl extends AbstractService<SActivityGift> implements SActivityGiftService {
    @Resource
    private SActivityGiftMapper sActivityGiftMapper;


    @Override
    public List<Map<String, Object>> selectGiftByMap(Map<String, Object> param) {
        return sActivityGiftMapper.selectGiftByMap(param);
    }

    @Override
    public List<Map<String, Object>> selectSearchGiftByMap(Map<String, Object> param) {
        return sActivityGiftMapper.selectSearchGiftByMap(param);
    }

    @Override
    public List<Map<String, Object>> selectBackGiftByMap(Map<String, Object> param) {
        return sActivityGiftMapper.selectBackGiftByMap(param);
    }
}
