package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SLoanGrant;
import com.supermoney.loan.api.entity.SLoanRepay;
import com.supermoney.loan.api.utils.Service;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;


/**
 * Created by xionghuifeng on 2018/06/06.
 */
public interface SLoanRepayService extends Service<SLoanRepay> {

    /**
     * 根据借款单号获取
     * @param userId
     * @param orderCode
     * @return
     */
    public List<SLoanRepay> getByOrderCode(Integer userId,String orderCode);

}
