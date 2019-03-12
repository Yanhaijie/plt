package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SMerchantUser;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by bear on 2018/08/06.
 */
public interface SMerchantUserService extends Service<SMerchantUser> {

    public List<SMerchantUser> selectByParam(Map<String, Object> param);

    public   List<Map<String, Object>> selectDrop();

}
