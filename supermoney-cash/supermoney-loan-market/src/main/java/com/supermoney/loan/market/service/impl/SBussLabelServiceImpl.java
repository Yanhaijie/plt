package com.supermoney.loan.market.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.market.dao.SBussLabelMapper;
import com.supermoney.loan.market.entity.SBussLabel;
import com.supermoney.loan.market.service.SBussLabelService;
import com.supermoney.loan.market.utils.AbstractService;
import com.supermoney.loan.market.utils.Result;
import com.supermoney.loan.market.utils.ResultGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by bear on 2018/11/13.
 */
@Service
@Transactional
public class SBussLabelServiceImpl extends AbstractService<SBussLabel> implements SBussLabelService {
    @Resource
    private SBussLabelMapper sBussLabelMapper;

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




}
