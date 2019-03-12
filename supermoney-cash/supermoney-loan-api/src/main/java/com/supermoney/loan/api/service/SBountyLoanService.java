package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SBountyLoan;
import com.supermoney.loan.api.utils.Service;


/**
 * Created by xionghuifeng on 2018/03/01.
 */
public interface SBountyLoanService extends Service<SBountyLoan> {

    /**
     * 获取第一个可用产品借贷配置
     * @param bountyId
     * @return
     */
    public  SBountyLoan getFirstByBountyId(Integer bountyId);

}
