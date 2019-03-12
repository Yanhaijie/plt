package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SUserPayMapper;
import com.supermoney.loan.api.entity.SUserAccount;
import com.supermoney.loan.api.entity.SUserBind;
import com.supermoney.loan.api.entity.SUserCash;
import com.supermoney.loan.api.entity.SUserPay;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.AbstractService;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by xionghuifeng on 2018/04/25.
 */
@Service
@Transactional
public class SUserPayServiceImpl extends AbstractService<SUserPay> implements SUserPayService {

    private static final Logger logger = LoggerFactory.getLogger(SUserPayServiceImpl.class);

    @Resource
    private SUserPayMapper sUserPayMapper;

    @Resource
    private SExchangeRateService sExchangeRateService;
    @Resource
    private UserAccountBussService userAccountBussService;
    @Resource
    private SUserBindService sUserBindService;
    @Resource
    private SUserAccountService sUserAccountService;

    @Override
    public BigDecimal getPaySum(SUserPay sUserPay) {
        return sUserPayMapper.getPaySum(sUserPay);
    }
    @Override
    public List<SUserPay> getList(SUserPay sUserPay){
        return sUserPayMapper.getList(sUserPay);
    }

    @Override
    public Result getAwardForNewUser(SUserPay sUserPay){
        Result  result =  ResultGenerator.genSuccessResult(1);
        sUserPay.setPayType(Constants.PayType.STATUS_GET_AWARD);
        List<SUserPay> sUserPayList = this.getList(sUserPay);
        if(sUserPayList == null || sUserPayList.size() < 1){
            //老用户，直接送2000奖金，
            sUserPay.setPayMoney( sExchangeRateService.toUsdByCountry(Constants.Country.INDONESIA_CR,new BigDecimal(2000)));
            sUserPay.setPayStatus(Constants.PayStatus.STATUS_AUDIT_WAIT);
            //老用户直接领取（默认审核通过的）
            sUserPay.setGetStatus(Constants.PayGetStatus.STATUS_DONE);
            sUserPay.setRemark("老用户领取奖励");
            sUserPay.setPaySourceId(Constants.PAY_ACOUNT);
            sUserPay.setPayPlatform(Constants.SOURCE_APP);
            sUserPay.setAreaCode(Constants.Country.INDONESIA_CODE);
            this.save(sUserPay);
            userAccountBussService.inMoenyAccount(Constants.PayType.STATUS_GET_AWARD,Constants.PayType.STATUS_GET_AWARD,sUserPay.getUserId(),sUserPay.getPayMoney());
            result.setMessage("Bonus sebesar Rp 2.000 telah ditranfer ke akun bank anda");
        }else{
            SUserPay payAward =  sUserPayList.get(0);
            if(payAward.getPayStatus().intValue() != 0){
                result.setData(2);
                result.setMessage("Anda telah menarik harisah senilai ₱ 8");
            }else{
                payAward.setPayStatus(1);
                this.update(payAward);
                userAccountBussService.inMoenyAccount(Constants.PayType.STATUS_GET_AWARD,Constants.PayType.STATUS_GET_AWARD,payAward.getUserId(),payAward.getPayMoney());
                result.setMessage("Bonus sebesar Rp 2.000 telah ditranfer ke akun bank anda");
            }
        }

        return result;
    }

    /**
     *用户借款订单的打款操作
     */
    @Override
    public  void  loanOrderToUser(){
        SUserPay param=new SUserPay();
        param.setPayType(Constants.BussPay.PAYTYPE_ORDER);
        param.setPayStatus(Constants.BussPay.PAY_STATUS_AUDIT);
        List<SUserPay> loanOrderPayList=getList(param);
        for (SUserPay order:loanOrderPayList){
            logger.info("loan-ordedr:"+order.getId() +" userId:"+order.getUserId());
            SUserBind userCard=sUserBindService.getUserUseCard(order.getUserId());
            if(userCard==null){
                logger.info("not bind card - orderId:"+ order.getId());
                continue;
            }
            Result result = sUserAccountService.moneyAccountLoanOrder(order, order.getUserId(), userCard.getCardAccount(), order.getPayMoney(), order.getAreaCode());
            Result cashResult= result;
            logger.info("loanOrderResult:"+cashResult.getMessage());
        }
    }

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public  boolean  updateStatus(Integer id,Integer status){
        SUserPay userPay=sUserPayMapper.selectByPrimaryKey(id);
        if(userPay==null){
            return false;
        }
        userPay.setPayStatus(status);
        return sUserPayMapper.updateByPrimaryKeySelective(userPay) >0 ;
    }

}
