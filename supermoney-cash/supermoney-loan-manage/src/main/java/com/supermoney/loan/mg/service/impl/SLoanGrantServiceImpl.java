package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SLoanGrantMapper;
import com.supermoney.loan.mg.dao.SLoanOrderMapper;
import com.supermoney.loan.mg.entity.SLoanGrant;
import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.entity.SUserBind;
import com.supermoney.loan.mg.entity.vo.SLoanOrderVo;
import com.supermoney.loan.mg.service.*;
import com.supermoney.loan.mg.utils.*;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/06/04.
 */
@Service
@Transactional
public class SLoanGrantServiceImpl extends AbstractService<SLoanGrant> implements SLoanGrantService {
    @Resource
    private SLoanGrantMapper sLoanGrantMapper;
    @Resource
    private SUserPayService sUserPayService;
    @Resource
    private SLoanOrderService sLoanOrderService;
    @Resource
    private SLoanRepayService sLoanRepayService;
    @Resource
    private SUserBindService sUserBindService;





    /**
     * 查询
     * @param param
     * @return
     */
    public List<SLoanGrant> getList(Map<String,Object> param)
    {
        return  sLoanGrantMapper.selectList(param);
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
        List<SLoanGrant> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 借款订单生成放款账单
     * @param order
     */
    @Override
    public  SLoanGrant loanOrderGrant(SLoanOrder order){
        SLoanGrant grant=new SLoanGrant();
        grant.setGrantCode(BussCodeGenerate.getGrantCode());
        grant.setOrderCode(order.getOrderCode());
        grant.setUserId(order.getUserId());
        grant.setGrantAmount(order.getGotAmount());
        grant.setRepayAmount(order.getLoanAmount());
        grant.setGrantMethod(0);
        grant.setRepayMethod(order.getRepaymentMethod());
        grant.setRepayLimit(order.getLoanLimit());
        grant.setRepayUnit(order.getLoanUnit());
        grant.setPlatformAmount(order.getPlatformAmount());
        grant.setGrantStatus(Constants.Loan.GRANT_STATUS_AUDIT_WAIT);
        grant.setRepayEndTime(order.getPlanRepaymentTime());
        sLoanGrantMapper.insertSelective(grant);
        return  grant;
    }

    /**
     * 根据借款单号获取
     * @param userId
     * @param orderCode
     * @return
     */
    @Override
    public  SLoanGrant getByOrderCode(Integer userId,String orderCode){
        SLoanGrant grant=new SLoanGrant();
        grant.setOrderCode(orderCode);
        grant.setUserId(userId);
        return  sLoanGrantMapper.selectOne(grant);
    }


    /**
     * 审核放款单
     * @param ids
     * @param orderStatus
     * @param remark
     * @return
     */
    @Override
    public Result audit(String[] ids, Integer orderStatus, String remark){

        if(ids==null){
          return    ResultGenerator.genFailResult("ids is null");
        }

        for(String id:ids){
            SLoanGrant grant=sLoanGrantMapper.selectByPrimaryKey(Integer.valueOf(id));
            if(grant==null){
                return ResultGenerator.genFailResult(" grant is null");
            }
            if(grant.getGrantStatus().compareTo(Constants.Loan.GRANT_STATUS_AUDIT_WAIT)>0){
                return  ResultGenerator.genFailResult("订单已审核过！");
            }

            SLoanOrder order=sLoanOrderService.getByOrderCode(grant.getOrderCode());
            if(order==null){
                return  ResultGenerator.genFailResult(" order is null");
            }


            if(orderStatus.compareTo(Constants.Loan.GRANT_STATUS_AUDIT_FAILD)==0){
                //审核失败
                grant.setGrantStatus(Constants.Loan.GRANT_STATUS_AUDIT_FAILD);
                order.setOrderStatus(Constants.Loan.STATUS_LOAN_AUDIT_FAILD);
            }
            else  if(orderStatus.compareTo(Constants.Loan.GRANT_STATUS_UNDO)==0){
                //用户有没有绑卡,如果没有提示
                SUserBind bind=sUserBindService.getUserUseCard(order.getUserId());
                if(bind==null){
                    return  ResultGenerator.genFailResult("请让用户先绑卡成功，不然无法打卡成功!");
                }
                //审核通过
                grant.setGrantStatus(Constants.Loan.GRANT_STATUS_UNDO);
                order.setOrderStatus(Constants.Loan.STATUS_LOAN_AUDIT_PASS);
                order.setLoanAuditTime(new Date());
                //还款账单生成
                sLoanRepayService.loanOrderRepay(order,grant);
                //放款打款
                sUserPayService.loanOrderPay(order.getUserId(),order.getOrderCode(),order.getGotAmountUsd(),Constants.Country.INDONESIA_CODE);
            }
            else {
                return  ResultGenerator.genFailResult("status is null");
            }

            //更新订单状态
            sLoanOrderService.update(order);
            sLoanGrantMapper.updateByPrimaryKeySelective(grant);

        }

        return  ResultGenerator.genSuccessResult();
    }


}
