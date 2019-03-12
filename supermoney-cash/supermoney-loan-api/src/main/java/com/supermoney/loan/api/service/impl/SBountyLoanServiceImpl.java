package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SBountyLoanMapper;
import com.supermoney.loan.api.entity.SBountyLoan;
import com.supermoney.loan.api.service.SBountyLoanService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by xionghuifeng on 2018/03/01.
 */
@Service
@Transactional
public class SBountyLoanServiceImpl extends AbstractService<SBountyLoan> implements SBountyLoanService {
    @Resource
    private SBountyLoanMapper sBountyLoanMapper;

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
