package com.supermoney.loan.mg.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/30.
 */
public interface SLoanOrderService extends Service<SLoanOrder> {
    /**
     * 分页
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);
    /**
     * 订单审核
     * @param id
     * @param orderStatus
     * @param remark
     * @return
     */
    public Result audit(Integer id, Integer orderStatus,String remark);

    /**
     * 测试还款
     * @param id
     * @return
     */
    public Result testRepay(Integer id,boolean isOverdue,Integer amount);
    /**
     *  逾期任务统计
     */
    public void  overdueListCount();
    /**
     * 订单号获取
     * @param orderCode
     * @return
     */
    public SLoanOrder getByOrderCode(String orderCode);

    public List<SLoanOrder> selectBlackListOrderList();

    /**
     * 按时间转API订单
     * @param start
     * @param end
     * @return
     */
    public  Result allToApi(String start,String end,Integer orderStatus,Integer toApiBountyId);
}
