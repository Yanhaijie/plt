package com.supermoney.loan.mg.service;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SVoucherUserRecord;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/15.
 */
public interface SVoucherUserRecordService extends Service<SVoucherUserRecord> {
    /**
     * 用户抵用券发放
     * @param method
     * @param voucherId
     * @param ids
     * @return
     */
    public Result userVoucher(Integer method, Integer voucherId, String[] ids);

    public PageInfo getByPage(int page, int size, Map<String,Object> param);
}
