package com.supermoney.loan.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.dao.SBussLabelMapper;
import com.supermoney.loan.entity.SBussLabel;
import com.supermoney.loan.service.SBussLabelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/20.
 */
@Service
@Transactional
public class SBussLabelServiceImpl implements SBussLabelService{

    @Resource
    private SBussLabelMapper sBussLabelMapper;


    public String getSmsSendTemplate(String key){
        String template=sBussLabelMapper.getAllTemplate();
        String[] arr=template.split("##");
        for(String s:arr){
            String[] par=s.split("@@");
            if(key.equals(par[0])){
                return par[1];
            }
        }
        return "";
    }
}
