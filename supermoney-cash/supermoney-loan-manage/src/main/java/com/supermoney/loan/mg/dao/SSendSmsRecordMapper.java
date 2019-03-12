package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SSendSmsRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: UserServiceDao
 * @Author: yanhaijie
 * @CreateDate: 2019-02-26 22:06
 * @Version: 1.0
 */
@Mapper
public interface SSendSmsRecordMapper {

    void insertSendSmsRecord(List<SSendSmsRecord> sSendSmsRecordList);

    void updateSendSmsRecord(List<SSendSmsRecord> sSendSmsRecordList);

    int getCount();

    SSendSmsRecord getBountyRecordByUserId(Integer id);
}
