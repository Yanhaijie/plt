package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SAppLog;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;


/**
 * Created by xionghuifeng on 2018/01/18.
 */
public interface SAppLogService extends Service<SAppLog> {
    /**
     * 日志提交
     * @param logType
     * @param logContent
     * @return
     */
    public Result filingLog(Integer userId,String logType, String logContent);
    /**
     * 跟踪日志
     * @param userId
     * @param logContent
     * @return
     */
    public Result trackLog(Integer userId, String logContent);
}
