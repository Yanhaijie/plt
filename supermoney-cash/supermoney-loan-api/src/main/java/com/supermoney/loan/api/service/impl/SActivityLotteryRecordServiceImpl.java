package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SActivityLotteryRecordMapper;
import com.supermoney.loan.api.entity.SActivityLotteryRecord;
import com.supermoney.loan.api.service.SActivityLotteryRecordService;
import com.supermoney.loan.api.utils.AbstractService;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/22.
 */
@Service
@Transactional
public class SActivityLotteryRecordServiceImpl extends AbstractService<SActivityLotteryRecord> implements SActivityLotteryRecordService {
    @Resource
    private SActivityLotteryRecordMapper sActivityLotteryRecordMapper;

    @Override
    public List<Map<String, Object>> selectLotteryRecordByUserId(Map<String ,Object> param) {
        return sActivityLotteryRecordMapper.selectLotteryRecordByUserId(param);
    }

    @Override
    public List<Map<String, Object>> selectPrizeRecordByUserId(Map<String, Object> param) {
        return sActivityLotteryRecordMapper.selectPrizeRecordByUserId(param);
    }

    @Override
    public Long getTodayLotteryRecord(Map<String, Object> param) {
        Date nowDay = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDayStr = dateFormat.format(nowDay);
        param.put("nowDay",nowDayStr);
        return sActivityLotteryRecordMapper.getTodayLotteryRecord(param);
    }

    @Override
    public Long selectLotteryRecordCountByUserId(Map<String, Object> param) {
        return sActivityLotteryRecordMapper.selectLotteryRecordCountByUserId(param);
    }

    @Override
    public List<Map<String, Object>> selectScroolLotteryRecord(Map<String, Object> param) {
        return sActivityLotteryRecordMapper.selectScroolLotteryRecord(param);
    }
}
