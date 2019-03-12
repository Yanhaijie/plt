package com.supermoney.loan.mg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueueUtil {

    private static final Logger logger = LoggerFactory.getLogger(MessageQueueUtil.class);

    private MessageQueueTemplate messageQueueTemplate;
    private List<Object> objectList;
    private int index;
    private ReentrantLock lock = new ReentrantLock();
    private int capacity;

    private ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue(5);

    public MessageQueueUtil(MessageQueueTemplate messageQueueTemplate){
        this(messageQueueTemplate,2);
    }

    public MessageQueueUtil(MessageQueueTemplate messageQueueTemplate, int capacity){
        this.messageQueueTemplate = messageQueueTemplate;
        this.objectList = this.messageQueueTemplate.baseData();
        this.index = 0;
        this.capacity = capacity;
    }

    public  void start() throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        for (int i = 0 ; i < this.capacity; i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        consumer();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void producer() throws InterruptedException {
        logger.info("producer-------开始结束------");
        for(int i = 0; i < objectList.size(); i++) {
            System.out.println("total :" + i + "    arrayBlockingQueue.size() = " + arrayBlockingQueue.size());
            arrayBlockingQueue.put(objectList.get(i));
        }
        logger.info("producer-------全部结束------");
    }

    private void consumer() throws InterruptedException {
        while(!messageQueueTemplate.stop(this.objectList,index)) {

            Object sAtIdentity = arrayBlockingQueue.poll(5000, TimeUnit.MILLISECONDS);
            if (sAtIdentity == null){
                continue;
            }

            try {
                this.messageQueueTemplate.consumer(sAtIdentity);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            lock.lock();
            System.out.println(Thread.currentThread().getName() + "  consumer : " + index);
            index++;
            lock.unlock();

        }

        logger.info(Thread.currentThread().getName() + "  consumer-------全部结束------");
    }
}
