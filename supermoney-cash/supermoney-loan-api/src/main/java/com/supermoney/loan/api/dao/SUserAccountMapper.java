package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SUserAccount;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserAccountMapper extends Mapper<SUserAccount> {

    /**
     * 单个具体的账户的信息
     *
     * @param maps
     * @return
     */
    SUserAccount getUserAccountEntity(Map<String, Object> maps);

    /**
     * 所有账户的信息
     *
     * @param maps
     * @return
     */
    List<SUserAccount> getUserAccountList(Map<String, Object> maps);

    /**
     * 修改单个账户的信息
     *
     * @param maps userid  accountid type
     * @return
     */
    Integer editUserAccount(Map<String, Object> maps);


    /**
     * 修改单个账户的信息
     *
     * @param maps userid  accountid type
     * @return
     */
    Integer editUserAccountReduce(Map<String, Object> maps);


    Integer addUserAccount(SUserAccount account);

}
