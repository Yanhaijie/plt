package com.supermoney.loan.mg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AddMessageQueueUtil<T> {

    private static final Logger logger = LoggerFactory.getLogger(AddMessageQueueUtil.class);

    private AddMessageQueueTemplate<T> addMessageQueueTemplate;
    private List<T> objectList;
    private int index;
    private ReentrantLock lock = new ReentrantLock();
    private int capacity = 1;

    private ArrayBlockingQueue<T> arrayBlockingQueue = new ArrayBlockingQueue(100);

    public AddMessageQueueUtil(AddMessageQueueTemplate addMessageQueueTemplate){
        this(addMessageQueueTemplate,2);
    }

    public AddMessageQueueUtil(AddMessageQueueTemplate addMessageQueueTemplate, int capacity){
        this.addMessageQueueTemplate = addMessageQueueTemplate;
        this.objectList = new ArrayList<>();
        this.index = 0;
        this.capacity = capacity;
    }

    public  void start() throws InterruptedException {
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

    public void addObject(T obj) throws InterruptedException{
        arrayBlockingQueue.put(obj);
    }

    public void addObject(List<T> obj) throws InterruptedException{
        for(int i = 0; i < obj.size(); i++) {
            arrayBlockingQueue.put(obj.get(i));
        }
    }

    private void consumer() throws InterruptedException {
        while(!addMessageQueueTemplate.stop(this.objectList,index)) {

            T sAtIdentity = arrayBlockingQueue.poll(300000, TimeUnit.MILLISECONDS);
            if (sAtIdentity == null){
                continue;
            }

            try {
                this.addMessageQueueTemplate.consumer(sAtIdentity);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            lock.lock();
            index++;
            lock.unlock();
        }

        logger.info(Thread.currentThread().getName() + "  consumer-------全部结束------");
    }
}
