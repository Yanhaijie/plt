package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SBussLimit;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;


/**
 * Created by bear on 2018/08/07.
 */
public interface SBussLimitService extends Service<SBussLimit> {

    /**
     * 保存借款费率区间
     * @param bountyLoanId
     * @param json
     * @return
     */
    public Result saveLoanLimit(Integer bountyLoanId, String json);
    /**
     * 删除借款费率区间
     * @param bountyLoanId
     * @return
     */
    public  Result delLoanLimit(Integer bountyLoanId);
    /**
     * 获取借款费率区间
     * @param bountyLoanId
     * @return
     */
    public List<SBussLimit> getLoanLimit(Integer bountyLoanId);

}
