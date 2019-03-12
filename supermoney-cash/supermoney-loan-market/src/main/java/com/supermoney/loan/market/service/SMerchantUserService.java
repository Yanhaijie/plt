package com.supermoney.loan.market.service;
import com.supermoney.loan.market.entity.SMerchantUser;
import com.supermoney.loan.market.utils.Result;
import com.supermoney.loan.market.utils.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by bear on 2018/08/06.
 */
public interface SMerchantUserService extends Service<SMerchantUser> {

    public boolean checkScretKey(String scretkey, HttpServletRequest request);

}
