package com.supermoney.loan.timer;

import com.supermoney.loan.entity.SSendSmsRecord;
import com.supermoney.loan.entity.SUser;
import com.supermoney.loan.service.*;
import com.supermoney.loan.util.Constants;
import com.supermoney.loan.vo.UserSendSmsVo;
import com.supermoney.loan.vo.WaitkeOutBalanceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@EnableScheduling
@Component
public class Scheduler {

    private static final Logger logger= LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    SUserService userService;

    @Autowired
    SSendSmsRecordService sSendSmsRecordService;

    @Autowired
    SUserAccountService sUserAccountService;

    @Autowired
    SUserAccountBookService sUserAccountBookService;


    @Autowired
    SBussLabelService sBussLabelService;


    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 扫描用户最后登录时间
     */
    @Scheduled(fixedRate = 1000 * 60 * 2)
    //@Scheduled(cron="0 0 11,0,0 * * ?")
    public void lastLoginTimeScheduled() {

        Map<Integer,Object> map = redisTemplate.opsForHash().entries(Constants.REDIS_LASTLOGINTIMEKEY);

        redisTemplate.delete(Constants.REDIS_LASTLOGINTIMEKEY);
        if(map != null && map.size() > 0){
            logger.info("用户最后登录时间数据"+map.toString());
            List<SUser> list=new ArrayList<>();
            for(Map.Entry<Integer,Object> entry:map.entrySet()){
                SUser sUser=new SUser();
                sUser.setId(entry.getKey());
                try {
                    sUser.setLostLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entry.getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list.add(sUser);
            }
            sSendSmsRecordService.updateLastLoginTime(list);
        }
    }

    /**
     * 用户分成
     */
    @Scheduled(fixedRate = 1000 * 60 * 2)
    //@Scheduled(cron="0 0 11,0,0 * * ?")
    public void userRewardScheduled(){

        List<WaitkeOutBalanceVo> waitkeOutBalanceVoList=sUserAccountBookService.getAllUserAccountBook();
        logger.info("Scheduler=====userRewardScheduled====用户分成："+waitkeOutBalanceVoList);
        List<SSendSmsRecord> sSendSmsRecordAddList = new ArrayList<SSendSmsRecord>();
        List<SSendSmsRecord> sSendSmsRecordUpdList = new ArrayList<SSendSmsRecord>();
        if(waitkeOutBalanceVoList != null && waitkeOutBalanceVoList.size() > 0) {
            boolean isOK = false;
            for (WaitkeOutBalanceVo waitkeOutBalanceVo : waitkeOutBalanceVoList) {
                SSendSmsRecord sSendSmsRecord = new SSendSmsRecord();
                sSendSmsRecord.setType(4);
                sSendSmsRecord.setSendTime(new Date());
                sSendSmsRecord.setUserId(waitkeOutBalanceVo.getUserId());
                //用户模板
                String result=sBussLabelService.getSmsSendTemplate("userReward");
                String template= (waitkeOutBalanceVo.getUserName() != null) ? result.replace("%%", waitkeOutBalanceVo.getUserName()) : result.replace("%%", "Trump");
                sSendSmsRecord.setSmsRecord(template);

                sSendSmsRecord.setStatus(1);
                sSendSmsRecord.setMobile(waitkeOutBalanceVo.getMobile());
                if (waitkeOutBalanceVo.getSmsSendNumber() != null) {
                    //发送次数
                    int number = Integer.parseInt(cwSvb(waitkeOutBalanceVo.getSmsSendNumber(),"ro"));
                    if (number >= 3) {
                        continue;
                    }
                    if (!isToday(waitkeOutBalanceVo.getSendTime())) {
                        sSendSmsRecordUpdList.add(sSendSmsRecord);
                    }
                } else {
                    sSendSmsRecordAddList.add(sSendSmsRecord);
                }
            }
        }
        //批量修改
        if (sSendSmsRecordUpdList != null && sSendSmsRecordUpdList.size() > 0) {
            sSendSmsRecordService.updateSendSmsRecord(sSendSmsRecordUpdList);
        }
        //批量插入
        if (sSendSmsRecordAddList != null && sSendSmsRecordAddList.size() > 0) {
            sSendSmsRecordService.insertSendSmsRecord(sSendSmsRecordAddList);
        }
        System.out.println("userRewardScheduled=======================end");
    }

    /**
     * 账户带提现余额超过XX
     */
    //@Scheduled(cron="0 0 11,0,0 * * ?")
    @Scheduled(fixedRate = 1000 * 60 * 1)
    public void waitakeOutBalanceScheduled() {
        System.out.println("waitakeOutBalanceScheduled=======================start");
        List<WaitkeOutBalanceVo> waitkeOutBalanceVoList = sUserAccountService.getUserAccount();
        logger.info("Scheduler=====userRewardScheduled====账户带提现："+waitkeOutBalanceVoList);
        List<SSendSmsRecord> sSendSmsRecordAddList = new ArrayList<SSendSmsRecord>();
        List<SSendSmsRecord> sSendSmsRecordUpdList = new ArrayList<SSendSmsRecord>();
        if (waitkeOutBalanceVoList != null) {
            boolean isOK = false;
            for (WaitkeOutBalanceVo waitkeOutBalanceVo : waitkeOutBalanceVoList) {
                SSendSmsRecord sSendSmsRecord = new SSendSmsRecord();
                sSendSmsRecord.setType(2);
                sSendSmsRecord.setStatus(1);
                sSendSmsRecord.setSendTime(new Date());
                sSendSmsRecord.setUserId(waitkeOutBalanceVo.getUserId());
                //模板
                String result=sBussLabelService.getSmsSendTemplate("waitakeOutBalance");
                String template= (waitkeOutBalanceVo.getUserName() != null) ? result.replace("%%", waitkeOutBalanceVo.getUserName()) : result.replace("%%", "Trump");
                sSendSmsRecord.setSmsRecord(template);

                sSendSmsRecord.setMobile(waitkeOutBalanceVo.getMobile());
                //获取user表中 发送次数
                if(waitkeOutBalanceVo.getSmsSendNumber()!=null){
                    //发送次数
                    int number=Integer.parseInt(cwSvb(waitkeOutBalanceVo.getSmsSendNumber(),"cw"));
                    if(number >= 3){
                        continue;
                    }
                    //是否今天发送的
                    if(!isToday(waitkeOutBalanceVo.getSendTime())) {
                        sSendSmsRecordUpdList.add(sSendSmsRecord);
                    }
                }else{
                    sSendSmsRecordAddList.add(sSendSmsRecord);
                }
            }
        }
        //批量修改
        if (sSendSmsRecordUpdList != null && sSendSmsRecordUpdList.size() > 0) {
            sSendSmsRecordService.updateSendSmsRecord(sSendSmsRecordUpdList);
        }
        //批量插入
        if (sSendSmsRecordAddList != null && sSendSmsRecordAddList.size() > 0) {
            sSendSmsRecordService.insertSendSmsRecord(sSendSmsRecordAddList);
        }
        System.out.println("waitakeOutBalanceScheduled=======================end");
    }



    /**
     * 截取字符串
     * @return
     */
    public static String cwSvb(String sb,String name) {
        if(sb.indexOf(name) != -1){
            String str=sb.substring(sb.indexOf(name)-2,sb.indexOf(name));
            return str.substring(0,str.indexOf(":"));
        }
        return "";
    }

    /**
     * 超N天未登录
     */
    @Scheduled(cron="0 30 11,0,0 * * ?")
    //@Scheduled(fixedRate = 1000 * 60 * 3)
    public void userNoLoginScheduled() {
        System.out.println("userNoLogin=======================start");
        List<UserSendSmsVo> userSendSmsVoList = userService.getAllSUser();
        logger.info("Scheduler=====userRewardScheduled====未登录用户："+userSendSmsVoList);
        if (userSendSmsVoList != null) {
            List<SSendSmsRecord> sSendSmsRecordAddList = new ArrayList<SSendSmsRecord>();
            List<SSendSmsRecord> sSendSmsRecordUpdList = new ArrayList<SSendSmsRecord>();
            for (UserSendSmsVo s : userSendSmsVoList) {
                SSendSmsRecord sSendSmsRecord = new SSendSmsRecord();
                sSendSmsRecord.setType(1);
                sSendSmsRecord.setStatus(1);
                sSendSmsRecord.setUserId(s.getUserId());
                sSendSmsRecord.setSendTime(new Date());
                sSendSmsRecord.setMobile(s.getMobile());
                boolean isOK=false;
                if (s.getSendTime() != null) {
                    //7天未登录
                    if (!timeCompareBefore(s.getSendTime(),s.getLastLoginTime(),3) && timeCompareBefore(s.getSendTime(),s.getLastLoginTime(),7)) {
                        if(!timeCompareBefore(new Date(),s.getLastLoginTime(),7)){
                            String result=sBussLabelService.getSmsSendTemplate("sevenDaysNoLogin");
                            String template= (s.getUserName() != null) ? result.replace("%%", s.getUserName()) : result.replace("%%", "Trump");
                            sSendSmsRecord.setSmsRecord(template);
                        }else {
                            continue;
                        }
                        //15天未登录
                    } else if (!timeCompareBefore(s.getSendTime(),s.getLastLoginTime(),7) && timeCompareBefore(s.getSendTime(),s.getLastLoginTime(),15)) {
                        if(!timeCompareBefore(new Date(),s.getLastLoginTime(),15)) {
                            String result=sBussLabelService.getSmsSendTemplate("fifteenDaysNoLogin");
                            String template= (s.getUserName() != null) ? result.replace("%%", s.getUserName()) : result.replace("%%", "Trump");
                            sSendSmsRecord.setSmsRecord(template);
                        }else {
                            continue;
                        }
                    }else{
                        continue;
                    }
                    System.out.printf(String.valueOf(s.getLastLoginTime()));
                    sSendSmsRecordUpdList.add(sSendSmsRecord);
                    continue;
                }else{
                    if(s.getStatus() != 1) {
                        //3天未登录
                        String result=sBussLabelService.getSmsSendTemplate("threeDaysNoLogin");
                        String template= (s.getUserName() != null) ? result.replace("%%", s.getUserName()) : result.replace("%%", "Trump");
                        sSendSmsRecord.setSmsRecord(template);
                        sSendSmsRecordAddList.add(sSendSmsRecord);
                    }
                }
            }
            //批量修改
            if (sSendSmsRecordUpdList != null && sSendSmsRecordUpdList.size() > 0) {
                sSendSmsRecordService.updateSendSmsRecord(sSendSmsRecordUpdList);
            }
            //批量插入
            if (sSendSmsRecordAddList != null && sSendSmsRecordAddList.size() > 0) {
                sSendSmsRecordService.insertSendSmsRecord(sSendSmsRecordAddList);
            }
        }
        System.out.println("userNoLogin=======================end");
    }

    /**
     * 是否今天
     * @param inputJudgeDate
     * @return
     */
    public static boolean isToday(Date inputJudgeDate) {
        boolean flag = false;
        if(inputJudgeDate == null){
            return flag;
        }
        //获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date nowDate = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(nowDate);
        String subDate = format.substring(0, 10);
        //定义每天的24h时间范围
        String beginTime = subDate + " 00:00:00";
        String endTime = subDate + " 23:59:59";
        Date paseBeginTime = null;
        Date paseEndTime = null;
        try {
            paseBeginTime = dateFormat.parse(beginTime);
            paseEndTime = dateFormat.parse(endTime);
        } catch (Exception e) {
            System.out.println("e.getMessage()");
        }
        if(inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
            flag = true;
        }
        return flag;
    }

    /**
     * day 天前的时间
     * @param day
     * @param day
     * @return
     */
    public static Date getDateBefore(Date oldTime, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(oldTime);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 时间比较
     * @param dateOne
     * @param dateTwo
     * @param day
     * @return
     */
    public static boolean timeCompareBefore(Date dateOne,Date dateTwo,int day) {
        dateOne=getDateBefore(dateOne,day);
        if(dateOne.before(dateTwo)){
            return true;
        }else{
            return false;
        }
    }
}
