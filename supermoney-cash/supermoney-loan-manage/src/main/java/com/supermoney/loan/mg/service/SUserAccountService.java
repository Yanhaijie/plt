package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.entity.SUserAccount;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/13.
 */
public interface SUserAccountService extends Service<SUserAccount> {
    public List<Map<String, Object>> getAccountList(Map<String, Object> param);
}
