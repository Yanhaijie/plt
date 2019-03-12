package com.wi.data.clearapi.service.impl;

import com.wi.data.clearapi.dao.CAppUserMapper;
import com.wi.data.clearapi.entity.CAppUser;
import com.wi.data.clearapi.service.CAppUserService;
import com.wi.data.clearapi.utils.AbstractService;
import com.wi.data.clearapi.utils.Result;
import com.wi.data.clearapi.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/07/02.
 */
@Service
@Transactional
public class CAppUserServiceImpl extends AbstractService<CAppUser> implements CAppUserService {

    @Resource
    private CAppUserMapper cAppUserMapper;

    @Override
    public List<CAppUser> selectList() {
        return cAppUserMapper.selectAll();
    }

    @Override
    public List<Map<String, Object>> selectCallrecords(Map<String, Object> param) {
        return cAppUserMapper.selectCallrecords(param);
    }

    @Override
    public List<Map<String, Object>> selectMobile(Map<String, Object> param) {
        return cAppUserMapper.selectMobile(param);
    }

    @Override
    public List<Map<String, Object>> selectApp(Map<String, Object> param) {
        return cAppUserMapper.selectApp(param);
    }

    @Override
    public List<Map<String, Object>> selectAppUser(Map<String, Object> param) {
        return cAppUserMapper.selectAppUser(param);
    }

    @Override
    public Result selectItemCount(String userName, String uniqueId) {
        Map<String, Object> param=new HashMap<>();
        param.put("userName",userName);
        param.put("uniqueId",uniqueId);
        Map<String, Object> item=cAppUserMapper.selectItemCount(param).get(0);
        return ResultGenerator.genSuccessResult(item);
    }

    @Override
    public List<Map<String, Object>> selectListCount(Map<String, Object> param) {
        return cAppUserMapper.selectListCount(param);
    }

    public List<Map<String, Object>> selectLocation(Map<String, Object> param) {
        return cAppUserMapper.selectLocation(param);
    }

    @Override
    public List<Map<String, Object>> selectMsg(Map<String, Object> param) {
        return cAppUserMapper.selectMsg(param);
    }
    @Override
    public List<Map<String, Object>> selectAllCount(Map param){
        return cAppUserMapper.selectAllCount(param);
    }
    @Override
    public List<Map<String, Object>> selectAppCount(Map<String,Object> param){
        return cAppUserMapper.selectAppCount(param);
    }
    @Override
    public List<Map<String, Object>> selectMsgCount(Map<String,Object> param){
        return cAppUserMapper.selectMsgCount(param);
    }
    @Override
    public List<Map<String, Object>> selectLocationCount(Map<String,Object> param){
        return cAppUserMapper.selectLocationCount(param);
    }
    @Override
    public List<Map<String, Object>> selectMobileCount(Map<String,Object> param){
        return cAppUserMapper.selectMobileCount(param);
    }
    @Override
    public List<Map<String, Object>> selectCallCount(Map<String,Object> param){
        return cAppUserMapper.selectCallCount(param);
    }
    @Override
    public List<Map<String, Object>> selectCountList(Map<String,Object> param){
        return cAppUserMapper.selectCountList(param);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly=true)
    public List<Map<String,Object>> executeSelect(Map<String,Object> param){
        return cAppUserMapper.executeSelect(param);
    }
}
