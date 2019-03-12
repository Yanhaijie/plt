package com.supermoney.loan.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.api.dao.*;
import com.supermoney.loan.api.entity.*;
import com.supermoney.loan.api.entity.vo.LoanMoneyLimitRateVo;
import com.supermoney.loan.api.entity.vo.PayPlatformFeeVo;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by xionghuifeng on 2018/05/29.
 */
@Service
@Transactional
public class SLoanOrderServiceImpl extends AbstractService<SLoanOrder> implements SLoanOrderService {
    private static final Logger logger = LoggerFactory.getLogger(SLoanOrderServiceImpl.class);

    @Resource
    private SLoanOrderMapper sLoanOrderMapper;

    @Resource
    private SBountyMapper sBountyMapper;

    @Resource
    private SUserMapper sUserMapper;

    @Resource
    private SBountyService sBountyService;

    @Resource
    private SBussLabelService sBussLabelService;

    @Resource
    private  SBussErrorService sBussErrorService;

    @Resource
    private SExchangeRateService sExchangeRateService;

    @Resource
    private SUserPayMapper sUserPayMapper;

    @Resource
    private  SLoanGrantService sLoanGrantService;

    @Resource
    private  SLoanRepayService sLoanRepayService;

    @Resource
    private  SXenditVirtualAccountService sXenditVirtualAccountService;

    @Resource
    private  SUserBindService sUserBindService;
    @Resource
    private  SAtIdentityService sAtIdentityService;

    @Resource
    private  UserAccountBussService userAccountBussService;

    @Resource
    private SBussLimitMapper sBussLimitMapper;

    @Resource
    private  SMerchantOrderService sMerchantOrderService;

    /**
     * 借款下单
     * @param bountyId
     * @param needAmount
     * @param limit
     * @param unit
     * @param reason
     * @param countryCode
     * @param userId
     * @return
     */
    @Override
    public Result toOrder(Integer bountyId,Integer needAmount,Integer limit,String unit,String reason, String countryCode,Integer userId){
        //身份认证不通过,征信未过 ,无法下单

        if(userId==null){
            return ResultGenerator.genFailResult("user is null");
        }
        SUser user=getUser(userId);
        if(user==null){
            return ResultGenerator.genFailResult("user is null");
        }
        SBounty bountyProduct=getBounty(bountyId);
        if(bountyProduct==null){
            return ResultGenerator.genFailResult("product is null");
        }
        //是否API商户的订单:现金贷类型 、API商户ID不为空
        if(bountyProduct.getAdsType().compareTo(2)==0 && StringUtils.isNotBlank(bountyProduct.getMerchantId())){
                return  sMerchantOrderService.toApiOrder(bountyProduct,needAmount,limit,reason,user,countryCode);
        }
        //某个产品, 审核失败可以再次提交订单 ， 订单还款完成可以再次提交订单，如有其它进行中不能再次下单
        if(hasIngOrder(userId,bountyId)){
            return ResultGenerator.genFailResult(" Pinjaman anda sudah ada, harap lengkapi informasi");
        }
        SBountyLoan loanProduct=sBountyService.getBountyCashLoan(bountyId);
        if(loanProduct==null){
            return  ResultGenerator.genFailResult(" plase add info");
        }

        BigDecimal day=new BigDecimal(limit);
        BigDecimal rateDay=day.subtract(new BigDecimal(7));
        rateDay=rateDay.compareTo(BigDecimal.ZERO)<0? BigDecimal.ZERO:rateDay;
        BigDecimal loanMoney=new BigDecimal(needAmount);
        BigDecimal limitRate=new BigDecimal(0);
        BigDecimal hundred=new BigDecimal(100);
        //区间利率
        List<LoanMoneyLimitRateVo> loanLimitRate=getLoanLimitRate();
        for(LoanMoneyLimitRateVo item :loanLimitRate){
            // val>= start  and   val< end
            if( loanMoney.compareTo(item.getStartVal())>=0 && loanMoney.compareTo(item.getEndVal())==-1){
                limitRate=item.getRate();
            }else if(loanMoney.compareTo(item.getStartVal())>=0 && item.getEndVal().compareTo( new BigDecimal(0))==0) {
                limitRate=item.getRate();
            }
        }
        //借款金额区间服务费率
        BigDecimal areaMoney=BigDecimal.ZERO;
        if(limitRate.compareTo(BigDecimal.ZERO)>0){
            BigDecimal areaRate=limitRate.divide(hundred);
            areaMoney=loanMoney.multiply(areaRate);
        }
        //天服务费率
        BigDecimal dayRate=loanProduct.getDayRate().divide(hundred);
        BigDecimal dayMoney=loanMoney.multiply(dayRate);
        BigDecimal allDayMoney=rateDay.multiply(dayMoney);
        BigDecimal allFeeMoney=areaMoney.add(allDayMoney);
        //第三方平台费用（支付平台手续费）
        PayPlatformFeeVo payFee=getXenditPayFee();

        BigDecimal disRateMoney=payFee.getDisburseRate()!=null? payFee.getDisburseMoney().multiply(BigDecimal.ONE.add(payFee.getDisburseRate().divide(hundred))):payFee.getDisburseMoney();
        BigDecimal payRateMoney= payFee.getPayRate()!=null ? payFee.getPayMoney().multiply(BigDecimal.ONE.add(payFee.getPayRate().divide(hundred))):payFee.getPayMoney();
        BigDecimal platformMoney=disRateMoney.add(payRateMoney);
        //利息利率
        BigDecimal loanRate= loanProduct.getLoanRate().divide(hundred);
        BigDecimal loanRateMoney=loanMoney.multiply(loanRate).multiply(day);
        //总费用=本金+服务费+第三方平台费+利息
        BigDecimal countMoney=loanMoney.add(allFeeMoney).add(platformMoney).add(loanRateMoney);
        //用户到账金额 和 还款金额
        BigDecimal gotMoney=BigDecimal.ZERO;
        BigDecimal repayMoney=BigDecimal.ZERO;
        //1服务费前置利息后置：实收= 借款额度 - 平台服务费 - 转账手续费
        if(loanProduct.getRepaymentMethod().compareTo(1)==0){
            gotMoney=loanMoney.subtract(allFeeMoney).subtract(platformMoney);
            repayMoney=loanMoney.add(loanRateMoney);
        }
        //0服务费利息前置：实收= 借款额度 - 平台服务费 - 转账手续费 - 利息
        else if(loanProduct.getRepaymentMethod().compareTo(0)==0){
            gotMoney=loanMoney.subtract(allFeeMoney).subtract(platformMoney).subtract(loanRateMoney);
            repayMoney=loanMoney;
        }else {
            return  ResultGenerator.genFailResult("repayment err !");
        }

        //到账金额不能    超过借款金额,不能小于0
        if(gotMoney.compareTo(loanMoney)>0 || gotMoney.compareTo(BigDecimal.ZERO)<0){
            return  ResultGenerator.genFailResult("amount faild!");
        }
        //添加订单
        SLoanOrder order =new SLoanOrder();
        order.setOrderCode(BussCodeGenerate.getOrderCode());
        order.setUserId(userId);
        order.setBountyId(bountyId);
        order.setAreaCode(Constants.Country.INDONESIA_CODE);
        order.setNeedAmount(loanMoney);
        order.setLoanAmount(repayMoney);
        order.setAllAmount(countMoney);
        order.setGotAmount(gotMoney);
        order.setFeeRate(dayRate);
        order.setFeeAmount(allFeeMoney);
        order.setRate(loanRate);
        order.setLoanInterest(loanRateMoney);
        order.setPlatformAmount(platformMoney);
        order.setOverdueRate(loanProduct.getOverdueRate());
        order.setRepaymentMethod(loanProduct.getRepaymentMethod());
        order.setLoanReason(reason);
        order.setLoanLimit(limit);
        order.setLoanUnit(Constants.Loan.LIMIT_DAY);
        order.setPeriod(1);
        order.setRepaymentLimit(Integer.valueOf(0));
        order.setRepaymentTotal(BigDecimal.ZERO);
        order.setRepaymentTotalUsd(BigDecimal.ZERO);
        order.setOrderStatus(0);
        order.setOverdueAmount(BigDecimal.ZERO);
        order.setPayOverdueAmount(BigDecimal.ZERO);
        order.setPayOverdueAmountUsd(BigDecimal.ZERO);
        //印尼
        //countryCode.equals(Constants.Country.INDONESIA_CODE.toString())
        order.setPlatformAmountUsd(sExchangeRateService.indoneslaToUsd(platformMoney));
        order.setLoanInterestUsd(sExchangeRateService.indoneslaToUsd(loanRateMoney));
        order.setFeeAmountUsd(sExchangeRateService.indoneslaToUsd(allFeeMoney));
        order.setGotAmountUsd(sExchangeRateService.indoneslaToUsd(gotMoney));
        order.setAllAmountUsd(sExchangeRateService.indoneslaToUsd(countMoney));
        order.setLoanAmountUsd(sExchangeRateService.indoneslaToUsd(repayMoney));
        order.setNeedAmountUsd(sExchangeRateService.indoneslaToUsd(loanMoney));

        sLoanOrderMapper.insertSelective(order);
        return  ResultGenerator.genSuccessResult();
    }


    /**
     * 借款订单回调业务,更新状态和还款生效时间
     * @param userId
     * @param payId
     * @return
     */
    @Override
    public Result orderCallBackBuss(Integer userId, Integer payId){
        SUserPay userPay=sUserPayMapper.selectByPrimaryKey(payId);
        if(userPay==null){
            return  ResultGenerator.genFailResult("orderCallBackBuss userPay is null");
        }

        String orderCode=userPay.getRelatedId();
        SLoanOrder order=new SLoanOrder();
        order.setUserId(userId);
        order.setOrderCode(orderCode);
        order=sLoanOrderMapper.selectOne(order);
        if(order==null){
            return  ResultGenerator.genFailResult("orderCallBackBuss order is null");
        }
        //只有待审核才能操作
        if(!(order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_AUDIT_PASS)==0)){
            return  ResultGenerator.genFailResult("orderCallBackBuss order status faild!");
        }
        //计算还款时间
        Date repayTime=new Date();
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(repayTime);
        calendar.add(Calendar.DATE,order.getLoanLimit());
        repayTime=calendar.getTime();
        //添加还款虚拟账户ID
        SUserBindVo virtualBind=getUserVirtualAccountBank(userId);
        Result virtualCreate=  sXenditVirtualAccountService.createVirtualAccount(userId,order.getId(),virtualBind.getHoldingName(),virtualBind.getBankCode(),order.getLoanAmount().intValue(),Constants.Loan.VRITUAL_ACCOUNT_LOAN);
        if(virtualCreate.getCode()==ResultCode.SUCCESS.code){
            order.setVirtualAccountId(virtualCreate.getData().toString());
        }
        //更新订单还款时间和状态
        order.setOrderStatus(Constants.Loan.STATUS_LOAN_REPAY_ING);
        order.setLoanTime(new Date());
        order.setInterestTime(new Date());
        order.setPlanRepaymentTime(repayTime);
        sLoanOrderMapper.updateByPrimaryKeySelective(order);
        //更新放款单还款时间和状态
        SLoanGrant grant=sLoanGrantService.getByOrderCode(userId,orderCode);
        if(grant==null){
            return  ResultGenerator.genFailResult("orderCallBackBuss grant is null");
        }
        grant.setRepayEndTime(repayTime);
        grant.setGrantStatus(Constants.Loan.GRANT_STATUS_REPAY_ING);
        sLoanGrantService.update(grant);
        //更新还款单还款时间和状态
        List<SLoanRepay> repayList=sLoanRepayService.getByOrderCode(userId,orderCode);

        if(repayList.size()==1){
            //一次性还款
            SLoanRepay loanRepay=repayList.get(0);
            loanRepay.setRepayEndTime(repayTime);
            loanRepay.setRepayStatus(Constants.Loan.REPAY_STATU_REPAYING);
            sLoanRepayService.update(loanRepay);
        }

        if(repayList.size()>1){
            //分期还款: 结束时间-开始时间/期数=每期还款时间   按期数排序

        }
        //借款账户增加还款金额
        userAccountBussService.inMoneyLoanAccount(Constants.BUSS_TYPE_LOAN, Constants.BUSS_STEP_LOAN_ORDER_INMONEY, order.getUserId(), order.getLoanAmountUsd());
        return  ResultGenerator.genSuccessResult();
    }

    /**
     * 解决回调错误重新执行业务
     * @param bussType
     * @param relateId
     * @return
     */
    @Override
    public Result  fixRepayCallBack(Integer bussType,String relateId){
        SBussError bussError=sBussErrorService.getByType(bussType,relateId);
        if(bussError==null){
            return  ResultGenerator.genFailResult("bussError is null");
        }
        JSONObject json=JSON.parseObject(bussError.getJson());
        logger.info("fixRepayCallBack to orderRepay start");
         orderRepay( json.get("virtualId").toString(),new BigDecimal(json.get("amount").toString()));
        logger.info("fixRepayCallBack to orderRepay end");
        return  ResultGenerator.genSuccessResult();
    }


    /**
     * 借款订单还款业务
     * @param virtualId
     * @param amount
     * @return
     */
    @Override
    public  Result orderRepay(String virtualId,BigDecimal amount){
        try{
            logger.info("order Repay vid:"+virtualId+" amount:"+amount);

            //把金额印尼盾转为美元
            BigDecimal usdAmount= sExchangeRateService.indoneslaToUsd(amount);

            SLoanOrder order=getByVirtualIdOrOverdueVirtualId(virtualId);
            if(order==null){
                throw  new BussException("order is null");
            }
            //还款中 和 还款逾期  和 还款完成 才可以进行业务操作
            if( !(order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_ING)==0 ||
                    order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_WAR)==0 ||
                    order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_SUCCESS)==0
            ) ){
                throw  new BussException("order status error:"+order.getOrderStatus().toString());
            }
            //判断订单是否逾期,如果逾期先计算逾期费用
            //do something

            //订单业务结算
            if(order.getPeriod().compareTo(Integer.valueOf(1))==0){
                //一次性还款更新单据数据
                oneceRepay(order,amount,usdAmount);
            }else {
                //分期还款
            }


        }catch (BussException ex){
            logger.info("orderRepay ex:"+ex.getMessage());
            Map<String,Object> param=new HashMap<>();
            param.put("virtualId",virtualId);
            param.put("amount",amount);
            sBussErrorService.addBussError(Constants.App.BUSS_ERROR_REPAY,virtualId,"orderRepay error",JSONObject.toJSONString(param));
            ex.printStackTrace();
            logger.info("orderRepay ex done!");
        }
        return  ResultGenerator.genSuccessResult();
    }

    /**
     * 一次性还款操作
     * @param order
     * @param amount
     * @param usdAmount
     */
    public  void oneceRepay(SLoanOrder order,BigDecimal amount,BigDecimal usdAmount){
        logger.info("oneceRepay start");
        //更新借款订单:金额、时间、状态
        // 待还款状态:   1.未逾期 状态为完成，更新本金.  2.逾期 状态为还款延期,更新本金.
        // 还款延期状态: 1.逾期费用还完: 状态为逾期还款完成,更新逾期费用。 2.逾期费用未还完:更新金额,状态不变，记录逾期还款次数

//        boolean hasOverdue=order.getOverdueVirtualAccountId()!=null&&StringUtils.isNotBlank(order.getOverdueVirtualAccountId());
//        logger.info("hasOverdue:"+hasOverdue);
//        if(hasOverdue&& order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_WAR)==0){
//             logger.info("oneceOverdueRepay start");
//             oneceOverdueRepay(order,amount,usdAmount);
//             return;
//        }else {
//
//        }
        //是否第一次还款
        if(order.getRepaymentTotal()==null){
            order.setRepaymentTotal(BigDecimal.ZERO);
        }
        if(order.getPayOverdueAmount()==null){
            order.setPayOverdueAmount(BigDecimal.ZERO);
        }
        boolean isFirst=order.getRepaymentTotal().compareTo(BigDecimal.ZERO)>0? false:true;
        boolean isFirstOverdue=order.getPayOverdueAmount().compareTo(BigDecimal.ZERO)>0? false:true;
        BigDecimal toRepayAmount=BigDecimal.ZERO;
        BigDecimal toRepayOverdueAmount=BigDecimal.ZERO;
        //第一次还款默认值，非第一次要减去上一次还的金额.
        BigDecimal gotLoanAmount=isFirst? order.getLoanAmount():order.getLoanAmount().subtract(order.getRepaymentTotal());
        BigDecimal gotOverdueAmount=isFirstOverdue? order.getOverdueAmount():order.getOverdueAmount().subtract(order.getPayOverdueAmount());
        gotLoanAmount=gotLoanAmount.compareTo(BigDecimal.ZERO)<0 ? BigDecimal.ZERO:gotLoanAmount;
        gotOverdueAmount=gotOverdueAmount.compareTo(BigDecimal.ZERO)<0? BigDecimal.ZERO:gotOverdueAmount;
        // 0 还款未完成 1 还款完成  2 还款状态异常:多次还款 或者恶意还款
        int isRepayFull=0;
        if(order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_ING)==0){
            //正常还款
            logger.info(" repaybuss nomalrepay:"+order.getOrderCode()+" gotLoanAmount:"+gotLoanAmount);
            toRepayAmount=amount;
            isRepayFull=1;
            int nomalPayLimit=amount.compareTo(gotLoanAmount);
            logger.info(" repaybuss nomalrepay nomalPayLimit:"+nomalPayLimit);
            if(nomalPayLimit<0){
                BigDecimal oweAmount=gotLoanAmount.subtract(amount);
                isRepayFull=oweAmount.compareTo(BigDecimal.ONE)>0 ? 0:1;
                logger.info(" momal repay amount < loanamount    waitRepayAmount:"+oweAmount +" isRepayFull:"+isRepayFull);
            }
        }else if(order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_WAR)==0){
            //逾期还款
             logger.info(" repaybuss overduerepay:"+order.getOrderCode());
             BigDecimal repayAllAmount=gotLoanAmount.add(gotOverdueAmount);
             int overduePayLimit= amount.compareTo(repayAllAmount);
             if(overduePayLimit==0){
                 logger.info(" overdue repay amount = loanamount+overdueamout");
                 toRepayAmount=gotLoanAmount;
                 toRepayOverdueAmount=gotOverdueAmount;
                 isRepayFull=1;
             }

            if(overduePayLimit>0){
                logger.info("overdue repay amount > loanamount+overdueamout");
                toRepayAmount=amount.subtract(gotOverdueAmount);
                toRepayOverdueAmount=gotOverdueAmount;
                isRepayFull=1;
            }

             if(overduePayLimit<0){
                 BigDecimal oweOverdueAmount=repayAllAmount.subtract(amount);
                 logger.info(" overdue repay amount < loanamount+overdueamout waitRepayAmount:"+oweOverdueAmount);
                 isRepayFull=oweOverdueAmount.compareTo(BigDecimal.ONE)>0 ? 0:1;
                 //判断还款少的情况
                 int oweLimit=amount.compareTo(gotLoanAmount);
                 if(oweLimit<1){
                    toRepayAmount=amount;
                    toRepayOverdueAmount=BigDecimal.ZERO;
                 }
                if(oweLimit>0){
                     toRepayAmount=gotLoanAmount;
                     toRepayOverdueAmount=amount.subtract(gotLoanAmount);
                }
             }

        }else{
            //异常还款,其它状态只更新金额，其它不更新
            isRepayFull=2;
            toRepayAmount=amount;
        }


        Date repaymentTime=new Date();
        //更新
        BigDecimal toRepayAmountUsd=sExchangeRateService.indoneslaToUsd(toRepayAmount);
        BigDecimal toRepayOverdueAmountUsd=sExchangeRateService.indoneslaToUsd(toRepayOverdueAmount);

        BigDecimal repaymentTotal=order.getRepaymentTotal()==null? BigDecimal.ZERO.add(toRepayAmount):order.getRepaymentTotal().add(toRepayAmount);
        BigDecimal repaymentTotalUsd=order.getRepaymentTotalUsd()==null? BigDecimal.ZERO.add(toRepayAmountUsd):order.getRepaymentTotalUsd().add(toRepayAmountUsd);
        BigDecimal orderOverdueAmount=order.getPayOverdueAmount()==null ? BigDecimal.ZERO.add(toRepayOverdueAmount):order.getPayOverdueAmount().add(toRepayOverdueAmount);
        BigDecimal orderOverdueAmountUsd=order.getPayOverdueAmountUsd()==null ? BigDecimal.ZERO.add(toRepayOverdueAmountUsd):order.getPayOverdueAmountUsd().add(toRepayOverdueAmountUsd);

        order.setRepaymentTotal(repaymentTotal);
        order.setRepaymentTotalUsd(repaymentTotalUsd);
        order.setPayOverdueAmount(orderOverdueAmount);
        order.setPayOverdueAmountUsd(orderOverdueAmountUsd);
        order.setRepaymentTime(repaymentTime);
        order.setRepaymentLimit(1);
       // Integer orderStatus=hasOverdue ?  Constants.Loan.STATUS_LOAN_REPAY_WAR:Constants.Loan.STATUS_LOAN_REPAY_SUCCESS;

        logger.info(" repaybuss isRepayFull:"+isRepayFull);
        if(isRepayFull==1){
            order.setOrderStatus(Constants.Loan.STATUS_LOAN_REPAY_SUCCESS);
        }
        sLoanOrderMapper.updateByPrimaryKeySelective(order);


        //更新放款订单:金额、时间、状态
        SLoanGrant loanGrant=sLoanGrantService.getByOrderCode(order.getUserId(),order.getOrderCode());
        if(loanGrant==null){
            throw  new BussException("loanGrant is null");
        }
        if(!(loanGrant.getGrantStatus().compareTo(Constants.Loan.GRANT_STATUS_REPAY_ING)==0 ||
                loanGrant.getGrantStatus().compareTo(Constants.Loan.GRANT_STATUS_REPAY_SUCCESS)==0
        )){
            throw  new BussException("loanGrant status error:"+loanGrant.getGrantStatus().toString());
        }
        loanGrant.setLastLimit(1);
        BigDecimal payAmount=loanGrant.getPayAmount()==null? BigDecimal.ZERO.add(toRepayAmount):loanGrant.getPayAmount().add(toRepayAmount);
        BigDecimal payAmountUsd=loanGrant.getPayAmountUsd()==null? BigDecimal.ZERO.add(toRepayAmountUsd):loanGrant.getPayAmountUsd().add(toRepayAmountUsd);
        loanGrant.setPayAmount(payAmount);
        loanGrant.setPayAmountUsd(payAmountUsd);
        loanGrant.setPayOverdueAmount(toRepayOverdueAmount);
        loanGrant.setPayOverdueAmountUsd(toRepayOverdueAmountUsd);
        //Integer loanStatus=hasOverdue ?  Constants.Loan.GRANT_STATUS_REPAY_ING:Constants.Loan.GRANT_STATUS_REPAY_SUCCESS;
        if(isRepayFull==1){
            loanGrant.setGrantStatus(Constants.Loan.GRANT_STATUS_REPAY_SUCCESS);
        }
        loanGrant.setRepaymentTime(repaymentTime);
        sLoanGrantService.update(loanGrant);
        //更新还款单:金额、时间状态
        List<SLoanRepay>  repayList=sLoanRepayService.getByOrderCode(order.getUserId(),order.getOrderCode());
        if(repayList.size()<1){
            throw  new BussException("SLoanRepay is null");
        }
        SLoanRepay repay=repayList.get(0);
        if( !(repay.getRepayStatus().compareTo(Constants.Loan.REPAY_STATU_REPAYING)==0 ||
                repay.getRepayStatus().compareTo(Constants.Loan.REPAY_STATU_REPAYSUCCESS)==0
        )){
            throw  new BussException("repay status error:"+repay.getRepayStatus().toString());
        }
        BigDecimal repayPayAmount=repay.getPayAmount()==null? BigDecimal.ZERO.add(toRepayAmount):repay.getPayAmount().add(toRepayAmount);
        BigDecimal repayPayAmountUsd=repay.getPayAmount()==null? BigDecimal.ZERO.add(toRepayAmountUsd):repay.getPayAmount().add(toRepayAmountUsd);
        repay.setRepayTime(repaymentTime);
        repay.setPayAmount(repayPayAmount);
        repay.setPayAmountUsd(repayPayAmountUsd);
        repay.setPayOverdueAmount(toRepayOverdueAmount);
        repay.setPayOverdueAmountUsd(toRepayOverdueAmountUsd);
        repay.setCurPeriod(1);
        //Integer repayStatus=hasOverdue ?  Constants.Loan.REPAY_STATU_REPAYING:Constants.Loan.REPAY_STATU_REPAYSUCCESS;
        if(isRepayFull==1) {
            repay.setRepayStatus(Constants.Loan.REPAY_STATU_REPAYSUCCESS);
        }
        sLoanRepayService.update(repay);
        //===借款账户划出结算
        //当前还款入账
        BigDecimal outLoanMoney=toRepayAmount.add(toRepayOverdueAmount);
        BigDecimal outLoanMoneyUsd=toRepayAmountUsd.add(toRepayOverdueAmountUsd);
        //入账是否超过要还的欠账
        BigDecimal passLoanMoney=outLoanMoney.subtract(gotLoanAmount.add(gotOverdueAmount));
        if(passLoanMoney.compareTo(BigDecimal.ZERO)>0){
            BigDecimal passLoanMoneyUsd=sExchangeRateService.indoneslaToUsd(passLoanMoney);
            //记录超出金额部分
            userAccountBussService.inFreezeLoanAccount(Constants.BUSS_TYPE_LOAN,Constants.BUSS_STEP_LOAN_ORDER_REPAY_OVERDUE_PASS,order.getUserId(),passLoanMoneyUsd);
            outLoanMoneyUsd=outLoanMoneyUsd.subtract(passLoanMoneyUsd);
        }
        userAccountBussService.outMoneyLoanAccount(Constants.BUSS_TYPE_LOAN, Constants.BUSS_STEP_LOAN_ORDER_REPAY, order.getUserId(), outLoanMoneyUsd);
        logger.info("oneceRepay end");
    }

    /**
     * 逾期还款操作
     * @param order
     * @param amount
     * @param usdAmount
     */
    public  void  oneceOverdueRepay(SLoanOrder order,BigDecimal amount,BigDecimal usdAmount){
        // 还款延期状态: 1.逾期费用还完: 状态为逾期还款完成,更新逾期费用。 2.逾期费用未还完:更新金额,状态不变，记录逾期还款次数

        //已还金额=历史还款+现还款
        BigDecimal  repaydOverdueAmount=order.getPayOverdueAmount()==null?  amount:order.getPayOverdueAmount().add(amount);
        //逾期还清=已还金额>=逾期金额
        int  val=repaydOverdueAmount.compareTo(order.getOverdueAmount());
        boolean hasOverdueFull=val>-1;
        logger.info(" repaydOverdueAmount:"+repaydOverdueAmount+" hasOverdueFull:"+hasOverdueFull+" val:"+val);
        //是否超过逾期还款金额
        if(val==1){
            BigDecimal passOverdueAmount=repaydOverdueAmount.subtract(order.getOverdueAmount());
            BigDecimal passOverdueAmountUsd=sExchangeRateService.indoneslaToUsd(passOverdueAmount);
            logger.info("overdue passmoney passAmount:"+passOverdueAmount+" overAmount:"+order.getOverdueAmount()+" payAmount:"+amount);
            amount=amount.subtract(passOverdueAmount);
            usdAmount=sExchangeRateService.indoneslaToUsd(amount);
            //记录超出金额部分
            userAccountBussService.inFreezeLoanAccount(Constants.BUSS_TYPE_LOAN,Constants.BUSS_STEP_LOAN_ORDER_REPAY_OVERDUE_PASS,order.getUserId(),passOverdueAmountUsd);
        }

        BigDecimal orderOverdueAmount=order.getPayOverdueAmount()==null ? BigDecimal.ZERO.add(amount):order.getPayOverdueAmount().add(amount);
        BigDecimal orderOverdueAmountUsd=order.getPayOverdueAmountUsd()==null ? BigDecimal.ZERO.add(usdAmount):order.getPayOverdueAmountUsd().add(usdAmount);
        Integer overdueNum=order.getOverdueNum()==null ? 1:order.getOverdueNum()+1;
        Integer orderStatus=hasOverdueFull ?  Constants.Loan.STATUS_LOAN_REPAY_SUCCESS:Constants.Loan.STATUS_LOAN_REPAY_WAR;
        if(hasOverdueFull){
            order.setRepaymentTime(new Date());
        }
        order.setPayOverdueAmount(orderOverdueAmount);
        order.setPayOverdueAmountUsd(orderOverdueAmountUsd);
        order.setOrderStatus(orderStatus);
        order.setOverdueNum(overdueNum);
        sLoanOrderMapper.updateByPrimaryKeySelective(order);
        //grant
        SLoanGrant loanGrant=sLoanGrantService.getByOrderCode(order.getUserId(),order.getOrderCode());
        if(loanGrant==null){
            throw  new BussException("overdue loanGrant is null");
        }
        BigDecimal grantOverdueAmount=loanGrant.getPayOverdueAmount()==null ? BigDecimal.ZERO.add(amount):loanGrant.getPayOverdueAmount().add(amount);
        BigDecimal grantOverdueAmountUsd=loanGrant.getPayOverdueAmountUsd()==null ? BigDecimal.ZERO.add(usdAmount):loanGrant.getPayOverdueAmountUsd().add(usdAmount);
        Integer grantStatus=hasOverdueFull?  Constants.Loan.GRANT_STATUS_REPAY_SUCCESS:Constants.Loan.GRANT_STATUS_REPAY_ING;
        loanGrant.setPayOverdueAmount(grantOverdueAmount);
        loanGrant.setPayOverdueAmountUsd(grantOverdueAmountUsd);
        loanGrant.setGrantStatus(grantStatus);
        sLoanGrantService.update(loanGrant);
        //repay
        List<SLoanRepay>  repayList=sLoanRepayService.getByOrderCode(order.getUserId(),order.getOrderCode());
        if(repayList.size()<1){
            throw  new BussException("overdue SLoanRepay is null");
        }
        SLoanRepay repay=repayList.get(0);

        BigDecimal repayOverdueAmount=repay.getPayOverdueAmount()==null ? BigDecimal.ZERO.add(amount):repay.getPayOverdueAmount().add(amount);
        BigDecimal repayOverdueAmountUsd=repay.getPayOverdueAmountUsd()==null ? BigDecimal.ZERO.add(usdAmount):repay.getPayOverdueAmountUsd().add(usdAmount);

        repay.setPayOverdueAmount(repayOverdueAmount);
        repay.setPayOverdueAmountUsd(repayOverdueAmountUsd);
        Integer repayStatus=hasOverdueFull ?  Constants.Loan.REPAY_STATU_REPAYSUCCESS:Constants.Loan.REPAY_STATU_REPAYING;
        repay.setRepayStatus(repayStatus);
        sLoanRepayService.update(repay);
        //借款账户划出结算
        userAccountBussService.outMoneyLoanAccount(Constants.BUSS_TYPE_LOAN, Constants.BUSS_STEP_LOAN_ORDER_OVERDUE_REPAY, order.getUserId(), usdAmount);
    }
    /**
     * 逾期费用计算
     * @param orderCode
     */
    @Override
    public  void  overdueMoneyCount(String  orderCode){
       List<LoanMoneyLimitRateVo> overdueList = getOverdueRate();
        SLoanOrder  order=getByOrderCode(orderCode);
        if(order==null){
            throw  new BussException("overdue order is null");
        }
        if(!(order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_ING)==0)){
            throw  new BussException("overdue order status not repaying ");
        }
        Date now=new Date();
        if(now.getTime()<order.getPlanRepaymentTime().getTime()){
            return;
        }
        Long differ=now.getTime()-order.getPlanRepaymentTime().getTime();
        //计算出相差天数
        BigDecimal days=new BigDecimal( Math.floor(differ/(24*3600*1000)));
        BigDecimal overdueMoney=BigDecimal.ZERO;
        for(LoanMoneyLimitRateVo item:overdueList){
            BigDecimal d=BigDecimal.ZERO;
            BigDecimal r=BigDecimal.ZERO;
            if(item.getStartVal().compareTo(BigDecimal.ZERO)>0 && item.getEndVal().compareTo(BigDecimal.ZERO)>0){
                    // >=start and <end
                    if(days.compareTo(item.getStartVal())>-1 && days.compareTo(item.getEndVal())<0){
                        d=days.subtract(item.getStartVal()).add(BigDecimal.ONE);
                        r=item.getRate();
                    }
                    // >end
                    if(days.compareTo(item.getStartVal())>0 && days.compareTo(item.getEndVal())>0){
                        d=item.getEndVal().subtract(item.getStartVal());
                        r=item.getRate();
                    }
            }
            if(item.getStartVal().compareTo(BigDecimal.ZERO)>0 && item.getEndVal().compareTo(BigDecimal.ZERO)==0){
                    // >=start and end=0
                   if(days.compareTo(item.getStartVal())>-1){
                       d=days.subtract(item.getStartVal()).add(BigDecimal.ONE);
                       r=item.getRate();
                   }
            }
            // (money*rate)*day
            BigDecimal areaMoney=  d.multiply( order.getLoanAmount().multiply(r.divide(new BigDecimal(100))));
            overdueMoney=overdueMoney.add( areaMoney);
        }
        BigDecimal addLoanAddMoney= days.multiply(order.getLoanAmount().multiply(order.getRate().divide(new BigDecimal(100))) );
        logger.info("days:"+days+" overdueMoney:"+overdueMoney+" addLoanAddMoney:"+addLoanAddMoney);
        overdueMoney=overdueMoney.add(addLoanAddMoney);
        order.setOverdueAmount(overdueMoney);
        order.setOverdueLimit(days.intValue());

        SLoanGrant grant=sLoanGrantService.getByOrderCode(order.getUserId(),order.getOrderCode());
        grant.setOverdueAmount(overdueMoney);
        grant.setOverdueLimit(days.intValue());

        SLoanRepay repay=sLoanRepayService.getByOrderCode(order.getUserId(),order.getOrderCode()).get(0);
        repay.setOverdueAmount(overdueMoney);
        repay.setOverdueLimit(days.intValue());

        sLoanOrderMapper.updateByPrimaryKeySelective(order);
        sLoanGrantService.update(grant);
        sLoanRepayService.update(repay);
    }

    /**
     * 实名且有订单
     * @param userId
     * @return
     */
    public Map<String,Object> hasIdentityOrder(Integer userId){
        Map<String,Object> param=new HashMap<>();
        param.put("userId",userId);
       return sLoanOrderMapper.identityOrder(param);
    }

    /**
     * 获取产品
     * @param bountyId
     * @return
     */
    public SBounty getBounty(Integer bountyId){
        return  sBountyMapper.selectByPrimaryKey(bountyId);
    }

    /**
     * 获取用户
     * @param userId
     * @return
     */
    public SUser getUser(Integer userId){
        return  sUserMapper.selectByPrimaryKey(userId);
    }
    /**
     * 获取订单
     * @param map
     * @return
     */
    public List<SLoanOrder> getList(Map<String,Object> map){
      return  sLoanOrderMapper.selectList(map);
    }

    /**
     * 根据还款虚拟账户ID获取订单
     * @param virtualId
     * @return
     */
    public SLoanOrder getByVirtualId(String virtualId){
        SLoanOrder order=new SLoanOrder();
        order.setVirtualAccountId(virtualId);
        return sLoanOrderMapper.selectOne(order);
    }
    /**
     * 根据 还款虚拟账户ID 或 逾期还款虚拟账户ID   获取订单
     * @param virtualId
     * @return
     */
    public SLoanOrder getByVirtualIdOrOverdueVirtualId(String virtualId) {
        Map<String,Object> map=new HashMap<>();
        map.put("virtualId",virtualId);
        return  sLoanOrderMapper.selectByVirtualId(map);
    }

    /**
     * 订单号获取
     * @param orderCode
     * @return
     */
    public SLoanOrder getByOrderCode(String orderCode){
        SLoanOrder order=new SLoanOrder();
        order.setOrderCode(orderCode);
        return sLoanOrderMapper.selectOne(order);
    }

    /**
     * 此产品是否有在进行中的
     * @param userId
     * @param bountyId
     * @return
     */
    public boolean hasIngOrder(Integer userId,Integer bountyId){
        Map<String,Object> map=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        map.put("bountyId",bountyId);
        map.put("userId",userId);
        Integer count=  sLoanOrderMapper.bountyHasOrder(map);
        return  count.compareTo(0)>0;
    }

    /**
     * 借款计算配置信息
     * cache
     * @return
     */
    public  Result loanCountConfig(){
        Map<String,Object> data=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        data.put("loanRateLimit",getLoanLimitRate());
        data.put("platformFee",getXenditPayFee());
        return  ResultGenerator.genSuccessResult(data);
    }

    /**
     * 获取产品计算配置参数
     * @return
     */
    public  Result getloanSet(Integer bountyId){
        SBounty bounty=sBountyMapper.selectByPrimaryKey(bountyId);
        if(bounty==null){
            return  ResultGenerator.genFailResult(" have not loan set! ");
        }
        return  bounty.getMerchantId()!=null&& StringUtils.isNotBlank(bounty.getMerchantId())  ? getApiLoanSet(bountyId):getMyLoanSet(bountyId);
    }

    /**
     * 获取自己的现金贷计算配置
     * @param bountyId
     * @return
     */
    public Result getMyLoanSet(Integer bountyId){
        SBussLabel label=  sBussLabelService.getByLabelName("loanset");
        JSONObject json =JSON.parseObject(label.getBussVal());
        SBountyLoan loanProduct=sBountyService.getBountyCashLoan(bountyId);
        if(loanProduct!=null){
            //还款方式:0服务费利息前置 1服务费前置利息后置
            json.put("repayMethod",loanProduct.getRepaymentMethod());
            //借款利率
            json.put("loanRate",loanProduct.getLoanRate());
            //日服务利率使用:（借款天数-7）*(日服利率*借款额度)
            json.put("dayRate",loanProduct.getDayRate());
        }
        return  ResultGenerator.genSuccessResult(json);
    }

    /**
     * 获取API商户现金贷计算配置
     * @param bountyId
     * @return
     */
    public  Result getApiLoanSet(Integer bountyId){
        Map<String,Object> result=new HashMap<>();
        //服务费区间
        List<Map<String,Object>> loanRateLimit= sBussLimitMapper.selectFeeLimit(bountyId);
        result.put("loanRateLimit",loanRateLimit);
        SBountyLoan loanProduct=sBountyService.getBountyCashLoan(bountyId);
        if(loanProduct!=null){
            //还款方式:0服务费利息前置 1服务费前置利息后置
            result.put("repayMethod",loanProduct.getRepaymentMethod());
            //借款利率
            result.put("loanRate",loanProduct.getLoanRate());
            //日服务利率使用:（借款天数-7）*(日服利率*借款额度)
            result.put("dayRate",loanProduct.getDayRate());
             //固定费用
            JSONObject xenditFee=new JSONObject();
            xenditFee.put("payMoney",loanProduct.getFeeMoeny());
            xenditFee.put("payRate",0);
            xenditFee.put("disburseMoney",0);
            xenditFee.put("disburseRate",0);
            result.put("xenditFee",xenditFee);
        }

        return  ResultGenerator.genSuccessResult(result);
    }




    /**
     * 获取借钱金额区间利率
     * @return
     */
    public List<LoanMoneyLimitRateVo> getLoanLimitRate(){
       SBussLabel label=  sBussLabelService.getByLabelName("loanRateLimit");
        List<LoanMoneyLimitRateVo> loanList=new ArrayList<LoanMoneyLimitRateVo>();
        loanList=JSONObject.parseArray(label.getBussVal(),LoanMoneyLimitRateVo.class);
        return  loanList;
    }

    /**
     * 获取逾期金额区间利率
     * @return
     */
    public  List<LoanMoneyLimitRateVo> getOverdueRate(){
        SBussLabel label=  sBussLabelService.getByLabelName("overdueRate");
        List<LoanMoneyLimitRateVo> loanList=new ArrayList<LoanMoneyLimitRateVo>();
        loanList=JSONObject.parseArray(label.getBussVal(),LoanMoneyLimitRateVo.class);
        return  loanList;
    }

    /**
     * xendit支付平台手续费
     * @return
     */

    public PayPlatformFeeVo getXenditPayFee(){
        SBussLabel label=  sBussLabelService.getByLabelName("xenditFee");
        JSONObject json= JSON.parseObject(label.getBussVal());
        PayPlatformFeeVo fee= JSONObject.toJavaObject(json,PayPlatformFeeVo.class);
        return  fee;
    }


    /**
     * 获取绑定虚拟账户的银行
     * @param userId
     * @return
     */
    public SUserBindVo getUserVirtualAccountBank(Integer userId){
        SUserBindVo bindVo=sUserBindService.getUserBindByCardAccount(userId,"");
        String[] arr=new String[]{"MANDIRI","BRI","BNI"};
        if(!Arrays.asList(arr).contains(bindVo.getBankCode())){
           bindVo.setBankCode("BNI");
        }
        if(StringUtils.isBlank(bindVo.getHoldingName())){
            SAtIdentity identity= sAtIdentityService.getSuccessIdentity(userId);
            bindVo.setHoldingName(identity.getRealName());
        }
        return  bindVo;
    }

    /**
     * 订单列表
     * @param orderType
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @Override
    public Result orderList(Integer orderType, Integer userId, Integer page, Integer size) {
        Integer[] orderTypeArray;
        if (orderType == 0){
            orderTypeArray = null;
        }
        else if(orderType == 1){
            //0 待审核    1 审核通过（放款中） 2 审核不通过  3放款审核通过  4放款审核失败    6 放款失败
            orderTypeArray = new Integer[]{0,1,2,3,4,6};
        }
        else if(orderType == 2){
            // 5、7 待还款（放款成功）   9、10 逾期
            orderTypeArray = new Integer[]{5,7,9,10};
        }
        else if(orderType == 3){
            //8 还款完成
            orderTypeArray = new Integer[]{8};
        }
        else {
            return ResultGenerator.genFailResult("orderType ");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("orderType",orderTypeArray);
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = sLoanOrderMapper.selectOrderList(param);
        PageInfo pageInfo = new PageInfo(list);
        if ((page - 1) * size > pageInfo.getTotal()){
            return ResultGenerator.genSuccessResult(new ArrayList<>());
        }

        for (Object temp :pageInfo.getList()) {
            Map<String, Object> resultMap =  (Map<String, Object>)temp;
            resultMap.put("needAmount",sExchangeRateService.indonwslaFormat( new BigDecimal(resultMap.get("needAmount").toString())) );
            if (resultMap.get("orderStatus").equals(7)){
                int boo = isRepaymentTime((Date) resultMap.get("planRepaymentTime"));
                resultMap.put("showAccountId",boo);
            }
            resultMap.remove("planRepaymentTime");

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date createTime  = dateFormat.parse(resultMap.get("createTime").toString());
                dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                resultMap.put("createTime",dateFormat.format(createTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return ResultGenerator.genSuccessResult(pageInfo.getList());
    }

    /**
     * 订单详情
     * @param orderId
     * @param userId
     * @return
     */
    @Override
    public Result orderDetail(Integer orderId, Integer userId){
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("orderId",orderId);
        Map<String, Object> resultMap = sLoanOrderMapper.selectOrderDetail(param);
        if (resultMap== null){
            return ResultGenerator.genFailResult("不存在该订单");
        }

        resultMap.put("cs","+62 81294224800");

        BigDecimal repayAllAmount=new BigDecimal(resultMap.get("loanAmount").toString()).add(new BigDecimal(resultMap.get("overdueAmount").toString()));
        resultMap.put("repayAllAmount",sExchangeRateService.indonwslaFormat(repayAllAmount));
        logger.info("loanAmount:"+resultMap.get("loanAmount")+" overdueAmount:"+resultMap.get("overdueAmount").toString()+" repayAllAmount:"+repayAllAmount+" format:"+sExchangeRateService.indonwslaFormat(repayAllAmount));

        resultMap.put("needAmount",sExchangeRateService.indonwslaFormat( new BigDecimal(resultMap.get("needAmount").toString())) );
        resultMap.put("loanAmount",sExchangeRateService.indonwslaFormat( new BigDecimal(resultMap.get("loanAmount").toString())) );
        resultMap.put("overdueAmount",sExchangeRateService.indonwslaFormat( new BigDecimal(resultMap.get("overdueAmount").toString())) );
        resultMap.put("gotAmount",sExchangeRateService.indonwslaFormat( new BigDecimal(resultMap.get("gotAmount").toString())) );
        resultMap.put("otherCharges",sExchangeRateService.indonwslaFormat( new BigDecimal(resultMap.get("otherCharges").toString())) );



        if (resultMap.get("orderStatus").equals(7)){

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date createTime  = dateFormat.parse(resultMap.get("planRepaymentTime").toString());
                int boo = isRepaymentTime(createTime);
                resultMap.put("showAccountId",boo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createTime  = dateFormat.parse(resultMap.get("planRepaymentTime").toString());
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            resultMap.put("planRepaymentTime",dateFormat.format(createTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ResultGenerator.genSuccessResult(resultMap);
    }


    public int isRepaymentTime(Date date){
        long threeDaySec = 3 * 60 * 60 * 24 * 1000;
        long subSec = new Date().getTime() - date.getTime();
        if (subSec >= 0){
            return 1;
        }
        else {
            if (Math.abs(subSec) < threeDaySec){
                return 1;
            }
        }
        return 0;
    }


    /**
     *
     * @param orderId
     * @param userId
     * @return
     */
    @Override
    public  Map<String, Object> orderVirtualAccount(Integer orderId, Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("orderId",orderId);
        Map<String, Object> resultMap = sLoanOrderMapper.selectOrderVirtualAccount(param);
        return resultMap;

    }

}
