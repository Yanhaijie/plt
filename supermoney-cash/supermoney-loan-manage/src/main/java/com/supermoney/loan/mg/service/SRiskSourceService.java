package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SRiskSource;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;


/**
 * Created by bear on 2018/07/30.
 */
public interface SRiskSourceService extends Service<SRiskSource> {

    /**
     * 获取下拉数据源
     * @param keyWord
     * @return
     */
    public Result getDrop(String keyWord);

}
