package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SActivityIssueRecordMapper;
import com.supermoney.loan.api.entity.SActivityIssueRecord;
import com.supermoney.loan.api.service.SActivityIssueRecordService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/25.
 */
@Service
@Transactional
public class SActivityIssueRecordServiceImpl extends AbstractService<SActivityIssueRecord> implements SActivityIssueRecordService {
    @Resource
    private SActivityIssueRecordMapper sActivityIssueRecordMapper;

    @Override
    public Long getTodayIssueRecord(Map<String, Object> param) {
        Date nowDay = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDayStr = dateFormat.format(nowDay);
        param.put("nowDay",nowDayStr);
        return sActivityIssueRecordMapper.getTodayIssueRecord(param);
    }
}
