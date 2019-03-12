package com.wi.data.clearapi.thread;

import com.wi.data.clearapi.utils.ElasticsearchUtil;
import com.wi.data.clearapi.utils.HttpUtil;
import org.elasticsearch.client.transport.TransportClient;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ElasticsearchSaveThread implements Runnable {

    private ElasticsearchUtil elasticsearchUtil;
    private String userName;
    private String loanMarketUrl;
    private String[] index;
    private String[] type;
    private List<List<Map<String,Object>>> list;



    //构造函数
    public ElasticsearchSaveThread(String[] index, String []type, ElasticsearchUtil elasticsearchUtil,List<List<Map<String,Object>>> list,String loanMarketUrl,String userName) {
        this.loanMarketUrl = loanMarketUrl;
        this.userName = userName;
        this.index=index;
        this.type=type;
        this.elasticsearchUtil=elasticsearchUtil;
        this.list=list;
    }
    @Override
    public void run() {
        try {
            int useStatus = 3;
            System.out.println("start cp cp msyql data username="+userName+"=="+new Date());
            for(int i =0;i<list.size();i++){
                List<Map<String,Object>> item = list.get(i);
                if(item != null && item.size() > 0){
                    useStatus = 1;
                    elasticsearchUtil.saveData(null,index[i],type[i],item);
                }
            }
            System.out.println("end cp cp msyql data username="+userName+"=="+new Date());
            String updateClearResultUrl  =loanMarketUrl+"/spa/market/buss/updateClearResult";
            String updatParams = "?userName="+userName+"&useStatus="+useStatus;
            updateClearResultUrl = updateClearResultUrl + updatParams;
            String contentType="application/json;charset=UTF-8";
            String params = "?page=1&size=10";
            String encoding="UTF-8";
            HttpUtil.postGeneralUrl(updateClearResultUrl, contentType, "", encoding);
        } catch (Exception e) {
            System.out.println("Error elasticsearchUtil saveData============================");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}