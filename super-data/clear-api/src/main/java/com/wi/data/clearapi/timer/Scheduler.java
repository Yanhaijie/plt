package com.wi.data.clearapi.timer;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wi.data.clearapi.service.CAppUserService;
import com.wi.data.clearapi.thread.ElasticsearchSaveThread;
import com.wi.data.clearapi.utils.ElasticsearchUtil;
import com.wi.data.clearapi.utils.HttpUtil;
import com.wi.data.clearapi.utils.Result;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class Scheduler {

    @Value("${spring.data.elasticsearch.source.ip}")
    private String host;
    @Value("${spring.data.elasticsearch.source.port}")
    private int port;
    @Value("${spring.data.elasticsearch.source.cluster-name}")
    private String clusterName;
    @Value("${conf.application.loanMarket.url}")
    private String loanMarketUrl;
    @Value("${conf.application.clearThreadNum}")
    private int clearThreadNum;
    @Resource
    private ElasticsearchUtil elasticsearchUtil;
    @Resource
    private CAppUserService cAppUserService;

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

   // @Scheduled(cron="0 0/59 20,23 * * ?")
  //  @Scheduled(cron="0 7 16 * * ?")
    //10个小时跑一次
   // @Scheduled(fixedDelay = 36000000)
   @Scheduled(fixedDelay = 180000)
    public  void  mysqlToEs(){
        logger.info("=======================================================start cp msyql data to elasticsearch====================================================");
        String generalUrl=loanMarketUrl+"/spa/market/buss/findClearUser";
        String contentType="application/json;charset=UTF-8";
        String params = "?page=1&size=10";
        String encoding="UTF-8";
        try {
         //   TransportClient client = elasticsearchUtil.getClient(clusterName,host,port);
            String json = HttpUtil.postGeneralUrl(generalUrl, contentType, params, encoding);
            Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(json);
            List<Map<String,Object>> list = (List) jsonMap.get("data");
            if(list != null && list.size()> 0){
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(clearThreadNum);
                for(Map<String,Object> objectMap : list){
                    String userName = (String)objectMap.get("userName");
                    Map<String,Object> paramMap = Maps.newHashMap();
                    paramMap.put("username",userName);
                    List<Map<String,Object>> locationList = cAppUserService.selectLocation(paramMap);
                    List<Map<String,Object>> appList = cAppUserService.selectApp(paramMap);
                    List<Map<String,Object>> callRecordList = cAppUserService.selectCallrecords(paramMap);
                    List<Map<String,Object>> msgList = cAppUserService.selectMsg(paramMap);
                    List<Map<String,Object>> mobileList = cAppUserService.selectMobile(paramMap);
                    String[] index = new String[]{"device_location","device_app","device_callrecord","device_massage","device_mobile"};
                    String[] type = new String[]{"location","app","callrecord","massage","mobile"};
                    List<List<Map<String,Object>>> lists = Lists.newArrayList();
                    lists.add(locationList);
                    lists.add(appList);
                    lists.add(callRecordList);
                    lists.add(msgList);
                    lists.add(mobileList);
                    Runnable clear = new ElasticsearchSaveThread(index,type,elasticsearchUtil,lists,loanMarketUrl, userName);
                    fixedThreadPool.execute(clear);
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
         logger.info("=======================================================end cp msyql data to elasticsearch====================================================");
    }





}
