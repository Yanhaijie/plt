package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SMessageUser;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SMessageUserMapper extends Mapper<SMessageUser> {

    public List<SMessageUser> selectList(Map<String,Object> map);

    /**
     * 批量插入
     * @param messageUserList
     */
    public void batchInsert( List<SMessageUser> messageUserList);
}