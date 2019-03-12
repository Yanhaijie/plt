package com.supermoney.loan.timer;

import com.supermoney.loan.entity.SSendSmsRecord;
import com.supermoney.loan.entity.SUser;
import com.supermoney.loan.service.SSendSmsRecordService;
import com.supermoney.loan.service.SUserService;
import com.supermoney.loan.vo.Paging;
import com.supermoney.sms.Constants;
import com.supermoney.sms.SmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanhaijie on 2019/3/3.
 */
@Configuration
@EnableScheduling
@Component
public class SendSmsScheduler {

    private static final Logger logger= LoggerFactory.getLogger(SendSmsScheduler.class);

    @Autowired
    SSendSmsRecordService sSendSmsRecordService;

    @Autowired
    SUserService sUserService;

    /**
     * 扫描发送短信
     */
    @Scheduled(fixedDelay = 1000 * 60)
    public void userSendScheduled() {
        logger.info("userSendScheduled=======================start");
        int size=20;
        int page=1;
        int count= sSendSmsRecordService.getCount();
        logger.info("SendSmsScheduler=====userSendScheduled=====发送数量："+count);
        int allPage=0;
        allPage=count/size+1;
        Paging paging=new Paging(0,size);
        while(allPage>=page) {
            List<SSendSmsRecord> resultList=new ArrayList<>();
            List<SSendSmsRecord> sendSmsSchedulerList = sSendSmsRecordService.getAllSendSmsRecord(paging);
            logger.info("SendSmsScheduler=====userSendScheduled=====发送数据："+sendSmsSchedulerList);
            if (sendSmsSchedulerList != null && sendSmsSchedulerList.size() > 0) {
                for(SSendSmsRecord s:sendSmsSchedulerList){
                    if(s.getType() ==2){
                        SUser user=sUserService.getUserById(s.getUserId());
                        sUserService.updateUserLoginLastTime(getSUser(user,":cw"));
                    }
                    if(s.getType() ==4){
                        SUser user=sUserService.getUserById(s.getUserId());
                        sUserService.updateUserLoginLastTime(getSUser(user,":ro"));
                    }
                    s.setSendTime(new Date());
                    boolean isOK= SmsClient.sendMsg(s.getMobile(),s.getSmsRecord(), Constants.PRODUCT_PAASOO_MARKETING);
                    s.setStatus(isOK ==true ? 2 : 3);
                    resultList.add(s);
                }
                if(resultList.size()>0) {
                    sSendSmsRecordService.updateSendSmsRecord(resultList);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            page++;
            paging.setStart(page*size);
            paging.setEnd(size);
        }
        logger.info("userSendScheduled=======================end");
    }

    /**
     * 修改用户发送数量
     * @param user
     * @param type
     * @return
     */
    public static SUser getSUser(SUser user,String type){
        if(user.getSmsSendNumber() != null) {
            int number =cwSvb(user.getSmsSendNumber(),type);
            if(number != -1){
                String oldNumber=number+type;
                number++;
                String sendNumber=number+type;
                user.setSmsSendNumber(user.getSmsSendNumber().replace(oldNumber, sendNumber));
            }else{
                //-1 从未发送过
                user.setSmsSendNumber("1"+type);
            }
        }else{
            user.setSmsSendNumber("1"+type);
        }
        return user;
    }

    /**
     * 截取用户发送数量，得到发送条数
     * @param sb
     * @param name
     * @return
     */
    public static int cwSvb(String sb,String name) {
        if(sb.indexOf(name) != -1){
            String str=sb.substring(sb.indexOf(name)-2,sb.indexOf(name));
            logger.info(str.substring(0,str.indexOf(":")));
            return Integer.parseInt(str.substring(0,str.indexOf(":")));
        }
        return -1;
    }

}
