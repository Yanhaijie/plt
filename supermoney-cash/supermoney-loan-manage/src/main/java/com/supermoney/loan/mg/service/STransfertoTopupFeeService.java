package com.supermoney.loan.mg.service;

import com.supermoney.loan.mg.entity.STransfertoTopupFee;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/07/18.
 */
public interface STransfertoTopupFeeService extends Service<STransfertoTopupFee> {
    public List<STransfertoTopupFee> selectList(Map<String,Object> param);
    public List<Map<String,Object>> topUpHistList(Map<String,Object> param);

}
