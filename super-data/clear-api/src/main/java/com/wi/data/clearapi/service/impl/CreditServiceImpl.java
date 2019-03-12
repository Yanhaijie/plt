package com.wi.data.clearapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wi.data.clearapi.dao.CAppUserMapper;
import com.wi.data.clearapi.service.CAppUserService;
import com.wi.data.clearapi.service.CreditService;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    @Resource
    private CAppUserService cAppUserService;

    @Value("${spring.data.elasticsearch.ip}")
    private String esIp;
    @Value("${spring.data.elasticsearch.port}")
    private int esPort;


    /**
     * 函数调用，只调用cAppUserService中的函数
     * @param paramMap
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> functionInvoke(Map<String,Object> paramMap) throws Exception{
        Object resultObj = this.invoke(paramMap);
        if(resultObj == null){
            return null;
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>)resultObj;
        return list;
    }


    /**
     * api调用
     * @param paramMap
     * @return
     */
    public Map<String,Object> apiInvoke(Map<String,Object> paramMap){
        return null;
    }

    /**
     * es调用
     * @param paramMap
     * @return
     */
    public int esInvoke(Map<String,String> paramMap,Map<String,Object> conditionValue){
        String requestType = paramMap.get("requestType");
        String url = paramMap.get("url");
        int total = 0;
        if("post".equals(requestType)){
            String jsonString = paramMap.get("jsonString");
            //replaceKey：要替换的文字，替换内容为conditongValue
            String key = paramMap.get("replaceKey");
            if(!StringUtils.isEmpty(key) && conditionValue != null){
                jsonString = this.addConditionInfo(jsonString,key,conditionValue);
            }
            total = this.esPost(url,jsonString);
        }else{
            total = this.esGet(url);
        }
        return total;
    }
    public List<Map<String,Object>> sqlInvoke(Map<String,Object> paramMap){
        return cAppUserService.executeSelect(paramMap);
    }


    private Object invoke(Map<String,Object> paramMap){
        Object resultObj = null;
        String methodName = (String)paramMap.get("methodName");
        //获得当前访问的class
        Class<?> className = cAppUserService.getClass();
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName,Map.class);
            resultObj = method.invoke(cAppUserService,paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObj;
    }


    private int esGet(String url) {
        int total = 0;
        RestClient restClient = RestClient.builder(new HttpHost(esIp, esPort, "http")).build();
        try {
            Map<String,String> params = Collections.singletonMap("pretty", "true");
            Response response = restClient.performRequest("GET", url, params);
            total = readResposneSum(response);
            restClient.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return total;
    }

    private int esPost(String url, String jsonString){
         int total = 0;
        //String jsonString = "{\"query\":{\"match\":{\"device_id\":\"310f02865e61356\"}}";
        //多条件and
        // { "query": { "bool": { "must": [{ "match": { "unique_id": "a5638fe520d46c3" } },{ "match": { "messaage": "perushaan" } }]} } }
        //多条件or
        // { "query": { "bool": { "must": [{ "should": { "unique_id": "a5638fe520d46c3" } },{ "match": { "messaage": "perushaan" } }]} } }

        RestClient restClient = RestClient.builder(new HttpHost(esIp, esPort, "http")).build();
        try {
            Map<String, String> params = Collections.emptyMap();
            params = Collections.singletonMap("pretty", "true");
            NStringEntity nStringEntity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
            Response response = restClient.performRequest("POST",url, params, nStringEntity);
            total = readResposneSum(response);
            restClient.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return total;
    }


    /**
     * 解析es返回数据，获取结果条数
     * @param response
     * @return
     * @throws Exception
     */
    private int readResposneSum(Response response) throws Exception {
        int total = 0;
        BufferedReader brd = new BufferedReader(new BufferedReader(new InputStreamReader(response.getEntity().getContent())));
        String line;
        StringBuilder respongseContext = new StringBuilder();

        while ((line = brd.readLine()) != null) {
            respongseContext.append(line).append("\n");
        }
        if (respongseContext.length() > 0) {
            respongseContext.deleteCharAt(respongseContext.length() - 1);
        }
        String responseStr = respongseContext.toString();
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        JSONObject hits = (JSONObject)jsonObject.get("hits");
        total  = (int)hits.get("total");
        return total;
    }


    /**
     * 字符串替换
     * @param sourceStr
     * @param condition
     * @return
     */
    private String addConditionInfo(String sourceStr,String condition,Map<String,Object> conditionValue){
        if(StringUtils.isEmpty(condition)){
            return sourceStr;
        }
        String[] keys = condition.split(",");
        for (String key : keys){
            sourceStr = sourceStr.replaceAll(key,(String)conditionValue.get(key));
        }
        return sourceStr;
    }

}
