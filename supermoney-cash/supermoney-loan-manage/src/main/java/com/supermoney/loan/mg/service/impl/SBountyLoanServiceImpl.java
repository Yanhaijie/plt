package com.supermoney.loan.mg.service.impl;

import com.alibaba.fastjson.JSON;
import com.supermoney.loan.mg.dao.SBountyLoanMapper;
import com.supermoney.loan.mg.entity.SBountyLoan;
import com.supermoney.loan.mg.entity.SBussLimit;
import com.supermoney.loan.mg.service.SBountyLoanService;
import com.supermoney.loan.mg.service.SBussLimitService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/02/27.
 */
@Service
@Transactional
public class SBountyLoanServiceImpl extends AbstractService<SBountyLoan> implements SBountyLoanService {
    @Resource
    private SBountyLoanMapper sBountyLoanMapper;

    @Resource
    private SBussLimitService sBussLimitService;
    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SBountyLoan> getList(Map<String,Object> param)
    {
        return  sBountyLoanMapper.selectList(param);
    }

    /**
     * 根据任务下的所有产品
     * @param bountyId
     * @return
     */
    public Result getByBountyId(Integer bountyId){
        Map<String,Object> param=new HashMap();
        param.put("bountyId",bountyId);
        List<SBountyLoan> list=getList(param);
        for(SBountyLoan item:list){
            List<SBussLimit> feeLimits=sBussLimitService.getLoanLimit(item.getId());
            item.setOpt(JSON.toJSONString(feeLimits));
        }
        return ResultGenerator.genSuccessResult(list);
    }


    /**
     * 获取第一个可用产品借贷配置
     * @param bountyId
     * @return
     */
    public  SBountyLoan getFirstByBountyId(Integer bountyId){
        SBountyLoan loan=new SBountyLoan();
        loan.setBountyLoanStatus(1);
        loan.setBountyId(bountyId);
        List<SBountyLoan> list= sBountyLoanMapper.select(loan);
        return list.size()>0 ? list.get(0):null;

    }


}
