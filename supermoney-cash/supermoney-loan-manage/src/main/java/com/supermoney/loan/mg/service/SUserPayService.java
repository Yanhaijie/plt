package com.supermoney.loan.mg.service;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SUserPay;
import com.supermoney.loan.mg.entity.vo.SUserPayVo;
import com.supermoney.loan.mg.utils.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/26.
 */
public interface SUserPayService extends Service<SUserPay> {
    /**
     * 查询
     * @param param
     * @return
     */
    public List<SUserPayVo> getList(Map<String,Object> param);
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 审核
     * @return
     */
    public void doPayCheck(Map<String, Object> param);

    /**
     * 借款订单充值
     * @param userId
     * @param relatedId
     * @param payMoney
     * @param areaCode
     */
    public  void  loanOrderPay(Integer userId,String relatedId, BigDecimal payMoney,Integer areaCode);

}
