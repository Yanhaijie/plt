package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SUserCash;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;


/**
 * Created by xionghuifeng on 2018/04/01.
 */
public interface SUserCashService extends Service<SUserCash> {
    /**
     * 提现审核状态的数量
     * @param userId
     * @param status
     * @return
     */
    public  Integer countByStatus(Integer userId,Integer status);
    /**
     * 处理提现审核通过的打款操作
     */
    public void  transferToUser();

    /**
     * 用户最近一条审核中提现
     * @param userId
     * @return
     */
    public Result lastCash(Integer userId);

    /**
     * 更新cash状态
     * @param cashId
     * @param cashStatus
     * @return
     */
    public  boolean  updateCashStatus(Integer cashId,Integer cashStatus);

}
