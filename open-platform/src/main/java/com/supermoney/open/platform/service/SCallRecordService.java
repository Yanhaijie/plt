package com.supermoney.open.platform.service;
import com.supermoney.open.platform.entity.SCallRecord;
import com.supermoney.open.platform.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/10/10.
 */
public interface SCallRecordService extends Service<SCallRecord> {

    public List<Map<String, Object>> selectCallRecordByParam(Map<String, Object> param);

}
