package com.wi.data.clearapi.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wi.data.clearapi.utils.ElasticsearchUtil;
import com.wi.data.clearapi.utils.RequestUntil;
import com.wi.data.clearapi.utils.Result;
import com.wi.data.clearapi.utils.ResultGenerator;
import com.wi.data.clearapi.entity.CAppUser;
import com.wi.data.clearapi.service.CAppUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Created by CodeGenerator on 2018/07/02.
*/
@RestController
@RequestMapping("/c/device/data/clear")
@Api(value = "/c/device/data/clear",description = "")
public class CAppUserController {
    @Resource
    private CAppUserService cAppUserService;
    @Autowired
    private ElasticsearchUtil elasticsearchUtil;
    @Value("${spring.data.elasticsearch.ip}")
    private String host;
    @Value("${spring.data.elasticsearch.port}")
    private int port;
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    @PostMapping("/callrecords")
    public Result callrecordsList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        if (tempParam == null || tempParam.get("username") == null || tempParam.get("username").toString().length() == 0){
            return ResultGenerator.genFailResult("");
        }
        List<Map<String,Object>> list = cAppUserService.selectCallrecords(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/app")
    public Result appList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        if (tempParam == null || tempParam.get("username") == null || tempParam.get("username").toString().length() == 0){
            return ResultGenerator.genFailResult("");
        }
        List<Map<String,Object>> list = cAppUserService.selectApp(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/mobile")
    public Result mobileList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        if (tempParam == null || tempParam.get("username") == null || tempParam.get("username").toString().length() == 0){
            return ResultGenerator.genFailResult("");
        }
        List<Map<String,Object>> list = cAppUserService.selectMobile(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/msg")
    public Result msgList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        if (tempParam == null || tempParam.get("username") == null || tempParam.get("username").toString().length() == 0){
            return ResultGenerator.genFailResult("");
        }
        List<Map<String,Object>> list = cAppUserService.selectMsg(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/location")
    public Result locationList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        System.out.println("====================");
       if (tempParam == null || tempParam.get("username") == null || tempParam.get("username").toString().length() == 0){
            return ResultGenerator.genFailResult("");
        }
        List<Map<String,Object>> list = cAppUserService.selectLocation(tempParam);
        System.out.println(list.getClass());
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/appuser")
    public Result appuserList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param= RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = cAppUserService.selectAppUser((Map<String,Object>)param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/itemcount")
    public Result clearAppUserItemCount(String userName, String uniqueId) {
        return  cAppUserService.selectItemCount(userName,uniqueId);
    }


    /**
     *     分词匹配短信
     */
    @PostMapping("/msgSearch")
    public Result msgSearch(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Map<String,Object> param=RequestUntil.getParams();
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        Map<String,String> filterMap = Maps.newHashMap();
        if(tempParam !=  null){
            String username = (String) tempParam.get("username");
            String messaage = (String) tempParam.get("messaage");
            String number = (String) tempParam.get("number");
            if(StringUtils.isNotEmpty(username)){
                filterMap.put("username",username);
            }
            if(StringUtils.isNotEmpty(messaage)){
                filterMap.put("messaage",messaage);
            }
            if(StringUtils.isNotEmpty(number)){
                filterMap.put("number",number);
            }
        }
        TransportClient client = elasticsearchUtil.getClient(clusterName,host,port);
        SearchResponse response =  elasticsearchUtil.search(client,"device_msg", filterMap, size*(page-1),size);
        SearchHits searchHitsDetail = response.getHits();
        int total = (int) searchHitsDetail.getTotalHits();
        if(total == 0 && tempParam !=  null){
            String username = (String) tempParam.get("username");
            if(StringUtils.isNotEmpty(username)){
                //检查mysql是否有数据，有的话上传数据
                List<Map<String,Object>> list = cAppUserService.selectMsg(tempParam);
                //有数据则上传到es
                if(list != null && list.size() > 0){
                    //上传数据
                    try {
                        elasticsearchUtil.saveData(client,"device_msg","doc",list);
                        response =  elasticsearchUtil.search(client,"device_msg", filterMap, size*(page-1),size);
                    }catch (Exception ex){}
                    searchHitsDetail = response.getHits();
                    total = (int) searchHitsDetail.getTotalHits();
                }
            }
        }
        Page<Map<String,Object>> list = new Page<Map<String,Object>>(page,size,true);
        list.setTotal(searchHitsDetail.getTotalHits());
        for (int j = 0; j < searchHitsDetail.getHits().length; j++) {
            String json = searchHitsDetail.getHits()[j].getSourceAsString();
            System.out.println(""+j+"=="+json);
            Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(json);
            HashMap<String, Object> dataAsMap = new HashMap<String, Object>(jsonMap);
            list.add(dataAsMap);
        }
        list.setTotal(total);
        list.setPages((total/size)+1);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }




}
