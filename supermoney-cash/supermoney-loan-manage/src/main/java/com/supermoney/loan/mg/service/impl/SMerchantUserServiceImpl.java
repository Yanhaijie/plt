package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SMerchantUserMapper;
import com.supermoney.loan.mg.entity.SMerchantUser;
import com.supermoney.loan.mg.service.SMerchantUserService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by bear on 2018/08/06.
 */
@Service
@Transactional
public class SMerchantUserServiceImpl extends AbstractService<SMerchantUser> implements SMerchantUserService {
    @Resource
    private SMerchantUserMapper sMerchantUserMapper;

    @Override
    public List<SMerchantUser> selectByParam(Map<String, Object> param) {
        return sMerchantUserMapper.selectByParam(param);
    }

    public   List<Map<String, Object>> selectDrop(){
        return  sMerchantUserMapper.selectDrop();
    }
}
