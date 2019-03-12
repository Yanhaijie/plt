package com.wi.data.clear.thread;
import com.wi.data.clear.utils.ElasticsearchUtil;

public class ElasticsearchClearThread implements Runnable {

    private String clearFieldName = "clear_status";
    private ElasticsearchUtil elasticsearchUtil;
    private int from;
    private int end;
    private int size;


    //构造函数
    public ElasticsearchClearThread(int from, int end, int size,ElasticsearchUtil elasticsearchUtil) {
        this.from = from;
        this.end = end;
        this.size = size;
        this.elasticsearchUtil=elasticsearchUtil;
    }
    @Override
    public void run() {
        try {
            elasticsearchUtil.clearDeviceMixTure(from,end,size);
        } catch (Exception e) {
            System.out.println("Error ============================");
            throw new RuntimeException();
        }
    }
}