package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SBountyLoan;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;


/**
 * Created by xionghuifeng on 2018/02/27.
 */
public interface SBountyLoanService extends Service<SBountyLoan> {
    /**
     * 根据任务下的所有产品
     * @param bountyId
     * @return
     */
    public Result getByBountyId(Integer bountyId);
    /**
     * 获取第一个可用产品借贷配置
     * @param bountyId
     * @return
     */
    public  SBountyLoan getFirstByBountyId(Integer bountyId);

}
