package com.supermoney.loan.mg.timer;

import com.supermoney.loan.mg.service.*;
//import com.supermoney.loan.mg.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
//import org.yaml.snakeyaml.scanner.Constant;


@Configuration
@EnableScheduling
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private IdentityCheckBussService identityCheckBussService;

    @Autowired
    private SBountyRecordService sBountyRecordService;

    @Autowired
    private SLoanOrderService sLoanOrderService;

    @Autowired
    private GoogleapisService googleapisService;

    private boolean overdueRun=false;
//临时注释 yhj
//    /**
//     * 赏金任务定时结算,10分钟执行一次。
//     */
    @Scheduled(fixedRate=600000)
    public  void  bountyCount(){
        logger.info("bountyCount start");
        sBountyRecordService.bountyCountTask();
        logger.info("bountyCount end");
    }
//
//    /**
//     * 逾期费用计算,  60分钟执行一次   60：3600000  30：1800000   1：60000
//     */
//    @Scheduled(fixedRate =1800000 )
//    public  void  overdueCount(){
//        if(overdueRun){
//            return;
//        }
//        logger.info("overdueListCount start");
//        overdueRun=true;
//        sLoanOrderService.overdueListCount();
//        overdueRun=false;
//        logger.info("overdueListCount end");
//    }
//
//    /**
//     * 根据id获取用户信息， 对比用户有效性
//     */
//    @Scheduled(fixedRate =30000)
//    public  void  getUserInfoById(){
//        identityCheckBussService.doCheck();
//    }
//
//    /**
//     * 黑名单验证
//     */
//    @Scheduled(fixedRate =1800000 )
//    public void blackListTask(){
//        identityCheckBussService.doBlackListCheck();
//    }
}
