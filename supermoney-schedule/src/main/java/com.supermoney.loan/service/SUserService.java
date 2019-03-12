package com.supermoney.loan.service;

import com.supermoney.loan.entity.SUser;
import com.supermoney.loan.vo.UserSendSmsVo;

import java.util.List;

public interface SUserService{

    public List<UserSendSmsVo> getAllSUser();

    SUser getUserById(int userId);

    void updateUserLoginLastTime(SUser sUser);
}
