package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SVoucherUserRecord;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;


/**
 * Created by xionghuifeng on 2018/01/14.
 */
public interface SVoucherUserRecordService extends Service<SVoucherUserRecord> {
    /**
     * 获取用户抵用券
     * @param type
     * @return
     */
    public Result getUserVoucherList(int type,Integer userID);
    /**
     * 使用抵用券
     * @param id
     * @param userId
     * @return
     */
    public  Result userVoucher(Integer id,Integer userId,Integer bountyId);
    /**
     * 获取新发放的抵用券
     * @param userId
     * @return
     */
    public  Result getNewVoucher(Integer userId);
    /**
     * 已查看新发的抵用券
     * @param userId
     * @param ids
     * @return
     */
    public  Result viewNewVoucher(Integer userId,String[] ids);

}
