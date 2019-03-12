package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SBussLimitMapper;
import com.supermoney.loan.api.dao.SMerchantOrderMapper;
import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.entity.SBountyLoan;
import com.supermoney.loan.api.entity.SMerchantOrder;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.vo.LoanMoneyLimitRateVo;
import com.supermoney.loan.api.service.SBountyLoanService;
import com.supermoney.loan.api.service.SMerchantOrderService;
import com.supermoney.loan.api.utils.AbstractService;
import com.supermoney.loan.api.utils.BussCodeGenerate;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/08/06.
 */
@Service
@Transactional
public class SMerchantOrderServiceImpl extends AbstractService<SMerchantOrder> implements SMerchantOrderService {
    @Resource
    private SMerchantOrderMapper sMerchantOrderMapper;

    @Resource
    private SBussLimitMapper sBussLimitMapper;

    @Resource
    private SBountyLoanService sBountyLoanService;

    /**
     * API商户下单
     * @param needAmount
     * @param limit
     * @param reason
     * @param user
     * @param countryCode
     * @return
     */
    public Result toApiOrder(SBounty bountyProduct, Integer needAmount, Integer limit, String reason, SUser user, String countryCode){

        if(hasIngOrder(user.getId(),bountyProduct.getId())){
            return ResultGenerator.genFailResult(" Pinjaman anda sudah ada, harap lengkapi informasi");
        }
        BigDecimal loanAmount=new BigDecimal(needAmount);
        SMerchantOrder order=new SMerchantOrder();
        order.setBountyId(bountyProduct.getId());
        order.setLoanAmount(loanAmount);
        order.setDuration(limit);
        order.setLoanReason(reason);
        order.setUserId(user.getId());
        order.setAreaCode(Integer.valueOf(countryCode));
        order.setMerchantId(bountyProduct.getMerchantId());
        order.setmOrderCode(bountyProduct.getMerchantProductCode());
        order.setOrderCode(BussCodeGenerate.getApiOrderCode());
        //利息
        SBountyLoan bountyLoan=sBountyLoanService.getFirstByBountyId(bountyProduct.getId());
        if(bountyLoan==null){
            return  ResultGenerator.genFailResult("have not set!");
        }
        order.setRate(bountyLoan.getLoanRate());
        //固定费
        order.setFeeAmount(bountyLoan.getFeeMoeny());
        //服务费率
        List<Map<String,Object>> feelimits= sBussLimitMapper.selectFeeLimit(bountyProduct.getId());
        BigDecimal limitRate=BigDecimal.ZERO;
        for(Map item :feelimits){
            // val>= start  and   val< end
            BigDecimal startVal=new BigDecimal(item.get("startVal").toString());
            BigDecimal endVal=new BigDecimal(item.get("endVal").toString());
            BigDecimal rate=new BigDecimal(item.get("rate").toString());
            if( loanAmount.compareTo(startVal)>=0 && loanAmount.compareTo(endVal)==-1){
                limitRate=rate;
            }else if(loanAmount.compareTo(startVal)>=0 && endVal.compareTo( new BigDecimal(0))==0) {
                limitRate=rate;
            }
        }
        order.setFeeRate(limitRate);
        order.setRepayMethod(bountyLoan.getRepaymentMethod());
        //默认值
        order.setGotAmount(BigDecimal.ZERO);
        order.setFeeAmount(BigDecimal.ZERO);
        order.setOverdueAmount(BigDecimal.ZERO);
        order.setWaitRepayAmount(BigDecimal.ZERO);
        order.setRepaymentedAmount(BigDecimal.ZERO);
        order.setInterestAmount(BigDecimal.ZERO);
        order.setOrderStatus(0);
        order.setUseStatus(getUseStatus(user.getId()));
        order.setOverdueLimit(0);
        order.setUserName(user.getUserName());


        sMerchantOrderMapper.insertSelective(order);

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 状态根据，用户是否已有采集数据来判断
     * @param userId
     * @return
     */
    public  Integer getUseStatus(Integer userId){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        return sMerchantOrderMapper.hasUserData(map)>0 ? 1:0;
    }

    /**
     * 此产品是否存在进行中的订单
     * @param userId
     * @param bountyId
     * @return
     */
    public boolean hasIngOrder(Integer userId,Integer bountyId){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("bountyId",bountyId);
       return sMerchantOrderMapper.bountyHasOrder(map)>0 ;
    }


}
