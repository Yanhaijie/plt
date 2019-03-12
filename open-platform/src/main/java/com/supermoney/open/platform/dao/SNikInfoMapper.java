package com.supermoney.open.platform.dao;

import com.supermoney.open.platform.entity.SNikInfo;
import com.supermoney.open.platform.utils.Mapper;

public interface SNikInfoMapper extends Mapper<SNikInfo> {

    public SNikInfo selectNikInfoByNik(String nik);
}