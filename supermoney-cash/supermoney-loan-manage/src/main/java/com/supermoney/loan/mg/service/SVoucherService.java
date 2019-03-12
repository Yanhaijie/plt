package com.supermoney.loan.mg.service;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SVoucher;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/15.
 */
public interface SVoucherService extends Service<SVoucher> {
    /**
     * 获取可用抵用券下拉
     * @return
     */
    public Result getDrop();

    public List<SVoucher> getList(Map<String,Object> param);

    public PageInfo getByPage(int page, int size, Map<String,Object> param);

}
