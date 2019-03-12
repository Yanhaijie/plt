package com.supermoney.loan.mg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.*;
import com.supermoney.loan.mg.entity.*;
import com.supermoney.loan.mg.entity.vo.LoanMoneyLimitRateVo;
import com.supermoney.loan.mg.entity.vo.SAtCreditInformationVo;
import com.supermoney.loan.mg.entity.vo.SLoanOrderVo;
import com.supermoney.loan.mg.entity.vo.SUserBindVo;
import com.supermoney.loan.mg.service.*;
import com.supermoney.loan.mg.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.StyleRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * Created by xionghuifeng on 2018/05/30.
 */
@Service
@Transactional
public class SLoanOrderServiceImpl extends AbstractService<SLoanOrder> implements SLoanOrderService {

    private static final Logger logger = LoggerFactory.getLogger(SLoanOrderServiceImpl.class);

    @Resource
    private SLoanOrderMapper sLoanOrderMapper;
    @Resource
    private SMessageService sMessageService;
    @Resource
    private SUserPayService sUserPayService;
    @Resource
    private SLoanGrantService sLoanGrantService;
    @Resource
    private SLoanRepayService sLoanRepayService;
    @Resource
    private  SExchangeRateService sExchangeRateService;

    @Resource
    private SXenditVirtualAccountService sXenditVirtualAccountService;

    @Resource
    private SBussLabelService sBussLabelService;
    @Resource
    private  AppProperties appProperties;

    @Resource
    private  SUserBindService sUserBindService;

    @Resource
    private  SAtIdentityService sAtIdentityService;

    @Resource
    private  UserAccountBussService userAccountBussService;

    @Resource
    private SBlackListService sBlackListService;

    @Resource
    private SBountyMapper sBountyMapper;

    @Resource
    private SBountyLoanService sBountyLoanService;

    @Resource
    private SBussLimitMapper sBussLimitMapper;

    @Resource
    private SMerchantOrderMapper sMerchantOrderMapper;

    @Resource
    private  SUserService sUserService;


    /**
     * 查询
     * @param param
     * @return
     */
    public List<SLoanOrderVo> getList(Map<String,Object> param)
    {
        return  sLoanOrderMapper.selectList(param);
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
        List<SLoanOrderVo> list =getList(param);
        Map<String,Object> tempMap = new HashMap<>();
        for(SLoanOrderVo item :list){
            if(StringUtils.isNotBlank(item.getBPJSImg())){item.setBPJSImg(item.getBPJSImg());}
            if(StringUtils.isNotBlank(item.getKKImg())){item.setKKImg(item.getKKImg());}
            if(StringUtils.isNotBlank(item.getCreditCardImg())){item.setCreditCardImg(item.getCreditCardImg());}
            if(StringUtils.isNotBlank(item.getWorkCardImg())){item.setWorkCardImg(item.getWorkCardImg());}
            if(StringUtils.isNotBlank(item.getSalaryCertImg())){item.setSalaryCertImg(item.getSalaryCertImg());}
            if(StringUtils.isNotBlank(item.getCompanyAffidavitImg())){item.setCompanyAffidavitImg(item.getCompanyAffidavitImg());}
            if(StringUtils.isNotBlank(item.getBankStatementImg())){item.setBankStatementImg(item.getBankStatementImg());}
            if(StringUtils.isNotBlank(item.getOtherWorkImg())){item.setOtherWorkImg(item.getOtherWorkImg());}
            if(StringUtils.isNotBlank(item.getAccountImg())){item.setAccountImg(item.getAccountImg());}
            if(StringUtils.isNotBlank(item.getDayHistoryImg())){item.setDayHistoryImg(item.getDayHistoryImg());}
            if(StringUtils.isNotBlank(item.getWeekHistoryImg())){item.setWeekHistoryImg(item.getWeekHistoryImg());}
            if(StringUtils.isNotBlank(item.getMonthHistoryImg())){item.setMonthHistoryImg(item.getMonthHistoryImg());}

            tempMap.put("userName",item.getUserName());
            List<SBlackList> blackLists = sBlackListService.selectList(tempMap);
            if (blackLists != null && blackLists.size() > 0){
                item.setRemarkText(blackLists.get(0).getRemark());
            }
        }

//        for (SLoanOrderVo item:list){
//            item.setNeedAmountArea(sExchangeRateService.usdToIndonesla( item.getNeedAmount(),false));
//            item.setGotAmountArea(sExchangeRateService.usdToIndonesla( item.getGotAmount(),false));
//            item.setLoanAmountArea(sExchangeRateService.usdToIndonesla( item.getLoanAmount(),false));
//            item.setLoanInterestArea(sExchangeRateService.usdToIndonesla( item.getLoanInterest(),false));
//            item.setFeeAmountArea(sExchangeRateService.usdToIndonesla( item.getFeeAmount(),false));
//            item.setOverdueAmountArea(sExchangeRateService.usdToIndonesla( item.getOverdueAmount(),false));
//            item.setPlatformAmountArea(sExchangeRateService.usdToIndonesla( item.getPlatformAmount(),false));
//            item.setAllAmountArea(sExchangeRateService.usdToIndonesla( item.getAllAmount(),false));
//        }
        PageInfo pageInfo = new PageInfo(list);

        return pageInfo;
    }

    /**
     * ID 获取
     * @param id
     * @return
     */
    public  SLoanOrder getOrderById(Integer id){
        return  sLoanOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 订单审核
     * @param id
     * @param orderStatus
     * @param remark
     * @return
     */
    public Result audit(Integer id, Integer orderStatus,String remark){
        SLoanOrder order=getOrderById(id);
        if(order==null){
            return ResultGenerator.genFailResult("订单不存在!");
        }
        if(order.getOrderStatus().compareTo(Constants.Order.STATUS_UN_AUDIT)>0){
            return  ResultGenerator.genFailResult("订单已经审核过!");
        }
        if(orderStatus.compareTo(Constants.Order.STATUS_FAILD_AUDIT)==0){
            //审核失败
            order.setOrderStatus(Constants.Order.STATUS_FAILD_AUDIT);
            sMessageService.sendNomalMessage(order.getUserId(),"Informasi Pemeriksaan Data",remark);
        }
        if(orderStatus.compareTo(Constants.Order.STATUS_PASS_AUDIT)==0){
            //审核通过
            order.setOrderStatus(Constants.Order.STATUS_PASS_AUDIT);
            order.setOrderAuditTime(new Date());
            sMessageService.sendNomalMessage(order.getUserId(),"Informasi Pemeriksaan Data",remark);
            //放款账单生成
            SLoanGrant grant=sLoanGrantService.loanOrderGrant(order);
//            //还款账单生成
//            sLoanRepayService.loanOrderRepay(order,grant);
//            //放款打款
//            sUserPayService.loanOrderPay(order.getUserId(),order.getOrderCode(),order.getGotAmount());
            logger.info("放款业务生成!");
        }
        order.setRemark(remark);
        sLoanOrderMapper.updateByPrimaryKeySelective(order);
        return  ResultGenerator.genSuccessResult("审核成功!");
    }



    /**
     * 测试还款
     * @param id
     * @return
     */
    public Result testRepay(Integer id,boolean isOverdue,Integer amount){
        if(appProperties.getDev().equals("false")){
            return  ResultGenerator.genFailResult("正式环境无法不可操作!");
        }

        SLoanOrder order=getOrderById(id);
        if(StringUtils.isBlank(order.getVirtualAccountId())){
            return  ResultGenerator.genFailResult("没有还款虚拟账户");
        }

       // BigDecimal repayMoneyIdr=isOverdue ? new BigDecimal(amount):order.getLoanAmount();
        //String virtualId=isOverdue? order.getOverdueVirtualAccountId():order.getVirtualAccountId();

        BigDecimal repayMoneyIdr=new BigDecimal(amount);
        String virtualId=order.getVirtualAccountId();

        logger.info("testRepay: isOverdue:"+isOverdue+"  virtualId:"+virtualId+" loanAmount:"+order.getLoanAmount()+" overdueAmount:"+ order.getOverdueAmount()+" idr:"+repayMoneyIdr);
        sXenditVirtualAccountService.payToVirtualAccout(virtualId, repayMoneyIdr.intValue());

        return  ResultGenerator.genSuccessResult();
    }
    /**
     *  逾期费用任务
     */
    @Override
    public void  overdueListCount(){
        List<LoanMoneyLimitRateVo> overdueRateList = getOverdueRate();
        List<SLoanOrder>  overdueList= sLoanOrderMapper.overdueList(null);
        logger.info("overdue list count:"+overdueList.size());
        for(SLoanOrder order:overdueList){
           overdueMoneyCount(order,overdueRateList);
        }
    }
    /**
     * 逾期费用计算
     * @param order
     * @param overdueList
     */
    public  void  overdueMoneyCount(SLoanOrder  order ,List<LoanMoneyLimitRateVo> overdueList){
        logger.info("overdueMoneyCount ordedrCode:"+order.getOrderCode());
        if(order==null){
            throw  new BussException("overdue order is null");
        }
        if(!( order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_ING)==0 || order.getOrderStatus().compareTo(Constants.Loan.STATUS_LOAN_REPAY_WAR)==0 )){
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
        if(days.compareTo(BigDecimal.ZERO)<=0){
            logger.info("days is zero:"+days);
            return;
        }
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


        BigDecimal addLoanAddMoney= days.multiply(order.getLoanAmount().multiply(order.getRate()) );
        logger.info("days:"+days+" overdueMoney:"+overdueMoney+" addLoanAddMoney:"+addLoanAddMoney);
        overdueMoney=overdueMoney.add(addLoanAddMoney);
        BigDecimal inLoanAccountMoneyUsd=BigDecimal.ZERO;

        if(overdueMoney.compareTo(order.getOverdueAmount())<=0){
            //逾期计算金额<=原金额  不处理
            logger.info("overdueMoney is not change , do not nothing!!");
            return;
        }else {
            //逾期计算金额>原金额   增加金额=逾期计算金额-原金额
            BigDecimal addOverdueMoney=overdueMoney.subtract(order.getOverdueAmount());
            inLoanAccountMoneyUsd=sExchangeRateService.indoneslaToUsd(addOverdueMoney);
            logger.info("overdueMoney addOverdueMoney idr:"+addLoanAddMoney+" usd:"+inLoanAccountMoneyUsd);
        }

        BigDecimal overdueMoneyUsd=sExchangeRateService.indoneslaToUsd( overdueMoney);
        order.setOverdueAmount(overdueMoney);
        order.setOverdueAmountUsd(overdueMoneyUsd);
        order.setOverdueLimit(days.intValue());
        order.setOrderStatus(Constants.Loan.STATUS_LOAN_REPAY_WAR);

        BigDecimal virtualAccountMoney=order.getLoanAmount().add(overdueMoney);
        sXenditVirtualAccountService.updateVirtualAccount(order.getVirtualAccountId(),virtualAccountMoney.intValue());

//        if(order.getOverdueVirtualAccountId()==null ||StringUtils.isBlank(order.getOverdueVirtualAccountId())){
//            //OVT8 创建逾期还款账户
//            SUserBindVo virtualBind=getUserVirtualAccountBank(order.getUserId());
//            Result virtualResult=sXenditVirtualAccountService.createVirtualAccount(order.getUserId(),order.getId(),virtualBind.getHoldingName(),virtualBind.getBankCode(),overdueMoney.intValue(),Constants.Loan.VRITUAL_ACCOUNT_OVERDUE);
//            if(virtualResult.getCode()!=ResultCode.SUCCESS.code){
//                throw  new BussException("virtualResult faild,创建逾期还款账户失败!!");
//            }
//            order.setOverdueVirtualAccountId(virtualResult.getData().toString());
//        }else {
//            //OVT8更新逾期还款账户金额
//            sXenditVirtualAccountService.updateVirtualAccount(order.getOverdueVirtualAccountId(),overdueMoney.intValue());
//        }


        SLoanGrant grant=sLoanGrantService.getByOrderCode(order.getUserId(),order.getOrderCode());
        if(grant==null){
            throw  new BussException("overdue grant is null");
        }
        grant.setOverdueAmount(overdueMoney);
        grant.setOverdueAmountUsd(overdueMoneyUsd);
        grant.setOverdueLimit(days.intValue());

        SLoanRepay repay=sLoanRepayService.getByOrderCode(order.getUserId(),order.getOrderCode()).get(0);
        if(repay==null){
            throw  new BussException("overdue repay is null");
        }
        repay.setOverdueAmount(overdueMoney);
        repay.setOverdueAmountUsd(overdueMoneyUsd);
        repay.setOverdueLimit(days.intValue());

        sLoanOrderMapper.updateByPrimaryKeySelective(order);
        sLoanGrantService.update(grant);
        sLoanRepayService.update(repay);
        //借款账户划入结算
        userAccountBussService.inMoneyLoanAccount(Constants.BUSS_TYPE_LOAN, Constants.BUSS_STEP_LOAN_ORDER_OVERDUE_INMONEY, order.getUserId(), inLoanAccountMoneyUsd);
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
     * 订单号获取
     * @param orderCode
     * @return
     */
    @Override
    public SLoanOrder getByOrderCode(String orderCode){
        SLoanOrder order=new SLoanOrder();
        order.setOrderCode(orderCode);
        return sLoanOrderMapper.selectOne(order);
    }

    @Override
    public List<SLoanOrder> selectBlackListOrderList() {
        return sLoanOrderMapper.selectBlackListOrderList();
    }

    /**
     * 获取逾期金额区间利率
     * @return
     */
    public  List<LoanMoneyLimitRateVo> getOverdueRate(){
        SBussLabel label=  sBussLabelService.getByLabelName("overdueRate");
        List<LoanMoneyLimitRateVo> loanList=new ArrayList<LoanMoneyLimitRateVo>();
        loanList= JSONObject.parseArray(label.getBussVal(),LoanMoneyLimitRateVo.class);
        return  loanList;
    }

    /**
     * 按借款ID转API订单
     * @param orderid
     * @param toApiBountyId
     * @return
     */
    public  Result itemToApi(Integer orderid,Integer toApiBountyId){
        SLoanOrder order=sLoanOrderMapper.selectByPrimaryKey(orderid);

        SBounty bountyProduct=sBountyMapper.selectByPrimaryKey(toApiBountyId);
        if(bountyProduct==null){
            return ResultGenerator.genFailResult("allToApi - bountyProduct is null");
        }

        orderToApi(order,bountyProduct);
        return  ResultGenerator.genSuccessResult();
    }
    /**
     * 按时间转API订单
     * @param start
     * @param end
     * @param orderStatus
     *  @param toApiBountyId
     * @return
     */
    @Override
    public  Result allToApi(String start,String end,Integer orderStatus,Integer toApiBountyId){
        if(StringUtils.isBlank(start) || StringUtils.isBlank(end)){
            Date d=new Date();
            start= NomalUntil.DateChangeToStr(d, Calendar.DATE,-3);
            end= NomalUntil.DateToStr(d,"");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("start",start);
        map.put("end",end);
        map.put("orderStatus",orderStatus);
        List<SLoanOrderVo> list=sLoanOrderMapper.selectList(map);

        SBounty bountyProduct=sBountyMapper.selectByPrimaryKey(toApiBountyId);
        if(bountyProduct==null){
            return ResultGenerator.genFailResult("allToApi - bountyProduct is null");
        }

        for(SLoanOrderVo item:list){
            orderToApi(item,bountyProduct);
        }
        return  ResultGenerator.genSuccessResult();
    }

    /**
     * 借款订单转为API订单
     * @param loanOrder
     */
    public void orderToApi(SLoanOrder loanOrder,SBounty bountyProduct){
        if(loanOrder==null){
            return;
        }

        if(hasIngOrder(loanOrder.getUserId(),bountyProduct.getId())){
            return;
        }

        if( !(loanOrder.getOrderStatus().equals(Constants.Loan.STATUS_AUDIT_FAILD)||
                loanOrder.getOrderStatus().equals(Constants.Loan.STATUS_AUDIT_UN)||
                loanOrder.getOrderStatus().equals(Constants.Loan.STATUS_LOAN_AUDIT_FAILD)
                )){
            return;
        }
        SUser user=sUserService.getById(loanOrder.getUserId());
        SBountyLoan bountyLoan=sBountyLoanService.getFirstByBountyId(bountyProduct.getId());
        BigDecimal loanAmount=loanOrder.getNeedAmount();
        SMerchantOrder order=new SMerchantOrder();
        order.setBountyId(bountyProduct.getId());
        order.setLoanAmount(loanAmount);
        order.setDuration(loanOrder.getLoanLimit());
        order.setLoanReason(loanOrder.getLoanReason());
        order.setUserId(loanOrder.getUserId());
        order.setAreaCode(loanOrder.getAreaCode());
        order.setMerchantId(bountyProduct.getMerchantId());
        order.setmOrderCode(bountyProduct.getMerchantProductCode());
        order.setOrderCode(BussCodeGenerate.getApiOrderCode());
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
        order.setUseStatus(getUseStatus(loanOrder.getUserId()));
        order.setOverdueLimit(0);
        order.setUserName(user.getUserName());

        sMerchantOrderMapper.insertSelective(order);

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
