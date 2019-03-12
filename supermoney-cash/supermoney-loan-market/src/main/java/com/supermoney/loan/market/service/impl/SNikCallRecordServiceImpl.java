package com.supermoney.loan.market.service.impl;

import com.supermoney.loan.market.dao.SNikCallRecordMapper;
import com.supermoney.loan.market.entity.SNikCallRecord;
import com.supermoney.loan.market.entity.SNikInfo;
import com.supermoney.loan.market.service.SNikCallRecordService;
import com.supermoney.loan.market.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.RecursiveTask;


/**
 * Created by bear on 2018/10/18.
 */
@Service
@Transactional
public class SNikCallRecordServiceImpl extends AbstractService<SNikCallRecord> implements SNikCallRecordService {
    @Resource
    private SNikCallRecordMapper sNikCallRecordMapper;


    /**
     * 获取已经使用成功条数
     * @param merchant_id
     * @return
     */
    @Override
    public  Integer getCoutRecordBySuccess(String merchant_id){
        SNikCallRecord param=new SNikCallRecord();
        param.setMerchantId(merchant_id);
        param.setCallStatus(0);
        return  sNikCallRecordMapper.selectCount(param);
    }

    /**
     * 用户自己是否已经请求过成功的NIK校验
     * @param merchant_id
     * @param nik
     * @return
     */
    @Override
    public  boolean hasRequestNik(String merchant_id,String nik){
        SNikCallRecord param=new SNikCallRecord();
        param.setMerchantId(merchant_id);
        param.setCallStatus(0);
        param.setNik(nik);
        Integer count= sNikCallRecordMapper.selectCount(param);
        return  count.compareTo(Integer.valueOf(0))>0;
    }

}
