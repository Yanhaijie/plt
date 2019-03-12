package com.supermoney.loan.dao;

import com.supermoney.loan.entity.SUser;
import com.supermoney.loan.vo.UserSendSmsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SUserMapper{
    /**
     * 用户信息
     * @param
     * @return
     */
    public List<UserSendSmsVo> getAllSUser();

    SUser getUserById(int userId);

    void updateUserLoginLastTime(SUser sUser);
}