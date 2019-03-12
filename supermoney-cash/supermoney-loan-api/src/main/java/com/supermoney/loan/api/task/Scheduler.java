package com.supermoney.loan.api.task;

import com.netflix.discovery.converters.Auto;
import com.supermoney.loan.api.entity.SAtCreditInformation;
import com.supermoney.loan.api.entity.SAtIdentity;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.AppProperties;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *定时任务
 */
@Component
public class Scheduler {

    private  final Logger logger= LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private SUserCashService sUserCashService;
    @Autowired
    private SUserPayService sUserPayService;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private SAtCreditInformationService sAtCreditInformationService;
    @Autowired
    private SUserService sUserService;

    private  boolean transNotDoing=true;

    /**
     * 打款钱到用户上的定时任务,5*60000=300000 5分钟执行一次。
     */
    //临时注释
//    @Scheduled(fixedRate=300000)
//    public  void  transferToUser(){
//        if(appProperties.getRuntask().equals("true")){
//            return;
//        }
//        if(!transNotDoing){
//            return;
//        }
//        transNotDoing=false;
//        logger.info("=======================================================transferToUser start=======================================================");
//        sUserCashService.transferToUser();
//        sUserPayService.loanOrderToUser();
//        logger.info("=======================================================transferToUser end=======================================================");
//        transNotDoing=true;
//    }
//
//    /**
//     *  信用等级刷新 5分钟刷新
//     */
////    @Scheduled(fixedRate=14400000)
//    @Scheduled(fixedRate=300000)
//    public  void  creditGrade(){
//        List<Map<String, Object>> list = sUserService.selectCreditModifyUser();
//
//        logger.info("=======================================================refresh credit grade start=======================================================");
//        for (int i = 0 ;i < list.size(); i++){
//            if (i >= 100) break;
//            Map<String, Object> map = list.get(i);
//            sAtCreditInformationService.gradeForUser((Integer) map.get("id"));
//        }
//        logger.info("=======================================================refresh credit grade stop=======================================================");
//    }

}
