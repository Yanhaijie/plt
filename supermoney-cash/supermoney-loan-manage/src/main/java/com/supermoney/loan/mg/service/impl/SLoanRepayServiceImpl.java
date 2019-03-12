package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SLoanDamagedMapper;
import com.supermoney.loan.mg.dao.SLoanRepayMapper;
import com.supermoney.loan.mg.entity.SLoanDamaged;
import com.supermoney.loan.mg.entity.SLoanGrant;
import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.entity.SLoanRepay;
import com.supermoney.loan.mg.service.*;
import com.supermoney.loan.mg.utils.*;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/06/04.
 */
@Service
@Transactional
public class SLoanRepayServiceImpl extends AbstractService<SLoanRepay> implements SLoanRepayService {

    private static final Logger logger = LoggerFactory.getLogger(SLoanRepayServiceImpl.class);

    @Resource
    private SLoanOrderService sLoanOrderService;

    @Resource
    private SLoanGrantService sLoanGrantService;

    @Resource
    private SLoanRepayMapper sLoanRepayMapper;

    @Resource
    private SLoanDamagedMapper sLoanDamagedMapper;

    @Resource
    private SExchangeRateService sExchangeRateService;

    @Resource
    private UserAccountBussService userAccountBussService;
    /**
     * 查询
     * @param param
     * @return
     */
    public List<SLoanRepay> getList(Map<String,Object> param)
    {
        return  sLoanRepayMapper.selectList(param);
    }

    /**
     *  用户还款记录
     * @param param
     * @return
     */
    public  List<Map<String,Object>> getRecordList(Map<String,Object> param){
        return  sLoanRepayMapper.getRecordList(param);
    }

    /**
     * 分页
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SLoanRepay> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 用户还款记录
     * @param page
     * @param size
     * @param param
     * @return
     */
    public  PageInfo getRecordByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list =getRecordList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 生成借款还款账单
     * @param order
     * @param grant
     */
    public  void  loanOrderRepay(SLoanOrder order, SLoanGrant grant){

        if(order.getPeriod().compareTo(1)==0){
            //一次性还款
            SLoanRepay repay=new SLoanRepay();
            repay.setUserId(order.getUserId());
            repay.setRepayCode(BussCodeGenerate.getRepayCode());
            repay.setGrantCode(grant.getGrantCode());
            repay.setOrderCode(order.getOrderCode());
            repay.setRepayMethod(grant.getRepayMethod());
            repay.setRepayAmount(grant.getRepayAmount());
            repay.setPeriod(order.getPeriod());
            repay.setCurPeriod(1);
            repay.setRepayStatus(Constants.Loan.REPAY_STATUS_CREATE);
            //印尼
            repay.setRepayAmountUsd(sExchangeRateService.indoneslaToUsd(grant.getRepayAmount()));

            sLoanRepayMapper.insertSelective(repay);
        }
       else if(order.getPeriod().compareTo(1)>0){
            //分期还款


        }else {
            logger.info("period is faild!");
        }
    }
    /**
     * 根据借款单号获取
     * @param userId
     * @param orderCode
     * @return
     */
    public List<SLoanRepay> getByOrderCode(Integer userId,String orderCode){
        SLoanRepay param=new SLoanRepay();
        param.setUserId(userId);
        param.setOrderCode(orderCode);
        return  sLoanRepayMapper.select(param);
    }

    /**
     * 报损
     * @param orderCode
     * @param damagedAmountType
     * @param damagedType
     * @param orderStatus
     * @return
     */
    public Result damaged(String orderCode,Integer damagedAmountType,Integer damagedType,Integer orderStatus){
        SLoanOrder loanOrder= sLoanOrderService.getByOrderCode(orderCode);
        if(loanOrder==null){
            return ResultGenerator.genFailResult(" loan order is null!");
        }

        if( !(loanOrder.getOrderStatus().equals(Constants.Loan.STATUS_LOAN_REPAY_ING) ||
                loanOrder.getOrderStatus().equals(Constants.Loan.STATUS_LOAN_REPAY_WAR) )
                ){
            return  ResultGenerator.genFailResult(" 非待还和逾期状态下不可以报损! ");
        }

        if(orderStatus.equals(Constants.Loan.STATUS_LOAN_REPAY_SUCCESS) ||
                orderStatus.equals(Constants.Loan.STATUS_LOAN_REPAY_BAD)
                ){
            loanOrder.setOrderStatus(orderStatus);
        }else {
            return  ResultGenerator.genFailResult(" order status is faild!");
        }
        //报损单
        SLoanDamaged damaged=new SLoanDamaged();
        damaged.setOrderCode(orderCode);
        damaged.setOrderStatus(orderStatus);
        damaged.setPayAmount(loanOrder.getRepaymentTotal());
        damaged.setPayAmountUsd(loanOrder.getRepaymentTotalUsd());
        damaged.setPayOverdueAmount(loanOrder.getPayOverdueAmount());
        damaged.setPayOverdueAmountUsd(loanOrder.getPayOverdueAmountUsd());
        damaged.setDamagedType(damagedType);
        damaged.setDamagedAmountType(damagedAmountType);
        //金额类型校验
        BigDecimal damagedAmount=BigDecimal.ZERO;
        BigDecimal damagedOverdueAmount=BigDecimal.ZERO;
        if(damagedAmountType.equals(Constants.Loan.DAMAGED_AMOUNTTYPE_ALLNOTPAY)){
            if( !(loanOrder.getRepaymentTotal().compareTo(BigDecimal.ZERO)<1&& loanOrder.getPayOverdueAmount().compareTo(BigDecimal.ZERO)<1)){
                return  ResultGenerator.genFailResult("金额类型错误:所有未还,条件不成立!");
            }
            damagedAmount=loanOrder.getLoanAmount();
            damagedOverdueAmount=loanOrder.getOverdueAmount();
        }
        else if(damagedAmountType.equals(Constants.Loan.DAMAGED_AMOUNTTYPE_SOMELOAN)){
            if( !(loanOrder.getRepaymentTotal().compareTo(BigDecimal.ZERO)>0 && loanOrder.getRepaymentTotal().compareTo(loanOrder.getLoanAmount())<0)){
                return  ResultGenerator.genFailResult("金额类型错误:还部分本息,条件不成立!");
            }
            damagedAmount=loanOrder.getLoanAmount().subtract(loanOrder.getRepaymentTotal());
            damagedOverdueAmount=loanOrder.getOverdueAmount();
        }
        else if(damagedAmountType.equals(Constants.Loan.DAMAGED_AMOUNTTYPE_PAYLOAN_NOTOVERDUE)){
            if( !(loanOrder.getRepaymentTotal().compareTo(loanOrder.getLoanAmount())>-1 && loanOrder.getPayOverdueAmount().compareTo(BigDecimal.ZERO)<1) ){
                return  ResultGenerator.genFailResult("金额类型错误:已还本息逾期未还,条件不成立!");
            }
            damagedOverdueAmount=loanOrder.getOverdueAmount();
        }
        else  if(damagedAmountType.equals(Constants.Loan.DAMAGED_AMOUNTTYPE_PAYLOAN_SOMEOVERDUE)){
            if( !(loanOrder.getRepaymentTotal().compareTo(loanOrder.getLoanAmount())>-1 && loanOrder.getPayOverdueAmount().compareTo(BigDecimal.ZERO)>0) ){
                return  ResultGenerator.genFailResult("金额类型错误:已还本息还部分逾期,条件不成立!");
            }
            damagedOverdueAmount=loanOrder.getOverdueAmount().subtract(loanOrder.getPayOverdueAmount());
        }
        else {
            if( !(loanOrder.getRepaymentTotal().compareTo(loanOrder.getLoanAmount())>-1 && loanOrder.getPayOverdueAmount().compareTo(loanOrder.getOverdueAmount())>-1) ){
                return  ResultGenerator.genFailResult("金额类型错误:所有已还,条件不成立!");
            }
        }
        //金额处理
        damaged.setDamagedRepayAmount(damagedAmount);
        damaged.setDamagedRepayAmountUsd(sExchangeRateService.indoneslaToUsd(damagedAmount));
        damaged.setDamagedOverdueAmount(damagedOverdueAmount);
        damaged.setDamagedOverdueAmountUsd(sExchangeRateService.indoneslaToUsd(damagedOverdueAmount));
        BigDecimal all=damagedAmount.add(damagedOverdueAmount);
        damaged.setDamagedAllAmount(all);
        BigDecimal allUsd=sExchangeRateService.indoneslaToUsd(all);
        damaged.setDamagedAllAmountUsd(allUsd);
        sLoanDamagedMapper.insertSelective(damaged);
        //还款账户减去报损金额
        userAccountBussService.outMoneyLoanAccount(Constants.BUSS_TYPE_LOAN,Constants.BUSS_STEP_LOAN_ORDER_DAMAGED,loanOrder.getUserId(),allUsd);
        //单据状态更新: 订单，放款,还款
        sLoanOrderService.update(loanOrder);
        SLoanGrant grant= sLoanGrantService.getByOrderCode(loanOrder.getUserId(),orderCode);
        grant.setGrantStatus(Constants.Loan.GRANT_STATUS_REPAY_SUCCESS);
        sLoanGrantService.update(grant);

        SLoanRepay sLoanRepay = getByOrderCode(loanOrder.getUserId(),orderCode).get(0);
        sLoanRepay.setRepayStatus(Constants.Loan.REPAY_STATUS_SUCCESS);
        sLoanRepayMapper.updateByPrimaryKeySelective(sLoanRepay);



        return  ResultGenerator.genSuccessResult();
    }


}
