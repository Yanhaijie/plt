package com.supermoney.loan.mg.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.mg.dao.SBussLimitMapper;
import com.supermoney.loan.mg.entity.SBussLimit;
import com.supermoney.loan.mg.service.SBussLimitService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by bear on 2018/08/07.
 */
@Service
@Transactional
public class SBussLimitServiceImpl extends AbstractService<SBussLimit> implements SBussLimitService {
    @Resource
    private SBussLimitMapper sBussLimitMapper;

    /**
     * 保存借款费率区间
     * @param bountyLoanId
     * @param json
     * @return
     */
    @Override
    public Result saveLoanLimit(Integer bountyLoanId,String json){
        //删除
        delLoanLimit(bountyLoanId);
//        //新增
//        if(StringUtils.isNotBlank(json) && !"null".equals(json)){
//            List<Map> list=new ArrayList<Map>();
//            list= JSONObject.parseArray(json,Map.class);
//            for(Map item:list){
//                 SBussLimit bussItem=new SBussLimit();
//                 bussItem.setRelateId(bountyLoanId);
//                 bussItem.setLimitType(0);
//                 bussItem.setStartval(new BigDecimal(item.get("startVal").toString()));
//                 bussItem.setEndval(new BigDecimal(item.get("endVal").toString()));
//                 bussItem.setTermval(new BigDecimal(item.get("termVal").toString()));
//                 sBussLimitMapper.insertSelective(bussItem);
//            }
//        }
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 删除借款费率区间
     * @param bountyLoanId
     * @return
     */
    @Override
    public  Result delLoanLimit(Integer bountyLoanId){
        SBussLimit limit=new SBussLimit();
        limit.setRelateId(bountyLoanId);
        limit.setLimitType(0);
        sBussLimitMapper.delete(limit);
        return  ResultGenerator.genSuccessResult();
    }
    /**
     * 获取借款费率区间
     * @param bountyLoanId
     * @return
     */
    public  List<SBussLimit> getLoanLimit(Integer bountyLoanId){
        SBussLimit param=new SBussLimit();
        param.setRelateId(bountyLoanId);
        param.setLimitType(0);
        return sBussLimitMapper.select(param);
    }

}
