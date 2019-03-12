package com.supermoney.loan.market.service;
import com.supermoney.loan.market.entity.SNikInfo;
import com.supermoney.loan.market.utils.Service;


/**
 * Created by bear on 2018/10/18.
 */
public interface SNikInfoService extends Service<SNikInfo> {

    public SNikInfo selectNikInfoByNik(String nik);

}
