package com.wi.data.clearapi.service;
import com.wi.data.clearapi.entity.CAppUser;
import com.wi.data.clearapi.utils.Service;

import java.util.List;
import java.util.Map;


public interface CreditService{

    public List<Map<String,Object>> functionInvoke(Map<String,Object> paramMap) throws Exception;
    public Map<String,Object> apiInvoke(Map<String,Object> paramMap);
    public int esInvoke(Map<String,String> paramMap,Map<String,Object> conditionParam);
    public List<Map<String,Object>> sqlInvoke(Map<String,Object> paramMap);

}
