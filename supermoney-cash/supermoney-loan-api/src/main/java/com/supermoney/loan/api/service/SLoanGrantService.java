package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SLoanGrant;
import com.supermoney.loan.api.utils.Service;
import org.omg.CORBA.PUBLIC_MEMBER;


/**
 * Created by wenyuhao on 2018/06/06.
 */
public interface SLoanGrantService extends Service<SLoanGrant> {
    /**
     * 根据借款单号获取
     * @param userId
     * @param orderCode
     * @return
     */
    public  SLoanGrant getByOrderCode(Integer userId,String orderCode);

}
