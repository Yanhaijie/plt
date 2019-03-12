package com.wi.data.clearapi.service;
import com.wi.data.clearapi.entity.CAppUser;
import com.wi.data.clearapi.utils.Result;
import com.wi.data.clearapi.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/07/02.
 */
public interface CAppUserService extends Service<CAppUser> {

    List<CAppUser> selectList();
    public List<Map<String, Object>> selectCallrecords(Map<String, Object> param);
    public List<Map<String, Object>> selectMobile(Map<String,Object> param);
    public List<Map<String, Object>> selectApp(Map<String,Object> param);
    public List<Map<String, Object>> selectAppUser(Map<String,Object> param);
    public Result selectItemCount(String userName, String uniqueId);
    public List<Map<String, Object>> selectListCount(Map<String, Object> param);
    public List<Map<String, Object>> selectLocation(Map<String,Object> param);
    public List<Map<String, Object>> selectMsg(Map<String,Object> param);
    public List<Map<String, Object>> selectAllCount(Map param);
    public List<Map<String, Object>> selectAppCount(Map<String,Object> param);
    public List<Map<String, Object>> selectMsgCount(Map<String,Object> param);
    public List<Map<String, Object>> selectLocationCount(Map<String,Object> param);
    public List<Map<String, Object>> selectMobileCount(Map<String,Object> param);
    public List<Map<String, Object>> selectCallCount(Map<String,Object> param);
    public List<Map<String, Object>> selectCountList(Map<String,Object> param);
    public List<Map<String,Object>> executeSelect(Map<String,Object> param);

}
