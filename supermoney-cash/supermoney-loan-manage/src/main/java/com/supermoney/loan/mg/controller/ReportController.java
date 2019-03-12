package com.supermoney.loan.mg.controller;

import com.supermoney.loan.mg.service.ReportBussService;
import com.supermoney.loan.mg.utils.Constants;
import com.supermoney.loan.mg.utils.NomalUntil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/s/report")
@Api(value = "/s/report",description = "报表统计")
public class ReportController {

    @Autowired
    private ReportBussService reportBussService;
    /**
     * 普通统计
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping("/nomal")
    public Result nomal(String startTime,String endTime) {
       return  reportBussService.nomalCount(startTime,endTime);
    }

    /**
     * 订单统计
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping("/order")
    public Result order(String startTime,String endTime) {
        return  reportBussService.orderCount(startTime,endTime);
    }

    /**
     * 分销统计
     * @param pid  父类id，如果为空则默认查询下级代理统计，如果传入值，则查询当前用户下级的推广总数
     * @param id   根据用户id搜索
     * @param mobile   根据用户手机号搜索
     * @return 查询结果
     */
    @PostMapping("/distribution")
    public Result distribution(String pid,String id,String mobile,Integer pageNo,Integer pageSize){
        return reportBussService.distribution(pid,id,mobile,pageNo,pageSize);
    }

    /**
     * 赏金统计
     * @param userId 用户id
     * @param mobile 电话
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @return
     */
    @PostMapping("/bountyReport")
    public Result bountyReport(String userId,String mobile,Integer pageNo,Integer pageSize){
        return reportBussService.bountyReport(userId,mobile,pageNo,pageSize);
    }


    /**
     * 注册来源统计
     *
     * @param appPackage
     * @param version
     * @param channel
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/regReport")
    public Result regReport(String appPackage,String version,String channel,String share,Integer pageNo,Integer pageSize){
        return reportBussService.regReport(appPackage,version,channel,share,pageNo,pageSize);
    }
}
