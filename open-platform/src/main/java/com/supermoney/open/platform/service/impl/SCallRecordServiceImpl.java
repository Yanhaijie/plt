package com.supermoney.open.platform.service.impl;

import com.supermoney.open.platform.dao.SCallRecordMapper;
import com.supermoney.open.platform.entity.SCallRecord;
import com.supermoney.open.platform.service.SCallRecordService;
import com.supermoney.open.platform.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/10/10.
 */
@Service
@Transactional
public class SCallRecordServiceImpl extends AbstractService<SCallRecord> implements SCallRecordService {
    @Resource
    private SCallRecordMapper sCallRecordMapper;

    @Override
    public List<Map<String, Object>> selectCallRecordByParam(Map<String, Object> param) {
        return sCallRecordMapper.selectCallRecordByParam(param);
    }
}
