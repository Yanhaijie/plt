package com.supermoney.loan.mg.service;

import com.supermoney.loan.mg.utils.Result;

import java.util.Map;

public interface ReportBussService {
    /**
     * 正常统计
     * @param startTime
     * @param endTime
     * @return
     */
    public Result nomalCount(String startTime,String endTime);

    /**
     * 订单统计
     * @param startTime
     * @param endTime
     * @return
     */
    public Result orderCount(String startTime,String endTime);

    /**
     * 分销统计
     * @param pid  父类id，如果为空则默认查询下级代理统计，如果传入值，则查询当前用户下级的推广总数
     * @param id   根据用户id搜索
     * @param mobile   根据用户手机号搜索
     * @return 查询结果
     */
    Result distribution(String pid, String id, String mobile, Integer pageNo, Integer pageSize);

    Result bountyReport(String userId, String mobile, Integer pageNo, Integer pageSize);

    /**
     * 注册来源统计
     * @param appPackage app包名
     * @param version app版本
     * @param channel app渠道
     * @param share 分享渠道
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result regReport(String appPackage, String version, String channel, String share, Integer pageNo, Integer pageSize);
}
