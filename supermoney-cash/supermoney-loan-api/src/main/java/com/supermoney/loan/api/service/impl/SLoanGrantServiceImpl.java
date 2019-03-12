package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SLoanGrantMapper;
import com.supermoney.loan.api.entity.SLoanGrant;
import com.supermoney.loan.api.service.SLoanGrantService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/06/06.
 */
@Service
@Transactional
public class SLoanGrantServiceImpl extends AbstractService<SLoanGrant> implements SLoanGrantService {
    @Resource
    private SLoanGrantMapper sLoanGrantMapper;

    /**
     * 根据借款单号获取
     * @param userId
     * @param orderCode
     * @return
     */
    @Override
    public  SLoanGrant getByOrderCode(Integer userId,String orderCode){
        SLoanGrant grant=new SLoanGrant();
        grant.setOrderCode(orderCode);
        grant.setUserId(userId);
        return  sLoanGrantMapper.selectOne(grant);
    }

}
