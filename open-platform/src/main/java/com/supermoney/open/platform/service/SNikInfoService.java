package com.supermoney.open.platform.service;
import com.supermoney.open.platform.entity.SNikInfo;
import com.supermoney.open.platform.utils.Service;


/**
 * Created by @author on 2018/10/25.
 */
public interface SNikInfoService extends Service<SNikInfo> {

    public SNikInfo selectNikInfoByNik(String nik);
}
