package com.supermoney.loan.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.dao.SBountyMapper;
import com.supermoney.loan.api.dao.SBussLabelMapper;
import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.entity.SBussLabel;
import com.supermoney.loan.api.service.SBussLabelService;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.MatchesPattern;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/20.
 */
@Service
@Transactional
public class SBussLabelServiceImpl extends AbstractService<SBussLabel> implements SBussLabelService {
    @Resource
    private SBussLabelMapper sBussLabelMapper;

    @Resource
    private SBountyMapper sBountyMapper;
    /**
     * 保存业务标签
     * @param pwd
     * @param labelName
     * @param lableVal
     * @return
     */
    public Result saveLable(String pwd , String labelName, String lableVal)
    {
        if(StringUtils.isBlank(labelName))
        {
            return  ResultGenerator.genFailResult("labelName is null");
        }

        if(!pwd.equals("xionghuifeng")){
            return ResultGenerator.genFailResult("warning!");
        }

        SBussLabel bussLabel=new SBussLabel();
        bussLabel.setBussName(labelName);
        bussLabel=sBussLabelMapper.selectOne(bussLabel);
        if(bussLabel==null){
            bussLabel=new SBussLabel();
            bussLabel.setBussName(labelName);
            bussLabel.setBussVal(lableVal);
            sBussLabelMapper.insert(bussLabel);
        }else {
            bussLabel.setBussVal(lableVal);
            sBussLabelMapper.updateByPrimaryKey(bussLabel);
        }
        return  ResultGenerator.genSuccessResult();
    }

    /**
     * 获取Banner数据
     * @return
     */
    public  Result getByBanner(){
        SBussLabel bussLabel=new SBussLabel();
        bussLabel.setBussName("banner");
        bussLabel=sBussLabelMapper.selectOne(bussLabel);
        if(bussLabel==null){
            ResultGenerator.genFailResult("label is null");
        }
        List<Map<String,Object>> banners=new ArrayList<>();
        String[] imgs=bussLabel.getBussVal().split(",");
        for(String img:imgs){
            String[] ary=img.split("\\|");
            Map<String,Object> item=new HashMap<>();
            if(ary.length>4){
                item.put("picture",ary[0]);
                item.put("adsType",ary[1]);
                item.put("content",ary[2]);
                item.put("title",ary[3]);
                item.put("needLogin",ary[4]);
                banners.add(item);
            }
        }
        return  ResultGenerator.genSuccessResult(banners);

    }


    @Override
    public Result getByNameNotSplit(String labelName) {
        SBussLabel bussLabel=new SBussLabel();
        bussLabel.setBussName(labelName);
        bussLabel=sBussLabelMapper.selectOne(bussLabel);
        if(bussLabel==null){
            ResultGenerator.genFailResult("label is null");
        }
        return  ResultGenerator.genSuccessResult(bussLabel.getBussVal());
    }

    /**
     * 获取业务标签
     * @param labelName
     * @return
     */
    public  Result getByName(String labelName){
        SBussLabel bussLabel=getByLabelName(labelName);
        if(bussLabel==null){
            ResultGenerator.genFailResult("label is null");
        }
        String[] ary=bussLabel.getBussVal().split(",");
        return  ResultGenerator.genSuccessResult(ary);
    }

    /**
     * 获取数据
     * @param labelName
     * @return
     */
    public  SBussLabel getByLabelName(String labelName){
        SBussLabel bussLabel=new SBussLabel();
        bussLabel.setBussName(labelName);
        return  sBussLabelMapper.selectOne(bussLabel);
    }

    /**
     *
     * @param labelName
     * @return
     */
    public  Result getByLabelToJson(String labelName){
        SBussLabel label=getByLabelName(labelName);
        JSONObject json= JSON.parseObject(label.getBussVal());
        return  ResultGenerator.genSuccessResult(json);
    }

    /**
     * app版本信息
     * @param packageName
     * @return
     */
    public  Result appVersion(String packageName){

        Map<String,String> reqHeaders= RequestUntil.getUserAgentParams();
        String pk=reqHeaders.get("PACKAGE");
        String appVersion=reqHeaders.get("VERSION");
        String channel=reqHeaders.get("CHANNEL").toLowerCase();

        SBussLabel label=getByLabelName("appVersion");
        List<Map> loanList=new ArrayList<Map>();
        loanList= JSONObject.parseArray(label.getBussVal(),Map.class);

        Map<String,Object> result=new HashMap<>();
        for(Map<String,Object> item:loanList){
            String apppk=item.get("packageName").toString();
            String appchannel=item.get("channel").toString();
            if(appchannel.equals(channel) && pk.equals(apppk) ){
                result=item;
                String serverVersion=item.get("version").toString();
                result.put("isUpdate",compeToVersion(serverVersion,appVersion));
                break;
            }
        }
        return  ResultGenerator.genSuccessResult(result);
    }

    /**
     * 版本对比
     * @param serverVersion 服务器版本
     * @param appVersion    应用版本
     * @return
     */
    public  boolean compeToVersion(String serverVersion,String appVersion){

        if(StringUtils.isBlank(serverVersion) || StringUtils.isBlank(appVersion)){
            return  false;
        }

        String[] seversionArray=serverVersion.split("\\.");
        String[] appvercionArray=appVersion.split("\\.");
        if(seversionArray.length!=appvercionArray.length){
            return  false;
        }
        int maxVal=5;
        if(seversionArray.length>maxVal){
            return  false;
        }
        int a=0,b=0;
        for(int i=0;i<seversionArray.length;i++){
            a+=(Integer.valueOf(seversionArray[i])+1)*Math.pow(10,(maxVal-i));
            b+=(Integer.valueOf(appvercionArray[i])+1)*Math.pow(10,(maxVal-i));
        }
        return a>b;
    }

    /**
     * APP模块功能开关
     * @return
     */
    public  Result appModules(){
        SBussLabel bussLabel = getByLabelName("appModules");
        JSONObject appModulesJson = JSONObject.parseObject(bussLabel.getBussVal());
        return  ResultGenerator.genSuccessResult(appModulesJson);
    }
}
