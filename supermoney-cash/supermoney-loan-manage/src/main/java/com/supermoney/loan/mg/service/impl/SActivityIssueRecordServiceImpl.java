package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SActivityIssueRecordMapper;
import com.supermoney.loan.mg.entity.SActivityIssueRecord;
import com.supermoney.loan.mg.service.SActivityIssueRecordService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xionghuifeng on 2018/04/25.
 */
@Service
@Transactional
public class SActivityIssueRecordServiceImpl extends AbstractService<SActivityIssueRecord> implements SActivityIssueRecordService {
    @Resource
    private SActivityIssueRecordMapper sActivityIssueRecordMapper;

}
