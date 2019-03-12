package com.wi.data.clear.service.impl;

import com.wi.data.clear.service.ClearBussService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
public class ClearBussServiceImpl implements ClearBussService {


    @Async
    public  void  testBuss(int i){
        try {
            int val=getSleepTime(50);
            System.out.println(i+" thing sleep time:"+val);
            Thread.sleep(val*1000);
            System.out.println(i+" thing the end :"+ val);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public  int getSleepTime(int max){
        Random rand = new Random();
        return rand.nextInt(max) + 1;

    }

}
