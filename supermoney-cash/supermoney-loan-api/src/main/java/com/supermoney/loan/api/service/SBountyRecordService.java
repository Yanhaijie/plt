package com.supermoney.loan.api.service;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.api.entity.SBountyRecord;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/06.
 */
public interface SBountyRecordService extends Service<SBountyRecord> {
    /**
     * 分页获取
     *
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String, Object> param);
    /**
     * 查询用户赏金记录
     * @param page
     * @param size
     * @param param
     * @return
     */
    public Result getUserRecordByPage(int page, int size, Map<String, Object> param);
    /**
     * 用户赏金任务统计信息
     * @param userId
     * @return
     */
    public  Result userBountyTotal(Integer userId);
    /**
     * 赏金任务回调记录处理
     * @param param
     * @return
     */
    public  Result  taskCallBack(Map<String,Object> param);


}
