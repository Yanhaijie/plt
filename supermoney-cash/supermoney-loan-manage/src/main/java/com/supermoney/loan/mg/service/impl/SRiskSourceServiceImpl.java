package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SRiskSourceMapper;
import com.supermoney.loan.mg.entity.SRiskSource;
import com.supermoney.loan.mg.service.SRiskSourceService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by bear on 2018/07/30.
 */
@Service
@Transactional
public class SRiskSourceServiceImpl extends AbstractService<SRiskSource> implements SRiskSourceService {
    @Resource
    private SRiskSourceMapper sRiskSourceMapper;

    /**
     * 获取下拉数据源
     * @param keyWord
     * @return
     */
    public Result getDrop(String keyWord){
        return ResultGenerator.genSuccessResult(sRiskSourceMapper.getDrop(null));
    }

}
