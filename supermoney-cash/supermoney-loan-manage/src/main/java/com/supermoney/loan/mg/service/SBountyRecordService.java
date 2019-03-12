package com.supermoney.loan.mg.service;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SBountyRecord;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/06.
 */
public interface SBountyRecordService extends Service<SBountyRecord> {
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 审核任务
     * @param status
     * @param ids
     * @return
     */
    public Result auditTask(Integer status, String[] ids);

    /**
     * 赏金结算定时任务
     */
    public void bountyCountTask();


}
