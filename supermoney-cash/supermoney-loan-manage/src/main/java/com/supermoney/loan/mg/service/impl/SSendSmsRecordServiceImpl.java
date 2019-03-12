package com.supermoney.loan.mg.service.impl;

/**
 * @ClassName: UserServiceImpl
 * @Author: yanhaijie
 * @CreateDate: 2019-02-26 20:44
 * @Version: 1.0
 */

import com.supermoney.loan.mg.dao.SSendSmsRecordMapper;
import com.supermoney.loan.mg.entity.SSendSmsRecord;
import com.supermoney.loan.mg.service.SSendSmsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SSendSmsRecordServiceImpl implements SSendSmsRecordService {

    @Autowired
    SSendSmsRecordMapper sSendSmsRecordMapper;

    @Override
    public void insertSendSmsRecord(List<SSendSmsRecord> sSendSmsRecordList) {
        sSendSmsRecordMapper.insertSendSmsRecord(sSendSmsRecordList);
    }

    @Override
    public void updateSendSmsRecord(List<SSendSmsRecord> sSendSmsRecordList) {
        sSendSmsRecordMapper.updateSendSmsRecord(sSendSmsRecordList);
    }


    @Override
    public int getCount() {
        return sSendSmsRecordMapper.getCount();
    }

    @Override
    public SSendSmsRecord getBountyRecordByUserId(Integer id) {
        return sSendSmsRecordMapper.getBountyRecordByUserId(id);
    }

}
