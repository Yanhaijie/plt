package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.CAppUser;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface CAppUserMapper extends Mapper<CAppUser> {
    public List<CAppUser> selectList();

    public List<Map<String, Object>> selectAppUser(Map<String,Object> param);

    public List<Map<String, Object>> selectMobile(Map<String,Object> param);

    public List<Map<String, Object>> selectCallrecords(Map<String,Object> param);

    public List<Map<String, Object>> selectApp(Map<String,Object> param);

    public List<Map<String, Object>> selectLocation(Map<String,Object> param);

    public List<Map<String, Object>> selectMsg(Map<String,Object> param);
}