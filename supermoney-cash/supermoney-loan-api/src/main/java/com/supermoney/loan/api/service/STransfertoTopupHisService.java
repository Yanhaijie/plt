package com.supermoney.loan.api.service;

import com.supermoney.loan.api.entity.STransfertoTopupHis;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/07/06.
 */
public interface STransfertoTopupHisService extends Service<STransfertoTopupHis> {
    public void saveTopUpRecord(Map map);
    public Result topup(Integer userId, Integer phoneNumberType, String phoneNumber, Integer product, String msisdn, String yesOrNo, String smsTxt, String sendYesOrNo, String sSmsTxt);
    public List<Map<String,Object>> topUpService(Integer userId, Integer type);
    public List<Map<String,Object>> topUpCheck(Map<String,Object> paramMap);
    public boolean isLimitTopUp(Integer userId,Integer product);
    //获取话费充值产品列表
    public Result msisdn_info(Integer userId);
}
