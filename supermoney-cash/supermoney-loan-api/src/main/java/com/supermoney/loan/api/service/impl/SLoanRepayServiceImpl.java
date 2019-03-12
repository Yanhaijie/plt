package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SLoanRepayMapper;
import com.supermoney.loan.api.entity.SLoanGrant;
import com.supermoney.loan.api.entity.SLoanRepay;
import com.supermoney.loan.api.service.SLoanRepayService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by xionghuifeng on 2018/06/06.
 */
@Service
@Transactional
public class SLoanRepayServiceImpl extends AbstractService<SLoanRepay> implements SLoanRepayService {
    @Resource
    private SLoanRepayMapper sLoanRepayMapper;

    /**
     * 根据借款单号获取
     * @param userId
     * @param orderCode
     * @return
     */
    public List<SLoanRepay> getByOrderCode(Integer userId,String orderCode){
        SLoanRepay param=new SLoanRepay();
        param.setUserId(userId);
        param.setOrderCode(orderCode);
        return  sLoanRepayMapper.select(param);
    }

}
