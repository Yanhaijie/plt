package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SActivityLotteryRecordMapper;
import com.supermoney.loan.mg.entity.SActivityLotteryRecord;
import com.supermoney.loan.mg.service.SActivityLotteryRecordService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/21.
 */
@Service
@Transactional
public class SActivityLotteryRecordServiceImpl extends AbstractService<SActivityLotteryRecord> implements SActivityLotteryRecordService {
    @Resource
    private SActivityLotteryRecordMapper sActivityLotteryRecordMapper;

    @Override
    public List<SActivityLotteryRecord> selectLotteryRecordListByMap(Map<String, Object> param) {
        return sActivityLotteryRecordMapper.selectLotteryRecordListByMap(param);
    }

    @Override
    public List<Map<String, Object>> selectUserLotteryCount(Map<String, Object> param) {
        return sActivityLotteryRecordMapper.selectUserLotteryCount(param);
    }
}
