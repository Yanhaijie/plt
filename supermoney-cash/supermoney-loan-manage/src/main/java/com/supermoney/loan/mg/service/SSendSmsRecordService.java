package com.supermoney.loan.mg.service;

import com.supermoney.loan.mg.entity.SSendSmsRecord;

import java.util.List;

/**
 * @ClassName: SSendSmsRecordSerivce
 * @Author: yanhaijiemg
 * @CreateDate: 2019-02-27 10:00
 * @Version: 1.0
 */
public interface SSendSmsRecordService {
    void insertSendSmsRecord(List<SSendSmsRecord> sSendSmsRecordList);

    void updateSendSmsRecord(List<SSendSmsRecord> sSendSmsRecordList);

    int getCount();

    SSendSmsRecord getBountyRecordByUserId(Integer id);
}
