package com.supermoney.loan.mg.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SLoanGrant;
import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;
import io.swagger.models.auth.In;

import java.util.Map;


/**
 * Created by wenyuhao on 2018/06/04.
 */
public interface SLoanGrantService extends Service<SLoanGrant> {
    /**
     * 借款订单生成放款账单
     * @param order
     */
    public  SLoanGrant loanOrderGrant(SLoanOrder order);
    /**
     * 分页
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);
    /**
     * 根据借款单号获取
     * @param userId
     * @param orderCode
     * @return
     */
    public  SLoanGrant getByOrderCode(Integer userId,String orderCode);

    /**
     * 审核放款单
     * @param ids
     * @param orderStatus
     * @param remark
     * @return
     */
    public Result audit(String[] ids, Integer orderStatus,String remark);


}
