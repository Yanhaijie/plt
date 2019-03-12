package com.supers.p2p.assets.task;

import com.supers.p2p.assets.service.SItemInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解锁资产项目定人任务
 */
@Component
public class UnLockItemTask {
    @Resource
    private SItemInfoService sItemInfoService;

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(UnLockItemTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(cron="0 0/10 * * * ?")
    public void reportCurrentTime() {
        System.out.println("解锁项目时间: " + dateFormat.format(new Date()));
        sItemInfoService.unLockItem();
    }
}
