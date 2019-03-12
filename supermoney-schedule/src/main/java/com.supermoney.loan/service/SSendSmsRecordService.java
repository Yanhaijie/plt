package com.supermoney.loan.service;

import com.supermoney.loan.entity.SSendSmsRecord;
import com.supermoney.loan.entity.SUser;
import com.supermoney.loan.vo.Paging;

import java.util.List;

/**
 * @ClassName: SSendSmsRecordSerivce
 * @Author: yanhaijie
 * @CreateDate: 2019-02-27 10:00
 * @Version: 1.0
 */
public interface SSendSmsRecordService {
    void insertSendSmsRecord(List<SSendSmsRecord> sSendSmsRecordList);

    void updateSendSmsRecord(List<SSendSmsRecord> sSendSmsRecordList);

    List<SSendSmsRecord> getAllSendSmsRecord(Paging paging);

    int getCount();

    void updateLastLoginTime(List<SUser> list);
}
