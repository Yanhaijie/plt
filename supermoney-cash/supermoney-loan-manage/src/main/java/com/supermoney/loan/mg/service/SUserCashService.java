package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SUserCash;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/02.
 */
public interface SUserCashService extends Service<SUserCash> {

    /**
     * 根据类型获取提现审核列表
     * @return
     */
    public List<Map<String,Object>> getCashCheckListByStatus(Map<String, Object> param);


    /**
     * 提现审核
     * @return
     */
    public void doCashCheck(Map<String, Object> param, List<SUserCash> list);
}
