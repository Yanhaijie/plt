package com.supermoney.loan.service.impl;


import com.supermoney.loan.dao.SUserMapper;
import com.supermoney.loan.entity.SUser;
import com.supermoney.loan.service.SUserService;
import com.supermoney.loan.vo.UserSendSmsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by xionghuifeng on 2018/01/08.
 */
@Service
public class SUserServiceImpl implements SUserService {

    @Autowired
    SUserMapper sUserMapper;

    @Override
    public List<UserSendSmsVo> getAllSUser(){
        return sUserMapper.getAllSUser();
    }

    @Override
    public SUser getUserById(int userId) {
        return sUserMapper.getUserById(userId);
    }

    @Override
    public void updateUserLoginLastTime(SUser sUser) {
        sUserMapper.updateUserLoginLastTime(sUser);
    }


}
