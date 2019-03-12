package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SActivityLotteryRecord;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/21.
 */
public interface SActivityLotteryRecordService extends Service<SActivityLotteryRecord> {

    public List<SActivityLotteryRecord> selectLotteryRecordListByMap(Map<String, Object> param);

    public List<Map<String ,Object>> selectUserLotteryCount(Map<String, Object> param);
}
