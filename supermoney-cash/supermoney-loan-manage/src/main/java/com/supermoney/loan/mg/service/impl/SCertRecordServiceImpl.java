package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SCertRecordMapper;
import com.supermoney.loan.mg.entity.SCertRecord;
import com.supermoney.loan.mg.service.SCertRecordService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/17.
 */
@Service
@Transactional
public class SCertRecordServiceImpl extends AbstractService<SCertRecord> implements SCertRecordService {
    @Resource
    private SCertRecordMapper sCertRecordMapper;

    @Override
    public void safeIdentityCert(Integer userId,Integer cerStatus) {
        SCertRecord certRecord = new SCertRecord();
        certRecord.setUserId(userId);
        certRecord.setCreateTime(new Date());
        certRecord.setCertType((byte)0);
        certRecord.setCertStatus((byte)cerStatus.intValue());
        sCertRecordMapper.insert(certRecord);
    }

    @Override
    public Integer getIdentityCertStatus(Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("certType",0);
        Map<String, Long> resultMap = sCertRecordMapper.selectStatusByUserId(param);
        if(resultMap == null || resultMap.get("certStatus") == null){
            return -1;
        }
        return resultMap.get("certStatus").intValue();
    }

    @Override
    public void safeLivingCert(Integer userId,Integer cerStatus) {
        SCertRecord certRecord = new SCertRecord();
        certRecord.setUserId(userId);
        certRecord.setCreateTime(new Date());
        certRecord.setCertType((byte)1);
        certRecord.setCertStatus((byte)cerStatus.intValue());
        sCertRecordMapper.insert(certRecord);
    }

    @Override
    public Integer getLivingCertStatus(Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("certType",1);
        Map<String, Long> resultMap = sCertRecordMapper.selectStatusByUserId(param);
        if(resultMap == null || resultMap.get("certStatus") == null){
            return -1;
        }
        return resultMap.get("certStatus").intValue();
    }

    @Override
    public void safeIdPeopleCompareCert(Integer userId,Integer cerStatus) {
        SCertRecord certRecord = new SCertRecord();
        certRecord.setUserId(userId);
        certRecord.setCreateTime(new Date());
        certRecord.setCertType((byte)2);
        certRecord.setCertStatus((byte)cerStatus.intValue());
        sCertRecordMapper.insert(certRecord);
    }

    @Override
    public Integer getPeopleCompareCertStatus(Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("certType",2);
        Map<String, Long> resultMap = sCertRecordMapper.selectStatusByUserId(param);
        if(resultMap == null || resultMap.get("certStatus") == null){
            return -1;
        }
        return resultMap.get("certStatus").intValue();
    }

    @Override
    public Map<String, Integer> getUserCertStatus(Integer userId) {
        return sCertRecordMapper.selectAllStatusByUserId(userId);
    }
}
