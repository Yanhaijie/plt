package com.supermoney.loan.market.service;
import com.supermoney.loan.market.entity.SNikCallRecord;
import com.supermoney.loan.market.utils.Service;


/**
 * Created by bear on 2018/10/18.
 */
public interface SNikCallRecordService extends Service<SNikCallRecord> {
    /**
     * 获取已经使用成功条数
     * @param merchant_id
     * @return
     */
    public  Integer getCoutRecordBySuccess(String merchant_id);

    /**
     * 用户自己是否已经请求过成功的NIK校验
     * @param merchant_id
     * @param nik
     * @return
     */
    public  boolean hasRequestNik(String merchant_id,String nik);

}
