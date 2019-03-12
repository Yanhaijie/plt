package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SUserPayMapper;
import com.supermoney.loan.mg.entity.SUserPay;
import com.supermoney.loan.mg.entity.vo.SUserPayVo;
import com.supermoney.loan.mg.service.SUserPayService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/26.
 */
@Service
@Transactional
public class SUserPayServiceImpl extends AbstractService<SUserPay> implements SUserPayService {
    @Resource
    private SUserPayMapper sUserPayMapper;

    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SUserPayVo> getList(Map<String,Object> param)
    {
        return  sUserPayMapper.selectList(param);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SUserPayVo> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    @Override
    @Transactional
    public void doPayCheck(Map<String, Object> param) {
        sUserPayMapper.doPayCheck(param);
    }

    /**
     * 借款订单充值
     * @param userId
     * @param relatedId
     * @param payMoney
     * @param areaCode
     */
    @Override
    public  void  loanOrderPay(Integer userId,String relatedId, BigDecimal payMoney,Integer areaCode){
        SUserPay pay=new SUserPay();
        pay.setUserId(userId);
        pay.setPaySourceId(0);
        pay.setRelatedId(relatedId);
        pay.setPayMoney(payMoney);
        pay.setAreaCode(areaCode);
        pay.setPayStatus(Constants.Pay.STATUS_PASS_AUDIT);
        pay.setPayType(Constants.Pay.TYPE_LOAN_ORDER);
        pay.setGetStatus(0);
        sUserPayMapper.insertSelective(pay);
    }

}
