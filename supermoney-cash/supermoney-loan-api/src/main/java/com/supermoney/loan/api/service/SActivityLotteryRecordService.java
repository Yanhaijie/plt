package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SActivityLotteryRecord;
import com.supermoney.loan.api.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/22.
 */
public interface SActivityLotteryRecordService extends Service<SActivityLotteryRecord> {

    List<Map<String, Object>> selectLotteryRecordByUserId(Map<String ,Object> param);

    List<Map<String, Object>> selectPrizeRecordByUserId(Map<String ,Object> param);

    Long getTodayLotteryRecord(Map<String ,Object> param);

    Long selectLotteryRecordCountByUserId(Map<String ,Object> param);

    List<Map<String, Object>> selectScroolLotteryRecord(Map<String ,Object> param);
}
