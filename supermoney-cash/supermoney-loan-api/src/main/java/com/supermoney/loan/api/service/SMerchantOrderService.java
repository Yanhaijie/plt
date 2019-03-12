package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.entity.SMerchantOrder;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;


/**
 * Created by @author on 2018/08/06.
 */
public interface SMerchantOrderService extends Service<SMerchantOrder> {

    /**
     * API商户下单
     * @param needAmount
     * @param limit
     * @param reason
     * @param user
     * @param countryCode
     * @return
     */
    public Result toApiOrder(SBounty bountyProduct, Integer needAmount, Integer limit, String reason, SUser user, String countryCode);

}
