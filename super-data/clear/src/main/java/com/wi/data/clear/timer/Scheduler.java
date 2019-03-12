package com.wi.data.clear.timer;


import com.wi.data.clear.utils.ElasticsearchMigratingUtil;
import com.wi.data.clear.utils.ElasticsearchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private ElasticsearchUtil elasticsearchUtil;
    @Autowired
    private ElasticsearchMigratingUtil elasticsearchMigratingUtil;







    /**
     * 每天2点到5点阶段中，每一分钟触发一次Elasticsearch 数据（max=10000）迁移
     */

    @Scheduled(cron="0 51 14 * * ?")
    public  void  elasticsearchDataMigrating(){
        logger.info("=======================================================Elasticsearch Data Migrating start====================================================");
        try {
            elasticsearchMigratingUtil.migratingDataAndUpdate();
        }catch (Exception e){
            logger.info("=======================================================Elasticsearch Data Migrating Exception===========================================");
            e.printStackTrace();
        }
        logger.info("=======================================================Elasticsearch Data Migrating end=======================================================");
    }



    /**
     * 每天2点到5点阶段中，每一分钟触发一次Elasticsearch 数据（max=10000）迁移
     */

     @Scheduled(fixedDelay = 300000)
    public  void  clearDeviceInfo(){
        logger.info("=======================================================clearDeviceInfo start====================================================");
        try {
                elasticsearchUtil.clearDeviceInfo();
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("=======================================================clearDeviceInfo end=======================================================");
    }

  //  @Scheduled(cron="0 0/5 16,23 * * ?")
   // @Scheduled(cron="   0 20 15 * * ?")
    @Scheduled(fixedDelay = 300000)
    public  void  clearDeviceMixTure(){
        logger.info("======================================================= clearDeviceMixTure start====================================================");
        try {
            elasticsearchUtil.clearDeviceMixTure();
        }catch (Exception ex){

        }
        logger.info("=======================================================clearDeviceMixTure end=======================================================");
    }

}
