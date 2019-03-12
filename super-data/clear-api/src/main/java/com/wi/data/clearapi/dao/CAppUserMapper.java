package com.wi.data.clearapi.dao;

import com.wi.data.clearapi.entity.CAppUser;
import com.wi.data.clearapi.utils.Mapper;

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
    public List<Map<String, Object>> selectItemCount(Map<String,Object> param);
    public List<Map<String, Object>> selectListCount(Map<String,Object> param);
    public List<Map<String, Object>> selectAllCount(Map<String,Object> param);
    public List<Map<String, Object>> selectAppCount(Map<String,Object> param);
    public List<Map<String, Object>> selectMsgCount(Map<String,Object> param);
    public List<Map<String, Object>> selectLocationCount(Map<String,Object> param);
    public List<Map<String, Object>> selectMobileCount(Map<String,Object> param);
    public List<Map<String, Object>> selectCallCount(Map<String,Object> param);
    public List<Map<String, Object>> selectCountList(Map<String,Object> param);

    public List<Map<String,Object>> executeSelect(Map<String,Object> map);
}