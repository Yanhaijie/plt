package com.supermoney.loan.service.impl;

/**
 * @ClassName: UserServiceImpl
 * @Author: yanhaijie
 * @CreateDate: 2019-02-26 20:44
 * @Version: 1.0
 */

import com.supermoney.loan.dao.SSendSmsRecordMapper;
import com.supermoney.loan.entity.SSendSmsRecord;
import com.supermoney.loan.entity.SUser;
import com.supermoney.loan.service.SSendSmsRecordService;
import com.supermoney.loan.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SSendSmsRecordServiceImpl implements SSendSmsRecordService{

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
    public List<SSendSmsRecord> getAllSendSmsRecord(Paging paging) {
        return sSendSmsRecordMapper.getAllSendSmsRecord(paging);
    }

    @Override
    public int getCount() {
        return sSendSmsRecordMapper.getCount();
    }

    @Override
    public void updateLastLoginTime(List<SUser> list) {
        sSendSmsRecordMapper.updateLastLoginTime(list);
    }

}
