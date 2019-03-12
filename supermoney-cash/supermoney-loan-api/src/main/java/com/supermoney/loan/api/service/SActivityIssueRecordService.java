package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SActivityIssueRecord;
import com.supermoney.loan.api.utils.Service;

import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/25.
 */
public interface SActivityIssueRecordService extends Service<SActivityIssueRecord> {

    public Long getTodayIssueRecord(Map<String, Object> param);

}
