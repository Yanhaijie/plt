package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.STransfertoTopupFeeMapper;
import com.supermoney.loan.mg.entity.STransfertoTopupFee;
import com.supermoney.loan.mg.service.STransfertoTopupFeeService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/07/18.
 */
@Service
@Transactional
public class STransfertoTopupFeeServiceImpl extends AbstractService<STransfertoTopupFee> implements STransfertoTopupFeeService {
    @Resource
    private STransfertoTopupFeeMapper sTransfertoTopupFeeMapper;

    public List<STransfertoTopupFee> selectList(Map<String,Object> param){
        return sTransfertoTopupFeeMapper.selectList(param);
    }
    public List<Map<String,Object>> topUpHistList(Map<String,Object> param){
        return sTransfertoTopupFeeMapper.topUpHistList(param);
    }




}
