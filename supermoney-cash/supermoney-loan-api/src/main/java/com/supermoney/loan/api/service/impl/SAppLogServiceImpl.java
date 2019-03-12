package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SAppLogMapper;
import com.supermoney.loan.api.entity.SAppLog;
import com.supermoney.loan.api.service.SAppLogService;
import com.supermoney.loan.api.utils.AbstractService;
import com.supermoney.loan.api.utils.NomalUntil;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xionghuifeng on 2018/01/18.
 */
@Service
@Transactional
public class SAppLogServiceImpl extends AbstractService<SAppLog> implements SAppLogService {
    @Resource
    private SAppLogMapper sAppLogMapper;

    /**
     * 日志提交
     * @param logType
     * @param logContent
     * @return
     */
    public Result filingLog(Integer userId, String logType, String logContent){
        if(StringUtils.isBlank(logType)){
            return ResultGenerator.genFailResult("logType is null");
        }
        SAppLog log =new SAppLog();
        log.setLogType(logType);
        logContent= NomalUntil.removeFourChar(logContent);
        log.setLogContent(logContent);
        log.setUserId(userId);
        sAppLogMapper.insert(log);
        return  ResultGenerator.genSuccessResult();
    }

    /**
     * 跟踪日志
     * @param userId
     * @param logContent
     * @return
     */
    @Override
    public Result trackLog(Integer userId, String logContent){

        return  ResultGenerator.genSuccessResult();
    }

}
