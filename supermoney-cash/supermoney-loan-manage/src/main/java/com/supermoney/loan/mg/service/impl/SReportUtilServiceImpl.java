package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SReportUtilMapper;
import com.supermoney.loan.mg.entity.SReportUtil;
import com.supermoney.loan.mg.service.SReportUtilService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/13.
 */
@Service
@Transactional
public class SReportUtilServiceImpl extends AbstractService<SReportUtil> implements SReportUtilService {
    @Resource
    private SReportUtilMapper sReportUtilMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly=true)
    public List<Map<String,Object>> executeSelect(Map<String,Object> param){
        return sReportUtilMapper.executeSelect(param);
    }

}
